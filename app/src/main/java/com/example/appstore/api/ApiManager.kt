package com.example.appstore.api

import android.content.Context
import com.example.api.core.ApiConverterFactory
import com.example.api.service.ITunesService
import com.example.appstore.BuildConfig
import com.example.appstore.util.JsonUtils
import com.google.gson.Gson
import com.himphen.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class ApiManager(val context: Context) {
    private val connectTimeout: Long = 15
    private val readTimeout: Long = 15
    private val writeTimeout: Long = 15
    private lateinit var client: OkHttpClient
    lateinit var iTunesService: ITunesService

    private fun initClient() {
        // init okHttp Client
        client = OkHttpClient().newBuilder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .addNetworkInterceptor(
                HttpLoggingInterceptor(HttpLogger()).apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS else HttpLoggingInterceptor.Level.NONE
                }
            )
            .addInterceptor(ApiLogInterceptor())
            .build()

        iTunesService = Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ApiConverterFactory(Gson()))
            .build()
            .create(ITunesService::class.java)
    }

    init {
        initClient()
    }

    // TODO refactor later
    fun reInitClient() {
        initClient()
    }
}

class HttpLogger : HttpLoggingInterceptor.Logger {
    private var mMessage: StringBuffer = StringBuffer("")

    override fun log(message: String) {
        var tempMessage = message
        if (message.startsWith("--> POST") ||
            message.startsWith("--> GET") ||
            message.startsWith("--> PATCH") ||
            message.startsWith("--> PUT")
        ) {
            mMessage.setLength(0)
        }
        if (message.startsWith("{") && message.endsWith("}") ||
            message.startsWith("[") && message.endsWith("]")
        ) {
            tempMessage = JsonUtils.formatJson(message)
        }
        mMessage.append(tempMessage + "\n")

        if (message.startsWith("<-- END HTTP")) {
            Logger.log(Logger.INFO, "api", mMessage.toString(), null)
        }
    }
}
