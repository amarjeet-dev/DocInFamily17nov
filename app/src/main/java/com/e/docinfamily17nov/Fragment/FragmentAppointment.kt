package com.e.docinfamily17nov.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.docinfamily17nov.Adapter.AppointmentAdapter
import com.e.docinfamily17nov.Model.MedicalOptionModel
import com.e.docinfamily17nov.databinding.LayoutAspatientLogBinding
import com.e.docinfamily17nov.databinding.LayoutHistoryBinding
import com.e.docinfamily17nov.databinding.LayoutPpointmentlistBinding

class FragmentAppointment : Fragment() {

    var item_list: ArrayList<MedicalOptionModel> = ArrayList()
lateinit var ppointmentlistBinding: LayoutPpointmentlistBinding
    lateinit var appointmentAdapter: AppointmentAdapter
    companion object {

        fun newInstance(): FragmentAppointment {
            return FragmentAppointment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ppointmentlistBinding =
            LayoutPpointmentlistBinding.inflate(inflater, container, false)

        appointmentAdapter = AppointmentAdapter(requireActivity())
        val linearLayoutManager =
            LinearLayoutManager(requireActivity())
        ppointmentlistBinding.rvAppointment.layoutManager = linearLayoutManager
        ppointmentlistBinding.rvAppointment.adapter = appointmentAdapter

        return ppointmentlistBinding.getRoot()
    }

}