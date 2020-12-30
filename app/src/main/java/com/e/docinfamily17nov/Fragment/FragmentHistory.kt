package com.e.docinfamily17nov.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.e.docinfamily17nov.Adapter.HistoryAdapter
import com.e.docinfamily17nov.Model.MedicalOptionModel
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.LayoutAspatientLogBinding
import com.e.docinfamily17nov.databinding.LayoutHistoryBinding

class FragmentHistory : Fragment() {

    var item_list: ArrayList<MedicalOptionModel> = ArrayList()
lateinit var layoutHistoryBinding: LayoutHistoryBinding
    lateinit var historyAdapter: HistoryAdapter
    companion object {

        fun newInstance(): FragmentHistory {
            return FragmentHistory()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutHistoryBinding =
            LayoutHistoryBinding.inflate(inflater, container, false)
        addTitle()
        historyAdapter = HistoryAdapter(requireActivity(), item_list)
        val linearLayoutManager =
            GridLayoutManager(requireActivity(),2, GridLayoutManager.VERTICAL,false)
        layoutHistoryBinding.rvRecyecle.layoutManager = linearLayoutManager
        layoutHistoryBinding.rvRecyecle.adapter = historyAdapter

        layoutHistoryBinding.rlMenu.setOnClickListener {
            layoutHistoryBinding.rlSocial.visibility=View.VISIBLE
        }
        return layoutHistoryBinding.getRoot()
    }

    fun addTitle() {
        var model: MedicalOptionModel = MedicalOptionModel("Medical", R.drawable.medicalic,  R.drawable.rect_medi )
        item_list.add(model)
        model = MedicalOptionModel("Surgical", R.drawable.surgicalic , R.drawable.rect_surgi )
        item_list.add(model)
        model = MedicalOptionModel("Medications",  R.drawable.medicationic,  R.drawable.rect_medicat )
        item_list.add(model)
        model = MedicalOptionModel("Allergies",  R.drawable.allergicic,  R.drawable.rect_allergi )
        item_list.add(model)
        model = MedicalOptionModel("Social",  R.drawable.socialic,  R.drawable.rect_social )
        item_list.add(model)
        model = MedicalOptionModel("Notes/Upload\nImages",  R.drawable.notesic,  R.drawable.rect_notes )
        item_list.add(model)

    }
}