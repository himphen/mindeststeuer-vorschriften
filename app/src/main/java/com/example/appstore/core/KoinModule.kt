package com.example.appstore.core

import com.example.appstore.api.ApiManager
import com.example.appstore.db.LocalDatabase
import com.example.appstore.domain.album.AlbumInteractor
import com.example.appstore.domain.album.GetAlbumsFromAPI
import com.example.appstore.domain.album.GetBookmarkedAsLiveData
import com.example.appstore.repository.AlbumRepository
import com.example.appstore.ui.main.AlbumViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val koinServiceModule: Module = module {
    single { ApiManager(androidContext()) }
}

val koinUIModule: Module = module {
    viewModel {
        AlbumViewModel(get())
    }
}

val koinRepositoryModule: Module = module {
    single { LocalDatabase.getInstance(androidContext()) }
    single { get<LocalDatabase>().albumDao() }
    single { AlbumRepository(get()) }
}

val koinUseCaseModule: Module = module {
    single { AlbumInteractor(get(), get()) }
    single { GetAlbumsFromAPI(get()) }
    single { GetBookmarkedAsLiveData(get()) }
}