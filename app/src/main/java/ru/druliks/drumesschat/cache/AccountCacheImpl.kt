package ru.druliks.drumesschat.cache

import ru.druliks.drumesschat.data.account.AccountCache
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Exception.Failure
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject

//Класс для взаимодействия с аккаунтом в локальной БД
class AccountCacheImpl @Inject constructor(private val prefsManager: SharedPrefsManager):AccountCache{
    override fun getToken(): Either<Failure, String> {
        return prefsManager.getToken()
    }

    override fun saveToken(token: String): Either<Failure, None> {
        return prefsManager.saveToken(token)
    }
}