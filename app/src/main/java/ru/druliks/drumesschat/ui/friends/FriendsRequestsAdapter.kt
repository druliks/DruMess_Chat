package ru.druliks.drumesschat.ui.friends

import android.view.View
import kotlinx.android.synthetic.main.item_friend_request.view.*
import ru.druliks.drumesschat.R
import ru.druliks.drumesschat.domain.friends.FriendEntity
import ru.druliks.drumesschat.ui.core.BaseAdapter

open class FriendRequestsAdapter : BaseAdapter<FriendRequestsAdapter.FriendRequestViewHolder>() {
    override val layoutRes = R.layout.item_friend_request

    override fun createHolder(view: View, viewType: Int): FriendRequestViewHolder {
        return FriendRequestViewHolder(view)
    }

    class FriendRequestViewHolder(view: View) : BaseViewHolder(view) {

        init {
            view.btnApprove.setOnClickListener {
                onClick?.onClick(item, it)
            }
            view.btnCancel.setOnClickListener {
                onClick?.onClick(item, it)
            }
        }

        override fun onBind(item: Any) {


            (item as? FriendEntity)?.let {
                view.tvName.text = it.name
            }

        }
    }
}