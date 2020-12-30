package com.e.docinfamily17nov.Fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bruce.pickerview.popwindow.DatePickerPopWin
import com.bruce.pickerview.popwindow.DateTimePickerPopWin
import com.e.docinfamily17nov.Activities.BaseActivity
import com.e.docinfamily17nov.databinding.LayoutConsulationBinding
import java.text.SimpleDateFormat
import java.util.*

class FragmentConsultantion : Fragment() {
    lateinit var layoutConsulationBinding: LayoutConsulationBinding

    companion object {

        fun newInstance(): FragmentConsultantion {
            return FragmentConsultantion()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutConsulationBinding =
            LayoutConsulationBinding.inflate(inflater, container, false)
        layoutConsulationBinding.editConsultTime.setOnClickListener {
            val todayDate = Calendar.getInstance().time
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val todayString = formatter.format(todayDate)
            Log.e("today_string", todayString)
            val pickerPopWin = DateTimePickerPopWin.Builder(requireActivity(),
                DateTimePickerPopWin.OnDatePickedListener { year, month, day, dateDesc ->

                }, object : DateTimePickerPopWin.OnTimePickListener {
                    override fun onTimePickCompleted(hour: Int, minute: Int, AM_PM: String?, time: String?) {
                        //Toast.makeText(MainActivity.this, time, Toast.LENGTH_SHORT).show();
                    }
                }).textConfirm("CONFIRM") //text of confirm button
                .textCancel("CANCEL") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .minYear(1990) //min year in loop
                .maxYear(2550) // max year in loop
                .dateChose(todayString) // date chose when init popwindow
                .build()
            pickerPopWin.showPopWin(requireActivity())

        }
        layoutConsulationBinding.editConsultDate.setOnClickListener {
            val todayDate = Calendar.getInstance().time
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val todayString = formatter.format(todayDate)
            Log.e("today_string", todayString)
            val pickerPopWin = DateTimePickerPopWin.Builder(requireActivity(),
                DateTimePickerPopWin.OnDatePickedListener { year, month, day, dateDesc ->

                }, object : DateTimePickerPopWin.OnTimePickListener {
                    override fun onTimePickCompleted(hour: Int, minute: Int, AM_PM: String?, time: String?) {
                        //Toast.makeText(MainActivity.this, time, Toast.LENGTH_SHORT).show();
                    }
                }).textConfirm("CONFIRM") //text of confirm button
                .textCancel("CANCEL") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .minYear(1990) //min year in loop
                .maxYear(2550) // max year in loop
                .dateChose(todayString) // date chose when init popwindow
                .build()
            pickerPopWin.showPopWin(requireActivity())


        }

layoutConsulationBinding.rlView.setOnClickListener {

    (activity as BaseActivity).docAppPresenter!!.navigateTo(FragmentPreviousConsultantion.newInstance())
}
        return layoutConsulationBinding.getRoot()
    }


}