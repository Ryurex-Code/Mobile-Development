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
import com.puitika.data.dummy.DetailCloth

class ClothesAdapter(private val context: Context, private val cloth:List<DetailCloth>): RecyclerView.Adapter<ClothesAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cloth_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(cloth[position].imageUrl).into(holder.ivCloth)
        holder.tvCloth.text = cloth[position].name

        holder.itemView.setOnClickListener {
            onItemClickListener?.onClick(holder.ivCloth, cloth[position])
        }
    }

    override fun getItemCount(): Int {
        return cloth.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCloth: ImageView = itemView.findViewById(R.id.iv_cloth)
        val tvCloth: TextView = itemView.findViewById(R.id.tv_cloth)
    }

    interface OnItemClickListener {
        fun onClick(ivCloth: ImageView, cloth: DetailCloth)
    }
}