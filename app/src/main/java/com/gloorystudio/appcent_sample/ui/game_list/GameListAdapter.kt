package com.gloorystudio.appcent_sample.ui.game_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.data.remote.models.GameListEntry
import com.gloorystudio.appcent_sample.databinding.ItemGameListBinding
import java.util.*
import kotlin.collections.ArrayList

class GameListAdapter(private val gameList: ArrayList<GameListEntry>) :
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
        holder.bind(defaultGameList[position])

    override fun getItemCount(): Int = defaultGameList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isNotEmpty()) {
                    val resultList = ArrayList<GameListEntry>()
                    for (row in gameList) {
                        if (row.name.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    defaultGameList = resultList
                } else {
                    if (gameList.size > 3)
                        defaultGameList =
                            gameList.takeLast(gameList.size - 3) as ArrayList<GameListEntry>
                }
                val filterResults = FilterResults()
                filterResults.values = defaultGameList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                defaultGameList = results?.values as ArrayList<GameListEntry>
                notifyDataSetChanged()
            }
        }
    }

    fun clearFilter() = filter.filter("")

    fun updateGameList(newList: List<GameListEntry>) {
        gameList.clear()
        gameList.addAll(newList)
        defaultGameList.clear()
        if (newList.size > 3)
            defaultGameList.addAll(newList.takeLast(newList.size - 3))
        notifyDataSetChanged()
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