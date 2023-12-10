package com.puitika.ui.main.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.puitika.R
import com.puitika.data.dummy.DetailEvent

class EventAdapter(private val context: Context, private val eventList: List<DetailEvent>) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.event_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(eventList[position].eventBannerURL).into(holder.imageView)
        holder.tvEventName.text = eventList[position].eventName


        holder.itemView.setOnClickListener {
            onItemClickListener?.onClick(holder.imageView, eventList[position])
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.event_item_image)
        val tvEventName: TextView = itemView.findViewById(R.id.tv_event_name)
    }

    interface OnItemClickListener {
        fun onClick(imageView: ImageView, event: DetailEvent)
    }
}
