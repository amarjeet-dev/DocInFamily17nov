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
import com.e.docinfamily17nov.databinding.LayoutLogReg2Binding

class logRegFrag2 : Fragment() {
    lateinit var logReg2Binding: LayoutLogReg2Binding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        logReg2Binding =
                LayoutLogReg2Binding.inflate(inflater, container, false)
        val nightModeFlags = context!!.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES ->
            {
                logReg2Binding.tv4.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))


            }

            Configuration.UI_MODE_NIGHT_NO ->
            {
                logReg2Binding.tv4.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))


            }
            //Configuration.UI_MODE_NIGHT_UNDEFINED -> doStuff()
        }
        return logReg2Binding.getRoot()
    }
    companion object {

        fun newInstance(): logRegFrag2 {
            return logRegFrag2()
        }
    }

}