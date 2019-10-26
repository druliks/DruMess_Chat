package ru.druliks.drumesschat.data.friends

import ru.druliks.drumesschat.domain.friends.FriendEntity

interface FriendsCache {
    fun saveFriend(entity: FriendEntity)

    fun getFriend(key: Long): FriendEntity?

    fun getFriends(): List<FriendEntity>

    fun getFriendRequests(): List<FriendEntity>

    fun removeFriendEntity(key: Long)
}