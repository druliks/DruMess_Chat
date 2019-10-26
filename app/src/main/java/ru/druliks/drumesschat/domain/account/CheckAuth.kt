package ru.druliks.drumesschat.domain.account

import ru.druliks.drumesschat.domain.interactor.UseCase
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Failure
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject


//UseCase для проверки авторизации пользователя
class CheckAuth @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<Boolean, None>() {

    override suspend fun run(params: None): Either<Failure, Boolean> = accountRepository.checkAuth()
}