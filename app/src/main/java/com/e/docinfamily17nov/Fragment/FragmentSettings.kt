package com.e.docinfamily17nov.Fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.docinfamily17nov.Activities.*
import com.e.docinfamily17nov.Model.RegisterExample
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.Utils.DocAppSharedPreference
import com.e.docinfamily17nov.Utils.ViewUtils
import com.e.docinfamily17nov.ViewModels.LogOutViewModel
import com.e.docinfamily17nov.databinding.LayoutNotes2Binding
import com.e.docinfamily17nov.databinding.LayoutNotesBinding
import com.e.docinfamily17nov.databinding.LayoutSettingMainBinding

class FragmentSettings : Fragment(), View.OnClickListener {
    var access_token = ""
    lateinit var docAppSharedPreference: DocAppSharedPreference
    lateinit var layoutSettingMainBinding: LayoutSettingMainBinding
    lateinit var logOutViewModel: LogOutViewModel

    companion object {

        fun newInstance(): FragmentSettings {
            return FragmentSettings()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutSettingMainBinding =
            LayoutSettingMainBinding.inflate(inflater, container, false)
        //shared preference
        docAppSharedPreference = DocAppSharedPreference.getInstance(requireActivity())!!
        access_token = docAppSharedPreference.access_token
        //model initialization
        logOutViewModel = ViewModelProvider(requireActivity()).get(LogOutViewModel::class.java)

        //subscribe observer
        subscribeObserver()
        //logout model

        //click listener
        layoutSettingMainBinding.rlBasic.setOnClickListener(this)
        layoutSettingMainBinding.rlCurent.setOnClickListener(this)
        layoutSettingMainBinding.rlOther.setOnClickListener(this)
        layoutSettingMainBinding.rlPassword.setOnClickListener(this)
        layoutSettingMainBinding.rlLogout.setOnClickListener(this)

        return layoutSettingMainBinding.getRoot()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.rl_password -> {
                val intent = Intent(requireActivity(), PasswordSettingActivity::class.java)
                requireActivity().startActivity(intent)
            }
            R.id.rl_other -> {
                val intent = Intent(requireActivity(), OtherSetting::class.java)
                requireActivity().startActivity(intent)
            }
            R.id.rl_curent -> {
                val intent = Intent(requireActivity(), SettingPlan::class.java)
                requireActivity().startActivity(intent)
            }
            R.id.rl_basic -> {
                val intent = Intent(requireActivity(), SettingBasicInfo::class.java)
                requireActivity().startActivity(intent)
            }
            R.id.rl_logout -> {
                layoutSettingMainBinding.isProgressShown = true
                logOutViewModel.init(access_token)
            }
        }
    }

    private fun subscribeObserver() {

        //getBankAccountDEtail
        if (logOutViewModel != null) { //subscriber for get bank detail
            logOutViewModel.observerServerResponse().observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer<RegisterExample> { response: RegisterExample? ->

                    if (logOutViewModel.observerServerResponse().value != null) {
                        var response: RegisterExample =
                            logOutViewModel.observerServerResponse().value!!
                        if (response.getSuccess() == true) {
                            Toast.makeText(
                                context,
                                response.getMessage().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            docAppSharedPreference.clearPreference()
                            val intent =
                                Intent(requireActivity(), LoginRegisterActivity::class.java)
                            startActivity(intent)
                            requireActivity().overridePendingTransition(
                                R.anim.fadein,
                                R.anim.fadeout
                            )
                            requireActivity().finishAffinity()
                        }

                    }
                })
            //subscriber for loader
            logOutViewModel.getLoading()
                ?.observe(
                    viewLifecycleOwner,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {

                            if (!aBoolean) {

                                layoutSettingMainBinding.setIsProgressShown(false)
                            }
                        }


                    })
            //subscriber for message
            logOutViewModel.getMessage()
                ?.observe(viewLifecycleOwner, androidx.lifecycle.Observer<String> { s: String? ->
                    if (s != null) {
                        if (!TextUtils.isEmpty(s)) {
                            ViewUtils.showSnackBar(
                                layoutSettingMainBinding.getRoot(),
                                requireActivity(),
                                s
                            )
                        }
                    }
                })
            //subscriber for message
            logOutViewModel.getIsDataFound()
                ?.observe(
                    viewLifecycleOwner,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {
                            if (!aBoolean) {
                                ViewUtils.showSnackBar(
                                    layoutSettingMainBinding.getRoot(),
                                    requireActivity(),
                                    "Something went wrong"
                                )
                            }
                        }
                    })
        }
    }
}