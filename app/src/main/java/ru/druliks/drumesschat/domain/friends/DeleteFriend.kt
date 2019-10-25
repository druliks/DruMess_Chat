package ru.druliks.drumesschat.domain.friends

import ru.druliks.drumesschat.domain.interactor.UseCase
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject

//UseCase для удаления друга
class DeleteFriend @Inject constructor(
    private val friendsRepository: FriendsRepository
):UseCase<None,FriendEntity>() {
    override suspend fun run(params: FriendEntity)=friendsRepository.deleteFriend(params)

}