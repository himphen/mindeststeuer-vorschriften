package com.example.appstore.core

import com.example.appstore.api.ApiManager
import com.example.appstore.repository.AlbumRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val koinServiceModule: Module = module {
    single { ApiManager(androidContext()) }
}

val koinUIModule: Module = module {}

val koinRepositoryModule: Module = module {
    single { AlbumRepository(get()) }
}
