package ru.druliks.drumesschat.remote.friends

import com.google.gson.annotations.SerializedName
import ru.druliks.drumesschat.domain.friends.FriendEntity
import ru.druliks.drumesschat.remote.core.BaseResponse

//POJO-класс для хранения ответа сервера при получении списка дружбы
class GetFriendRequestsResponse (
    success:Int,
    message:String,
    @SerializedName("friend_requests")
    val friendsRequests:List<FriendEntity>
):BaseResponse(success, message)