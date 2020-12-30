package com.e.docinfamily17nov.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.docinfamily17nov.Activities.BaseActivity
import com.e.docinfamily17nov.Adapter.NotesAdapter
import com.e.docinfamily17nov.Model.MedicalOptionModel
import com.e.docinfamily17nov.databinding.LayoutNotes2Binding
import com.e.docinfamily17nov.databinding.LayoutPpointmentlistBinding

class FragmentNotesDetail : Fragment() {

    var item_list: ArrayList<MedicalOptionModel> = ArrayList()
    lateinit var layoutNotes2Binding: LayoutNotes2Binding
    lateinit var notesAdapter: NotesAdapter
    companion object {

        fun newInstance(): FragmentNotesDetail {
            return FragmentNotesDetail()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutNotes2Binding =
            LayoutNotes2Binding.inflate(inflater, container, false)
        layoutNotes2Binding.rlBack.setOnClickListener {
            (activity as BaseActivity).docAppPresenter!!.oneStepBack()
        }

        return layoutNotes2Binding.getRoot()
    }
}