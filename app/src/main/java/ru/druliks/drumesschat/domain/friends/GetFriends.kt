package ru.druliks.drumesschat.domain.friends

import ru.druliks.drumesschat.domain.interactor.UseCase
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject

//UseCase для получения списка друзей
class GetFriends @Inject constructor(
    private val friendsRepository: FriendsRepository
):UseCase<List<FriendEntity>,None>() {
    override suspend fun run(params: None)=friendsRepository.getFriends()
}