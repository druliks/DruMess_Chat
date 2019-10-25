package ru.druliks.drumesschat.remote.friends

import ru.druliks.drumesschat.domain.friends.FriendEntity
import ru.druliks.drumesschat.remote.core.BaseResponse

//POJO-класс для хранения ответа сервера при получении списка друзей
class GetFriendsResponse(
    success: Int,
    message:String,
    val friends:List<FriendEntity>
):BaseResponse(success, message)