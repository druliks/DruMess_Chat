package ru.druliks.drumesschat.domain.account

import ru.druliks.drumesschat.domain.interactor.UseCase
import javax.inject.Inject


//Use-case для авторизации
class Login @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<AccountEntity, Login.Params>() {

    override suspend fun run(params: Params) = accountRepository.login(params.email, params.password)

    data class Params(val email: String, val password: String)
}