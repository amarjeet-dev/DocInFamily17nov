package com.e.docinfamily17nov.Fragment

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.docinfamily17nov.Activities.BaseActivity
import com.e.docinfamily17nov.Adapter.NotesAdapter
import com.e.docinfamily17nov.Utils.RecyclerItemTouchHelper
import com.e.docinfamily17nov.databinding.LayoutNotesBinding


class FragmentNotesList : Fragment() , RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{

    var item_list: ArrayList<Int> = ArrayList()
    lateinit var layoutNotsBinding: LayoutNotesBinding
    lateinit var notesAdapter: NotesAdapter
    companion object {

        fun newInstance(): FragmentNotesList {
            return FragmentNotesList()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutNotsBinding =
            LayoutNotesBinding.inflate(inflater, container, false)
        item_list.add(1)
        item_list.add(2)
        item_list.add(3)
        notesAdapter = NotesAdapter(requireActivity(), item_list)
        val linearLayoutManager =
            LinearLayoutManager(requireActivity())
        layoutNotsBinding.rvNotes.layoutManager = linearLayoutManager
        layoutNotsBinding.rvNotes.adapter = notesAdapter

        layoutNotsBinding.rlAdd.setOnClickListener {
            (activity as BaseActivity).docAppPresenter!!.navigateTo(FragmentNotesAdd.newInstance())
        }

        val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(layoutNotsBinding.rvNotes)


        // making http call and fetching menu json



        val itemTouchHelperCallback1: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    notesAdapter.notifyItemRemoved(viewHolder.adapterPosition)

                }
                override  fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    super.onChildDraw(
                        c!!, recyclerView!!,
                        viewHolder!!, dX, dY, actionState, isCurrentlyActive
                    )
                }

            }

        // attaching the touch helper to recycler view

        // attaching the touch helper to recycler view
        ItemTouchHelper(itemTouchHelperCallback1).attachToRecyclerView(layoutNotsBinding.rvNotes)

        return layoutNotsBinding.getRoot()
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int) {
        notesAdapter.notifyItemRemoved(viewHolder!!.adapterPosition)

    }
}