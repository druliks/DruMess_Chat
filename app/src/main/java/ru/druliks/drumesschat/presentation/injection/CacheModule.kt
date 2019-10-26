package ru.druliks.drumesschat.presentation.injection

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.druliks.drumesschat.cache.AccountCacheImpl
import ru.druliks.drumesschat.cache.ChatDatabase
import ru.druliks.drumesschat.cache.SharedPrefsManager
import ru.druliks.drumesschat.data.account.AccountCache
import ru.druliks.drumesschat.data.friends.FriendsCache
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

    @Provides
    @Singleton
    fun provideChatDatabase(context: Context): ChatDatabase {
        return ChatDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideFriendsCache(chatDatabase: ChatDatabase): FriendsCache {
        return chatDatabase.friendsDao
    }
}