package com.e.docinfamily17nov.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.e.docinfamily17nov.Activities.AppointmentDetailActivity
import com.e.docinfamily17nov.R


class AppointmentAdapter (
    internal var activity: Activity

    ) :  RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(viewgroup.getContext())
            .inflate(R.layout.layout_appointmentitem, viewgroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
holder.rl_item.setOnClickListener {
    val intent=Intent(activity,AppointmentDetailActivity::class.java)
    activity.startActivity(intent)
}
    }

    override fun getItemCount(): Int {
       return 3
    }
    class ViewHolder(viewitem: View) : RecyclerView.ViewHolder(viewitem) {

       lateinit var rl_item:RelativeLayout

        init {
            rl_item=viewitem.findViewById(R.id.rl_item)
        }
    }
}