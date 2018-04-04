package com.client.haole.mollbiz.adapter

import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.client.haole.mollbiz.R
import com.client.haole.mollbiz.application.App
import com.client.haole.mollbiz.model.VideoMol
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_video.*

class VideoItemListAdapter(private val items: List<VideoMol>, var onClick:(Int) -> Unit)
    :RecyclerView.Adapter<VideoItemListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_video, parent, false)
                .let {
                    ViewHolder(it)
                }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindData(items[position])
        holder.linear_layout_video.setOnClickListener {
            onClick(position)
        }
    }

    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindData(item: VideoMol) {
            with(item) {
                text_view_title.text = item.name
                Glide.with(App.instance)
                        .load(item.bgPicture)
                        .crossFade()
                        .into(image_view_video_thumb)
            }
        }
    }
}