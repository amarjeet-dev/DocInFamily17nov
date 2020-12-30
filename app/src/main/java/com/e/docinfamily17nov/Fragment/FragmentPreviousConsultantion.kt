package com.e.docinfamily17nov.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.docinfamily17nov.Activities.BaseActivity
import com.e.docinfamily17nov.Adapter.TellAboutAdapter
import com.e.docinfamily17nov.Adapter.ViewPreviousConsAdapter
import com.e.docinfamily17nov.databinding.LayoutAspatientLogBinding
import com.e.docinfamily17nov.databinding.LayoutConsulationBinding
import com.e.docinfamily17nov.databinding.LayoutPreviousConsulationBinding

class FragmentPreviousConsultantion : Fragment() {
    lateinit var layoutPreviousConsulationBinding: LayoutPreviousConsulationBinding
lateinit var viewPreviousConsAdapter: ViewPreviousConsAdapter
    companion object {

        fun newInstance(): FragmentPreviousConsultantion {
            return FragmentPreviousConsultantion()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutPreviousConsulationBinding =
            LayoutPreviousConsulationBinding.inflate(inflater, container, false)

        viewPreviousConsAdapter = ViewPreviousConsAdapter(requireActivity())
        val linearLayoutManager =
            LinearLayoutManager(requireActivity())
        layoutPreviousConsulationBinding.rvCons.layoutManager = linearLayoutManager
        layoutPreviousConsulationBinding.rvCons.adapter = viewPreviousConsAdapter

        layoutPreviousConsulationBinding.rlBack.setOnClickListener {
            BaseActivity.rl_toolbar.visibility=View.VISIBLE
            (activity as BaseActivity).docAppPresenter!!.oneStepBack()
        }

        return layoutPreviousConsulationBinding.getRoot()

    }


}