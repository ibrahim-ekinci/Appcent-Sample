package com.gloorystudio.appcent_sample.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.data.models.GameListEntry
import com.gloorystudio.appcent_sample.databinding.ItemGameListBinding
import kotlin.collections.ArrayList

class GameListFavoriteAdapter(private var gameList: ArrayList<GameListEntry>) :
    RecyclerView.Adapter<GameListFavoriteAdapter.GameListViewHolder>() {

    private var containerCardViewOnClick: ((GameListEntry, View) -> Unit)? = null
    fun onClickItem(actionFragmentList: (GameListEntry, View) -> Unit) {
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

    fun updateGameList(newList: List<GameListEntry>) {
        val diffCallBack = GameListDiffCallBack(gameList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        diffResult.dispatchUpdatesTo(this)
        this.gameList = newList as ArrayList<GameListEntry>
    }

    inner class GameListViewHolder(var binding: ItemGameListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.containerCardView.setOnClickListener {
                containerCardViewOnClick?.invoke(gameList[adapterPosition], it)
            }
        }

        fun bind(game: GameListEntry) {
            binding.game = game
        }
    }
}