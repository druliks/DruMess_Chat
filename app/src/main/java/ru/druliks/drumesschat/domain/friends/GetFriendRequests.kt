package ru.druliks.drumesschat.domain.friends

import ru.druliks.drumesschat.domain.interactor.UseCase
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject

//UseCase для полчения списка приглашений дружбы
class GetFriendRequests @Inject constructor(
    private val friendsRepository: FriendsRepository
):UseCase<List<FriendEntity>,Boolean>() {
    override suspend fun run(needFetch: Boolean)=friendsRepository.getFriendRequests(needFetch)

}