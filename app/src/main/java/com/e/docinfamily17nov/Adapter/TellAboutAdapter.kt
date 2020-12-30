package com.e.docinfamily17nov.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.docinfamily17nov.Activities.DescriptionFormActivity
import com.e.docinfamily17nov.Model.MedicalOptionModel
import com.e.docinfamily17nov.R
import com.squareup.picasso.Picasso


class TellAboutAdapter (
    internal var activity: Activity,
    internal var item_list: ArrayList<MedicalOptionModel>,

    ) :  RecyclerView.Adapter<TellAboutAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(viewgroup.getContext())
            .inflate(R.layout.layout_tell_item, viewgroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rl_background.setImageResource( item_list.get(position).imageback)
//        Picasso.get().load(
//            item_list.get(position).imageback.toString()
//        ).fit().into(holder.rl_background)
        Picasso.get().load(
            item_list.get(position).imagef
        ).fit().into(holder.iv_logo)
        holder.tv_title.setText(item_list.get(position).title.toString())
        holder.rl_background.setOnClickListener{
            val intent=Intent(activity,DescriptionFormActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return item_list.size
    }
    class ViewHolder(viewitem: View) : RecyclerView.ViewHolder(viewitem) {

        var tv_title: TextView
        var iv_logo: ImageView
        var rl_background:ImageView

        init {
            tv_title = viewitem.findViewById(R.id.tv_name)
            iv_logo = viewitem.findViewById(R.id.iv_logo)
            rl_background=viewitem.findViewById(R.id.rl_background)
        }
    }
}