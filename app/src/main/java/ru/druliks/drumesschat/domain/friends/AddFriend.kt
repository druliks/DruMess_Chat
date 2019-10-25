package ru.druliks.drumesschat.domain.friends

import ru.druliks.drumesschat.domain.interactor.UseCase
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Failure
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject

//UseCase для отправления приглашения дружбы
class AddFriend @Inject constructor(
    private val friendsRepository: FriendsRepository
): UseCase<None, AddFriend.Params>(){
    override suspend fun run(params: Params): Either<Failure, None> =friendsRepository.addFriend(params.email)

    data class Params(val email:String)
}