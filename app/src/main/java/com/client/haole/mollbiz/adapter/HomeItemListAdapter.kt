package com.client.haole.mollbiz.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.model.AndMol
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home.*

class HomeItemListAdapter(private val items: List<AndMol>, val onClick: (Int) -> Unit)
    :RecyclerView.Adapter<HomeItemListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_home, parent, false)
                .let {
                    ViewHolder(it)
                }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
        holder.linear_layout_item_home.setOnClickListener {
            onClick(position)
        }
    }

    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindData(item: AndMol) {
            with (item) {

            }
        }
    }
}