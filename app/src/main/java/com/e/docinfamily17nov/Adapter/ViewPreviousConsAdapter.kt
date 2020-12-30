package com.e.docinfamily17nov.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.docinfamily17nov.R


class ViewPreviousConsAdapter (
    internal var activity: Activity,
//    internal var item_list: ArrayList<MedicalOptionModel>,

    ) :  RecyclerView.Adapter<ViewPreviousConsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(viewgroup.getContext())
            .inflate(R.layout.layout_previous_item, viewgroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 3
    }
    class ViewHolder(viewitem: View) : RecyclerView.ViewHolder(viewitem) {

        var tv_title: TextView
        var tv_description: TextView
        var imageView1:ImageView

        init {
            tv_description=viewitem.findViewById(R.id.tv_description)
            tv_title = viewitem.findViewById(R.id.tv_title)
            imageView1=viewitem.findViewById(R.id.imageView1)
        }
    }
}