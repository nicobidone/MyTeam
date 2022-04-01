package com.example.myteam.team

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.PlayerEntity
import com.example.myteam.R
import com.example.myteam.databinding.ItemTeamPlayerBinding
import java.util.Collections

class TeamPlayerAdapter(
    private val dragStartListener: OnStartDragListener,
    private val reordered: (List<PlayerEntity>) -> Unit,
    private val action: (PlayerEntity) -> Unit,
    private val onSwiped: (PlayerEntity) -> Unit
) : RecyclerView.Adapter<TeamPlayerViewHolder>(), ItemTouchHelperAdapter {

    private var list: MutableList<PlayerEntity> = mutableListOf()

    fun setData(playerList: List<PlayerEntity>) {
        list = playerList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TeamPlayerViewHolder, position: Int) {
        holder.bind(list[position], action)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(list, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        onSwiped(list[position])
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamPlayerViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_team_player, parent, false),
        reordered,
        list,
        dragStartListener
    )
}

class TeamPlayerViewHolder(
    itemRvBinding: View,
    private val reordered: (List<PlayerEntity>) -> Unit,
    private val list: List<PlayerEntity>,
    private val dragStartListener: OnStartDragListener? = null
) : RecyclerView.ViewHolder(itemRvBinding), ItemTouchHelperViewHolder {

    fun bind(data: PlayerEntity, action: (PlayerEntity) -> Unit) {
        with(ItemTeamPlayerBinding.bind(itemView)) {
            tvItemTeamPlayerName.text = data.name
            tvItemTeamPlayerAge.text = data.age.toString()
            tvItemTeamPlayerPosition.text = data.position.toString()
            mcItemTeamPlayer.setOnClickListener { action(data) }
            ivTeamPlayerReorder.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    dragStartListener?.onStartDrag(this@TeamPlayerViewHolder)
                }
                false
            }
        }
    }

    override fun onItemSelected() {}

    override fun onItemClear() {
        reordered(list)
    }
}

class HelperCallback(private val adapter: ItemTouchHelperAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView, source: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        adapter.onItemMove(source.adapterPosition, target.adapterPosition)
        return true
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        (viewHolder as ItemTouchHelperViewHolder).onItemClear()
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemDismiss(viewHolder.adapterPosition)
    }
}

interface ItemTouchHelperViewHolder {
    fun onItemSelected()
    fun onItemClear()
}

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    fun onItemDismiss(position: Int)
}

interface OnStartDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder?)
}
