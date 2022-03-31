package com.example.myteam.team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.PlayerEntity
import com.example.myteam.R
import com.example.myteam.databinding.ItemTeamPlayerBinding

class TeamPlayerAdapter(
    private var list: MutableList<PlayerEntity> = mutableListOf(),
    private val action: (PlayerEntity) -> Unit,
    private val onSwiped: (PlayerEntity) -> Unit
) : RecyclerView.Adapter<TeamPlayerViewHolder>(), ItemTouchHelperAdapter {

    override fun onBindViewHolder(holder: TeamPlayerViewHolder, position: Int) {
        holder.bind(list[position], action)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean = false

    override fun onItemDismiss(position: Int) {
        onSwiped(list[position])
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TeamPlayerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team_player, parent, false))
}

class TeamPlayerViewHolder(itemRvBinding: View) : RecyclerView.ViewHolder(itemRvBinding) {

    fun bind(data: PlayerEntity, action: (PlayerEntity) -> Unit) {
        with(ItemTeamPlayerBinding.bind(itemView)) {
            tvItemTeamPlayerName.text = data.name
            tvItemTeamPlayerAge.text = data.age.toString()
            tvItemTeamPlayerPosition.text = data.position.toString()
            mcItemTeamPlayer.setOnClickListener { action(data) }
        }
    }
}

class SwipeHelperCallback(private val adapter: TeamPlayerAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView, source: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemDismiss(viewHolder.adapterPosition)
    }
}

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    fun onItemDismiss(position: Int)
}
