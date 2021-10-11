package com.example.myteam.team

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Player
import com.example.myteam.databinding.ItemRequestBinding
import com.example.myteam.utils.adapter.GenericAdapter

class RequestViewHolder(itemRvBinding: ItemRequestBinding) :
    RecyclerView.ViewHolder(itemRvBinding.root),
    GenericAdapter.Binder<Player> {

    var binding: ItemRequestBinding = itemRvBinding

    override fun bind(data: Player) {
        with(binding) {
            textView.text = data.name
            textView2.text = data.age.toString()
            textView3.text = data.position.toString()
        }
    }
}
