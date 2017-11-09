package com.leiyun.elemalist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter

/**
 * 类名：TestAdapter
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2017-11-09 16:12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class TestAdapter(private var isHome: Boolean) : RecyclerView.Adapter<ViewHolder>(), StickyRecyclerHeadersAdapter<HeaderViewHolder> {


    override fun onBindHeaderViewHolder(holder: HeaderViewHolder?, position: Int) {

    }

    override fun getHeaderId(position: Int): Long = if (position == 0 && isHome) -1 else 1

    override fun onCreateHeaderViewHolder(parent: ViewGroup?): HeaderViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(viewType, parent, false)
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && isHome) R.layout.item_recommend else R.layout.item_test
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    override fun getItemCount(): Int = 30

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}

class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}