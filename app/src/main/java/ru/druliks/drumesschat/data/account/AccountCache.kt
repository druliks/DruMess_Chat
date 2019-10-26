package ru.druliks.drumesschat.data.account

import ru.druliks.drumesschat.domain.account.AccountEntity
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Failure
import ru.druliks.drumesschat.domain.type.None

//Интерфейс функций для взаимодействия с аккаунтом в локальной БД
interface AccountCache {
    fun getToken(): Either<Failure, String>
    fun saveToken(token: String): Either<Failure, None>

    fun logout(): Either<Failure, None>

    fun getCurrentAccount(): Either<Failure, AccountEntity>
    fun saveAccount(account: AccountEntity): Either<Failure, None>

    fun checkAuth(): Either<Failure, Boolean>
}