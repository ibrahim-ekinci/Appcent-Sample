package com.gloorystudio.appcent_sample.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.gloorystudio.appcent_sample.data.models.GameListEntry

open class GameListDiffCallBack(
    private val oldGameList: List<GameListEntry>,
    private val newGameList: List<GameListEntry>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldGameList.size
    override fun getNewListSize(): Int = newGameList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldGameList[oldItemPosition].id == newGameList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldGameList[oldItemPosition] == newGameList[newItemPosition]
    }
}