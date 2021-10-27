package com.gloorystudio.appcent_sample.ui.game_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.data.remote.models.GameListEntry
import com.gloorystudio.appcent_sample.databinding.ItemGameListBinding

class GameListAdapter(private val gameList: ArrayList<GameListEntry>) :
    RecyclerView.Adapter<GameListAdapter.GameListViewHolder>() {

    private var containerCardViewOnClick: ((GameListEntry) -> Unit)? = null
    fun onClickItem(actionFragmentList: (GameListEntry) -> Unit) {
        this.containerCardViewOnClick = actionFragmentList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemGameListBinding>(
            inflater,
            R.layout.item_game_list,
            parent,
            false
        )
        return GameListViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) =
        holder.bind(gameList[position])

    override fun getItemCount(): Int = gameList.size

    inner class GameListViewHolder(var binding: ItemGameListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.containerCardView.setOnClickListener {
                containerCardViewOnClick?.invoke(gameList[adapterPosition])
            }
        }

        fun bind(game: GameListEntry) {
            binding.game = game
        }
    }
}