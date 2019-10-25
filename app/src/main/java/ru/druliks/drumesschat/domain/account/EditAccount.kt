package ru.druliks.drumesschat.domain.account

import ru.druliks.drumesschat.domain.interactor.UseCase
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Failure
import javax.inject.Inject

//UseCase для редактирования аккаунта
class EditAccount @Inject constructor(
    private val repository: AccountRepository
) :UseCase<AccountEntity, AccountEntity>()
{
    override suspend fun run(params: AccountEntity): Either<Failure, AccountEntity> {
        return repository.editAccount(params)
    }
}