package com.example.myteam.team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.PlayerEntity
import com.example.myteam.R
import com.example.myteam.databinding.ItemTeamPlayerBinding

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

class TeamPlayerAdapter(
    private val list: MutableList<PlayerEntity>,
    private val action: (PlayerEntity) -> Unit
) : RecyclerView.Adapter<TeamPlayerViewHolder>() {

    override fun onBindViewHolder(holder: TeamPlayerViewHolder, position: Int) {
        holder.bind(list[position], action)
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TeamPlayerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team_player, parent, false))

    fun removePlayer(playerEntity: PlayerEntity) {
        list.remove(playerEntity)
        notifyDataSetChanged()
    }
}
