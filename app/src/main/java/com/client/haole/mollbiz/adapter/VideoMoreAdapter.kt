package com.client.haole.mollbiz.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.model.VideoMoreMol
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rank.*

class VideoMoreAdapter(private val items: List<VideoMoreMol>, val onClick:(Int) -> Unit)
    :RecyclerView.Adapter<VideoMoreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_rank, parent, false)
                .let {
                    ViewHolder(it)
                }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindData(items[position])
        holder!!.layout.setOnClickListener {
            onClick(position)
        }
    }

    class ViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(item: VideoMoreMol) {
            with(item) {

            }
        }

    }
}