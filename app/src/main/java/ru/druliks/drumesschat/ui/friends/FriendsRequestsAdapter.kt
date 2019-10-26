package ru.druliks.drumesschat.ui.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_friend_request.view.*
import ru.druliks.drumesschat.R
import ru.druliks.drumesschat.databinding.ItemFriendRequestBinding
import ru.druliks.drumesschat.domain.friends.FriendEntity
import ru.druliks.drumesschat.ui.core.BaseAdapter
import ru.druliks.drumesschat.ui.core.GlideHelper

open class FriendRequestsAdapter : BaseAdapter<FriendRequestsAdapter.FriendRequestViewHolder>() {

    override fun createHolder(parent: ViewGroup): FriendRequestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendRequestBinding.inflate(layoutInflater)
        return FriendRequestViewHolder(binding)
    }

    class FriendRequestViewHolder(val binding: ItemFriendRequestBinding) : BaseViewHolder(binding.root) {

        init {
            binding.btnApprove.setOnClickListener {
                onClick?.onClick(item, it)
            }
            binding.btnCancel.setOnClickListener {
                onClick?.onClick(item, it)
            }
        }

        override fun onBind(item: Any) {
            (item as? FriendEntity)?.let {
                binding.friend = it
            }
        }
    }
}