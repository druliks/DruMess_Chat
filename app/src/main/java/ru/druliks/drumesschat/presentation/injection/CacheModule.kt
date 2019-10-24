package ru.druliks.drumesschat.presentation.injection

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.druliks.drumesschat.cache.AccountCacheImpl
import ru.druliks.drumesschat.cache.SharedPrefsManager
import ru.druliks.drumesschat.data.account.AccountCache
import javax.inject.Singleton

//Класс-модуль для предоставления зависимостей SharedPreferences и AccountCache.
@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideAccountCache(prefsManager: SharedPrefsManager): AccountCache = AccountCacheImpl(prefsManager)
}