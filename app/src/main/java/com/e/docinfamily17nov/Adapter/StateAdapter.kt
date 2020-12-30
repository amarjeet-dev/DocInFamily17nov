package com.e.docinfamily17nov.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.docinfamily17nov.Model.StatesData
import com.e.docinfamily17nov.R

class StateAdapter(internal var activity: Activity,
                   internal var item_list: List<StatesData>,

                   ) :  RecyclerView.Adapter<StateAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(viewgroup.getContext())
            .inflate(R.layout.layout_country_item, viewgroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
holder.tv_country.setText(item_list.get(position).getTitle())
    }
    override fun getItemCount(): Int {
        return item_list.size
    }
    class ViewHolder(viewitem: View) : RecyclerView.ViewHolder(viewitem) {

        var tv_country: TextView


        init {
            tv_country = viewitem.findViewById(R.id.tv_country)

        }
    }
}