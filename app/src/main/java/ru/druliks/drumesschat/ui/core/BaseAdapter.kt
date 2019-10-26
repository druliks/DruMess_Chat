package ru.druliks.drumesschat.ui.core


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


//Базовый класс адаптера для выделения поведения списков
abstract class BaseAdapter<VH : BaseAdapter.BaseViewHolder> : RecyclerView.Adapter<VH>() {

    var items: ArrayList<Any> = ArrayList()

    var onClick: OnClick? = null


    abstract fun createHolder(parent: ViewGroup): VH


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))

        holder.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return createHolder(parent)
    }


    fun getItem(position: Int): Any {
        return items[position]
    }


    fun add(newItem: Any) {
        items.add(newItem)
    }

    fun add(newItems: List<Any>) {
        items.addAll(newItems)
    }

    fun clear() {
        items.clear()
    }


    fun setOnClick(click: (Any?, View) -> Unit, longClick: (Any?, View) -> Unit = {_,_ ->}) {
        onClick = object : OnClick {
            override fun onClick(item: Any?, view: View) {
                click(item, view)
            }

            override fun onLongClick(item: Any?, view: View) {
                longClick(item, view)
            }
        }
    }

    interface OnClick {
        fun onClick(item: Any?, view: View)
        fun onLongClick(item: Any?, view: View)
    }

    abstract class BaseViewHolder(protected val view: View) : RecyclerView.ViewHolder(view) {
        var onClick: OnClick? = null
        var item: Any? = null

        init {
            view.setOnClickListener {
                onClick?.onClick(item, it)
            }
            view.setOnLongClickListener {
                onClick?.onLongClick(item, it)
                true
            }
        }

        protected abstract fun onBind(item: Any)

        fun bind(item: Any) {
            this.item = item

            onBind(item)
        }

    }
}