package ru.druliks.drumesschat.presentation.injection

import dagger.Module
import dagger.Provides
import ru.druliks.drumesschat.BuildConfig
import ru.druliks.drumesschat.data.account.AccountRemote
import ru.druliks.drumesschat.remote.account.AccountRemoteImpl
import ru.druliks.drumesschat.remote.core.Request
import ru.druliks.drumesschat.remote.service.ApiService
import ru.druliks.drumesschat.remote.service.ServiceFactory
import javax.inject.Singleton

//Класс-модуль для предоставления зависимостей ApiService и AccountRemote.
@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService = ServiceFactory.makeService(BuildConfig.DEBUG)

    @Singleton
    @Provides
    fun provideAccountRemote(request: Request, apiService: ApiService): AccountRemote {
        return AccountRemoteImpl(request, apiService)
    }
}