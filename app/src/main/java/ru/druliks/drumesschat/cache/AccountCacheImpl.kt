package ru.druliks.drumesschat.cache

import ru.druliks.drumesschat.data.account.AccountCache
import ru.druliks.drumesschat.domain.account.AccountEntity
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Failure
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject

//Класс для взаимодействия с аккаунтом в локальной БД
class AccountCacheImpl @Inject constructor(private val prefsManager: SharedPrefsManager):AccountCache{
    override fun saveToken(token: String): Either<Failure, None> {
        return prefsManager.saveToken(token)
    }

    override fun getToken(): Either<Failure, String> {
        return prefsManager.getToken()
    }

    override fun logout(): Either<Failure, None> {
        return prefsManager.removeAccount()
    }

    override fun getCurrentAccount(): Either<Failure, AccountEntity> {
        return prefsManager.getAccount()
    }

    override fun saveAccount(account: AccountEntity): Either<Failure, None> {
        return prefsManager.saveAccount(account)
    }
}