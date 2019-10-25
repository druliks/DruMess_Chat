package ru.druliks.drumesschat.domain.friends

import ru.druliks.drumesschat.domain.interactor.UseCase
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject

//UseCase для принятия приглашения дружбы
class ApproveFriendRequest @Inject constructor(
    private val friendsRepository: FriendsRepository
) :UseCase<None,FriendEntity>() {
    override suspend fun run(params: FriendEntity) =friendsRepository.approveFriendRequest(params)

}