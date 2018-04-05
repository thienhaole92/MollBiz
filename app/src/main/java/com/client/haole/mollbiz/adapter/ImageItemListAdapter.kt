package com.client.haole.mollbiz.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.application.App
import com.client.haole.mollbiz.model.AndMol
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_image.*

class ImageItemListAdapter(private val items: List<AndMol>, val onClick: (Int) -> Unit)
    :RecyclerView.Adapter<ImageItemListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_image, parent, false)
                .let {
                    ViewHolder(it)
                }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindData(items[position])
        holder!!.linear_layout_image.setOnClickListener {
            onClick(position)
        }
    }

    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindData(item: AndMol) {
            with(item) {
                Glide.with(App.instance)
                        .load(item.url)
                        .crossFade()
                        .into(image_view_thumb)

            }
        }

    }
}