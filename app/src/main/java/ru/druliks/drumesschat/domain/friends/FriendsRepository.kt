package ru.druliks.drumesschat.domain.friends

import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Failure
import ru.druliks.drumesschat.domain.type.None

//Интерфейс для взаимодействия с друзьями
interface FriendsRepository {
    fun getFriends(): Either<Failure, List<FriendEntity>>
    fun getFriendRequests(): Either<Failure, List<FriendEntity>>

    fun approveFriendRequest(friendEntity: FriendEntity): Either<Failure, None>
    fun cancelFriendRequest(friendEntity: FriendEntity): Either<Failure, None>

    fun addFriend(email: String): Either<Failure, None>
    fun deleteFriend(friendEntity: FriendEntity): Either<Failure, None>
}