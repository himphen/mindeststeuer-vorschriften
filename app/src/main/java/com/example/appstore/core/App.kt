package com.example.appstore.core

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.appstore.BuildConfig
import com.himphen.logger.AndroidLogAdapter
import com.himphen.logger.Logger
import com.himphen.logger.PrettyFormatStrategy
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate()

        initLogger()
        initKoin()
    }

    // init logger
    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(2)
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    private fun initKoin() {
        startKoin {
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(
                koinServiceModule,
                koinRepositoryModule,
                koinUIModule,
                koinUseCaseModule
            )
        }
    }
}
