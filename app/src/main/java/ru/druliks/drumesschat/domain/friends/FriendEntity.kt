package ru.druliks.drumesschat.domain.friends

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//Модельный класс содержит данные друга
@Entity(tableName = "friends_table")
data class FriendEntity (
    @PrimaryKey
    @SerializedName("user_id")
    var id:Long,
    var name: String,
    var email: String,
    @ColumnInfo(name="friends_id")
    @SerializedName("friends_id")
    var friendsId:Long,
    var status:String,
    var image:String,
    @ColumnInfo(name = "is_request")
    var isRequest:Int=0
)