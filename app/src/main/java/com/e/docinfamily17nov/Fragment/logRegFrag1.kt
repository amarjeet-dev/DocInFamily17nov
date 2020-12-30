package com.e.docinfamily17nov.Fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.LayoutLogReg1Binding

class logRegFrag1 : Fragment() {
    lateinit var logReg1Binding: LayoutLogReg1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logReg1Binding =
                LayoutLogReg1Binding.inflate(inflater, container, false)
        val nightModeFlags = context!!.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES ->
            {
                logReg1Binding.tv1.setTextColor(ContextCompat.getColor(requireActivity(),R.color.white))
                logReg1Binding.tv2.setTextColor(ContextCompat.getColor(requireActivity(),R.color.white))
                logReg1Binding.tv3.setTextColor(ContextCompat.getColor(requireActivity(),R.color.white))

            }

                    Configuration.UI_MODE_NIGHT_NO ->
                    {
                        logReg1Binding.tv1.setTextColor(ContextCompat.getColor(requireActivity(),R.color.black))
                        logReg1Binding.tv2.setTextColor(ContextCompat.getColor(requireActivity(),R.color.black))
                        logReg1Binding.tv3.setTextColor(ContextCompat.getColor(requireActivity(),R.color.black))

                    }
            //Configuration.UI_MODE_NIGHT_UNDEFINED -> doStuff()
        }
        return logReg1Binding.getRoot()
    }
    companion object {

        fun newInstance(): logRegFrag1 {
            return logRegFrag1()
        }
    }

}