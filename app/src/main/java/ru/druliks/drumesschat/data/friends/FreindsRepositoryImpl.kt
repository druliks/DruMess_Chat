package ru.druliks.drumesschat.data.friends

import ru.druliks.drumesschat.data.account.AccountCache
import ru.druliks.drumesschat.domain.friends.FriendEntity
import ru.druliks.drumesschat.domain.friends.FriendsRepository
import ru.druliks.drumesschat.domain.type.Either
import ru.druliks.drumesschat.domain.type.Failure
import ru.druliks.drumesschat.domain.type.None
import ru.druliks.drumesschat.domain.type.flatMap

//Реализация интерфеса репозитория для взаимодействия с друзьями
class FreindsRepositoryImpl (
    private val accountCache: AccountCache,
    private val friendsRemote: FriendsRemote
) :FriendsRepository {
    override fun getFriends(): Either<Failure, List<FriendEntity>> {
        return accountCache.getCurrentAccount()
            .flatMap { friendsRemote.getFriends(it.id,it.token) }
    }

    override fun getFriendRequests(): Either<Failure, List<FriendEntity>> {
        return accountCache.getCurrentAccount()
            .flatMap { friendsRemote.getFriendRequests(it.id,it.token) }
    }

    override fun approveFriendRequest(friendEntity: FriendEntity): Either<Failure, None> {
        return accountCache.getCurrentAccount()
            .flatMap { friendsRemote.approveFriendRequest(it.id,friendEntity.id,friendEntity.friendsId,it.token) }
    }

    override fun cancelFriendRequest(friendEntity: FriendEntity): Either<Failure, None> {
        return accountCache.getCurrentAccount()
            .flatMap { friendsRemote.cancelFriendRequest(it.id,friendEntity.id,friendEntity.friendsId,it.token) }
    }

    override fun addFriend(email: String): Either<Failure, None> {
        return accountCache.getCurrentAccount()
            .flatMap { friendsRemote.addFriend(email,it.id,it.token) }
    }

    override fun deleteFriend(friendEntity: FriendEntity): Either<Failure, None> {
        return accountCache.getCurrentAccount()
            .flatMap { friendsRemote.deleteFriend(it.id,friendEntity.id,friendEntity.friendsId,it.token) }
    }
}