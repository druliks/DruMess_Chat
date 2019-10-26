package ru.druliks.drumesschat.ui.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_friend.view.*
import ru.druliks.drumesschat.R
import ru.druliks.drumesschat.databinding.ItemFriendBinding
import ru.druliks.drumesschat.domain.friends.FriendEntity
import ru.druliks.drumesschat.ui.core.BaseAdapter
import ru.druliks.drumesschat.ui.core.GlideHelper

open class FriendsAdapter : BaseAdapter<FriendsAdapter.FriendViewHolder>() {

    override fun createHolder(parent: ViewGroup): FriendViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendBinding.inflate(layoutInflater, parent, false)
        return FriendViewHolder(binding)
    }

    class FriendViewHolder(val binding: ItemFriendBinding) : BaseViewHolder(binding.root) {
        init {
            binding.btnRemove.setOnClickListener {
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