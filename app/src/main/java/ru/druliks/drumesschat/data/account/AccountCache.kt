package ru.druliks.drumesschat.data.account

import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Exception.Failure
import ru.druliks.drumesschat.domain.type.None

//Интерфейс функций для взаимодействия с аккаунтом в локальной БД
interface AccountCache {
    fun getToken(): Either<Failure, String>
    fun saveToken(token: String): Either<Failure, None>
}