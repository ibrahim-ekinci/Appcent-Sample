package com.gloorystudio.appcent_sample.ui.game_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.data.remote.models.GameListEntry
import com.gloorystudio.appcent_sample.databinding.ItemGameListBinding
import com.gloorystudio.appcent_sample.databinding.ItemViewPagerBinding

class ViewPagerAdapter(private val gameList:ArrayList<GameListEntry>) : PagerAdapter() {

    private var imageViewOnClick: ((GameListEntry, View) -> Unit)? = null
    fun onClickItem(actionFragmentList: (GameListEntry, View) -> Unit) {
        this.imageViewOnClick = actionFragmentList
    }

    override fun getCount(): Int = gameList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view =  DataBindingUtil.inflate<ItemViewPagerBinding>(
            inflater,
            R.layout.item_view_pager,
            container,
            false
        )
        view.game = gameList[position]
        view.mcvContainer.setOnClickListener {
            imageViewOnClick?.invoke(gameList[position],it)
        }

        val viewPager = container as ViewPager
        viewPager.addView(view.root,0)
        return view.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }

    fun updateGameList(newList: List<GameListEntry>) {
        gameList.clear()
        gameList.addAll(newList)
        notifyDataSetChanged()
    }
}