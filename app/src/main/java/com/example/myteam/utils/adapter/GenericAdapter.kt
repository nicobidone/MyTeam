package com.example.myteam.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class GenericAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    var listItems: List<T>

    constructor(listItems: List<T>) {
        this.listItems = listItems
    }

    constructor() {
        listItems = emptyList()
    }

    fun setItems(listItems: List<T>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false),
            viewType
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(listItems[position])
    }

    override fun getItemCount(): Int = listItems.size

    override fun getItemViewType(position: Int): Int = getLayoutId(position, listItems[position])

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    fun deleteItem(itemIndex: Int) {
        (listItems as MutableList<T>).removeAt(itemIndex)
    }

    internal interface Binder<T> {
        fun bind(data: T)
    }
}

/*
        val myAdapter = object : GenericAdapter<Player>(dialogResult) {
            override fun getLayoutId(position: Int, obj: Player): Int = R.layout.item_request
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
                RequestViewHolder(ItemRequestBinding.inflate(layoutInflater))
        }
        binding.rvRequestPlayers.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = myAdapter
            visibility = View.VISIBLE
        }
 */
