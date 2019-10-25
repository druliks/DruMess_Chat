package ru.druliks.drumesschat.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.druliks.drumesschat.domain.friends.*
import ru.druliks.drumesschat.domain.type.None
import javax.inject.Inject

//ViewModel для взаимодействия с друзьями
class FriendsViewModel @Inject constructor(
    val getFriendsUseCase: GetFriends,
    val deleteFriendUseCase: DeleteFriend,
    val addFriendUseCase: AddFriend,
    val getFriendRequestsUseCase: GetFriendRequests,
    val approveFriendRequestUseCase: ApproveFriendRequest,
    val cancelFriendRequestUseCase: CancelFriendRequest
) :BaseViewModel() {
    var friendsData: MutableLiveData<List<FriendEntity>> = MutableLiveData()
    var friendRequestsData: MutableLiveData<List<FriendEntity>> = MutableLiveData()
    var deleteFriendData: MutableLiveData<None> = MutableLiveData()
    var addFriendData: MutableLiveData<None> = MutableLiveData()
    var approveFriendData: MutableLiveData<None> = MutableLiveData()
    var cancelFriendData: MutableLiveData<None> = MutableLiveData()

    fun getFriends() {
        getFriendsUseCase(None()) { it.either(::handleFailure, ::handleFriends) }
    }

    fun getFriendRequests() {
        getFriendRequestsUseCase(None()) { it.either(::handleFailure, ::handleFriendRequests) }
    }

    fun deleteFriend(friendEntity: FriendEntity) {
        deleteFriendUseCase(friendEntity) { it.either(::handleFailure, ::handleDeleteFriend) }
    }

    fun addFriend(email: String) {
        addFriendUseCase(AddFriend.Params(email)) { it.either(::handleFailure, ::handleAddFriend) }
    }

    fun approveFriend(friendEntity: FriendEntity) {
        approveFriendRequestUseCase(friendEntity) { it.either(::handleFailure, ::handleApproveFriend) }
    }

    fun cancelFriend(friendEntity: FriendEntity) {
        cancelFriendRequestUseCase(friendEntity) { it.either(::handleFailure, ::handleCancelFriend) }
    }


    private fun handleFriends(friends: List<FriendEntity>) {
        friendsData.value = friends
    }

    private fun handleFriendRequests(friends: List<FriendEntity>) {
        friendRequestsData.value = friends
    }

    private fun handleDeleteFriend(none: None?) {
        deleteFriendData.value = none
    }

    private fun handleAddFriend(none: None?) {
        addFriendData.value = none
    }

    private fun handleApproveFriend(none: None?) {
        approveFriendData.value = none
    }

    private fun handleCancelFriend(none: None?) {
        cancelFriendData.value = none
    }


    override fun onCleared() {
        super.onCleared()
        getFriendsUseCase.unsubscribe()
        getFriendRequestsUseCase.unsubscribe()
        deleteFriendUseCase.unsubscribe()
        addFriendUseCase.unsubscribe()
        approveFriendRequestUseCase.unsubscribe()
        cancelFriendRequestUseCase.unsubscribe()
    }
}