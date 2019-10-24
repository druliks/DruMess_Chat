package ru.druliks.drumesschat.presentation.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.druliks.drumesschat.data.account.AccountCache
import ru.druliks.drumesschat.data.account.AccountRemote
import ru.druliks.drumesschat.data.account.AccountRepositoryImpl
import ru.druliks.drumesschat.domain.account.AccountRepository
import javax.inject.Singleton

//Класс-модуль для предоставления зависимойстей контекста и репозитория
@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext():Context=context

    @Provides
    @Singleton
    fun provideAccountRepository(remote: AccountRemote, cache: AccountCache): AccountRepository {
        return AccountRepositoryImpl(remote, cache)
    }
}