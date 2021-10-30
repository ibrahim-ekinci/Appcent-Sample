package com.gloorystudio.appcent_sample.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.data.models.GameListEntry
import com.gloorystudio.appcent_sample.databinding.ItemGameListBinding
import java.util.*
import kotlin.collections.ArrayList

class GameListAdapter(private var gameList: ArrayList<GameListEntry>) :
    RecyclerView.Adapter<GameListAdapter.GameListViewHolder>(),
    Filterable {

    var defaultGameList = ArrayList<GameListEntry>()

    init {
        defaultGameList.addAll(gameList)
    }

    private var containerCardViewOnClick: ((GameListEntry, View) -> Unit)? = null
    fun onClickItem(actionFragmentList: (GameListEntry, View) -> Unit) {
        this.containerCardViewOnClick = actionFragmentList
    }
    private var onAfterSearch: ((isListEmpty:Boolean) -> Unit)? = null
    fun onAfterSearch(onAfterSearch: (isListEmpty:Boolean) -> Unit) {
        this.onAfterSearch = onAfterSearch
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isNotEmpty()) {
                    val resultList = ArrayList<GameListEntry>()
                    for (row in defaultGameList) {
                        if (row.name.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    gameList = resultList
                } else {
                    if (defaultGameList.size > 3)
                        gameList =
                            defaultGameList.takeLast(defaultGameList.size - 3) as ArrayList<GameListEntry>
                }
                val filterResults = FilterResults()
                filterResults.values = gameList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                results?.values?.let {
                    gameList = results.values as ArrayList<GameListEntry>
                    notifyDataSetChanged()
                    onAfterSearch?.invoke(gameList.isEmpty())
                }
            }
        }
    }

    fun clearFilter() = filter.filter("")

    fun updateGameList(newList: List<GameListEntry>) {
        if (newList.size > 3) {
            val diffCallBack = GameListDiffCallBack(gameList, newList)
            val diffResult = DiffUtil.calculateDiff(diffCallBack)
            diffResult.dispatchUpdatesTo(this)
            val croppedNewList = newList.takeLast(newList.size - 3)
            this.gameList = croppedNewList as ArrayList<GameListEntry>
            this.defaultGameList = newList as ArrayList<GameListEntry>
        }
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