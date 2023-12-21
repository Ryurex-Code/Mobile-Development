package com.puitika.ui.main.scan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.puitika.R
import com.puitika.data.remote.response.PrediksiItem

class ScanModelAdapter(private val context: Context, private val scanModelList: List<PrediksiItem>) :
    RecyclerView.Adapter<ScanModelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.scan_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val scanModel = scanModelList[position]

        // Set data to views
        holder.tvPercent.text = "${scanModel.percent.substring(0,4)}%"
        holder.tvRegion.text = scanModel.region
    }

    override fun getItemCount(): Int {
        return scanModelList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPercent: TextView = itemView.findViewById(R.id.tv_percent_scan)
        val tvRegion: TextView = itemView.findViewById(R.id.tv_region_scan)
        val cardView: CircularRevealCardView = itemView.findViewById(R.id.carousel_item_container)
    }
}
