package com.e.docinfamily17nov.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.docinfamily17nov.Activities.BaseActivity
import com.e.docinfamily17nov.Adapter.NotesAdapter
import com.e.docinfamily17nov.databinding.LayoutNotes2Binding
import com.e.docinfamily17nov.databinding.LayoutNotesAddBinding
import com.e.docinfamily17nov.databinding.LayoutPpointmentlistBinding


class FragmentNotesAdd : Fragment() {

    lateinit var layoutNotesAddBinding: LayoutNotesAddBinding
    lateinit var notesAdapter: NotesAdapter
    companion object {

        fun newInstance(): FragmentNotesAdd {
            return FragmentNotesAdd()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutNotesAddBinding =
            LayoutNotesAddBinding.inflate(inflater, container, false)
layoutNotesAddBinding.rlBack.setOnClickListener {
    (activity as BaseActivity).docAppPresenter!!.oneStepBack()
}

        return layoutNotesAddBinding.getRoot()
    }
}