package com.puitika.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.puitika.R
import com.puitika.data.remote.response.RegionDetail

class RegionAdapter(private val context: Context, private val regionList: List<DetailRegion>) :
    RecyclerView.Adapter<RegionAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.region_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(regionList[position].imageURL).into(holder.imageView)
        holder.tvRegion.text = regionList[position].name

        holder.itemView.setOnClickListener {
            onItemClickListener?.onClick(holder.imageView, regionList[position])
        }
    }

    override fun getItemCount(): Int {
        return regionList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.list_item_image)
        val tvRegion: TextView = itemView.findViewById(R.id.tv_region)
    }

    interface OnItemClickListener {
        fun onClick(imageView: ImageView, region: DetailRegion)
    }
}
