package ru.druliks.drumesschat.data.friends

import ru.druliks.drumesschat.domain.friends.FriendEntity
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Failure
import ru.druliks.drumesschat.domain.type.None

//Интерфейс функций для взаимодействия с друзьями на сервере
interface FriendsRemote {
    fun getFriends(userId: Long, token: String): Either<Failure, List<FriendEntity>>
    fun getFriendRequests(userId: Long, token: String): Either<Failure, List<FriendEntity>>

    fun approveFriendRequest(userId: Long, requestUserId: Long, friendsId: Long, token: String): Either<Failure, None>
    fun cancelFriendRequest(userId: Long, requestUserId: Long, friendsId: Long, token: String): Either<Failure, None>

    fun addFriend(email: String, userId: Long, token: String): Either<Failure, None>
    fun deleteFriend(userId: Long, requestUserId: Long, friendsId: Long, token: String): Either<Failure, None>
}