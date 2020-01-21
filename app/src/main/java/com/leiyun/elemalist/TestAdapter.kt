package com.leiyun.elemalist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jay.widget.StickyHeaders

/**
 * 类名：TestAdapter
 * 作者：Yun.Lei
 * 功能：
 * 创建日期：2017-11-09 16:12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class TestAdapter(private var isHome: Boolean) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaders {

    override fun isStickyHeader(position: Int): Boolean {
        return if (isHome) {
            position == 1
        } else {
            position == 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_recommend -> {
                view.setOnClickListener {
                    Toast.makeText(parent.context, "点击", Toast.LENGTH_SHORT).show()
                }
                RecommendViewHolder(view)
            }
            R.layout.item_header -> {
                view.findViewById<TextView>(R.id.type1).setOnClickListener { Toast.makeText(parent.context, "综合排序", Toast.LENGTH_SHORT).show() }
                view.findViewById<TextView>(R.id.type2).setOnClickListener { Toast.makeText(parent.context, "好评优先", Toast.LENGTH_SHORT).show() }
                view.findViewById<TextView>(R.id.type3).setOnClickListener { Toast.makeText(parent.context, "距离最近", Toast.LENGTH_SHORT).show() }
                view.findViewById<TextView>(R.id.type4).setOnClickListener { Toast.makeText(parent.context, "筛选", Toast.LENGTH_SHORT).show() }
                HeaderViewHolder(view)
            }
            else -> {
                view.setOnClickListener {
                    Toast.makeText(parent.context, it.tag as String, Toast.LENGTH_SHORT).show()
                }
                ViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isHome) {
            when (position) {
                0 -> R.layout.item_recommend
                1 -> R.layout.item_header
                else -> R.layout.item_test
            }
        } else {
            when (position) {
                0 -> R.layout.item_header
                else -> R.layout.item_test
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecommendViewHolder -> {
                //绑定推荐
            }
            is HeaderViewHolder -> {
                //绑定头数据
            }
            else -> {
                holder?.itemView?.tag = "模拟商品描述$position"
                holder?.itemView?.findViewById<TextView>(R.id.contentTv)?.text = "模拟商品描述$position"
                holder?.itemView?.findViewById<ImageView>(R.id.imageView)?.setImageResource(
                        if (position % 2 == 0) {
                            R.mipmap.ic_test2
                        } else {
                            R.mipmap.ic_test1
                        }
                )
            }
        }
    }

    override fun getItemCount(): Int = 30

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
class RecommendViewHolder(view: View) : RecyclerView.ViewHolder(view)
class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view)