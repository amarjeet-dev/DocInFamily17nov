
package com.e.docinfamily17nov.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.e.docinfamily17nov.Activities.BaseActivity
import com.e.docinfamily17nov.Fragment.FragmentNotesDetail
import com.e.docinfamily17nov.R


class NotesAdapter(
    internal var activity: Activity,

    var item_list: ArrayList<Int> = ArrayList()
) :  RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(viewgroup.getContext())
            .inflate(R.layout.layout_notes_item, viewgroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
holder.rl_item.setOnClickListener {
    (activity as BaseActivity).docAppPresenter!!.navigateTo(FragmentNotesDetail.newInstance())
}
    }

    override fun getItemCount(): Int {
       return item_list.size
    }
    class ViewHolder(viewitem: View) : RecyclerView.ViewHolder(viewitem) {

       lateinit var rl_item:RelativeLayout

        init {
            rl_item=viewitem.findViewById(R.id.view_forground)
        }
    }

    fun removeItem(position: Int) {
        item_list.remove(position)
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position)
    }


}