package com.e.docinfamily17nov.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.e.docinfamily17nov.Model.GetProfileExample
import com.e.docinfamily17nov.Model.GetProfileUserData
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.Utils.DocAppSharedPreference
import com.e.docinfamily17nov.Utils.ViewUtils
import com.e.docinfamily17nov.ViewModels.GetProfileViewModel
import com.e.docinfamily17nov.databinding.ActivitySettingBasicInfoBinding

class SettingBasicInfo : AppCompatActivity() {
    lateinit var activitySettingBasicInfoBinding: ActivitySettingBasicInfoBinding
    lateinit var getProfileViewModel: GetProfileViewModel
    var access_token=""
    lateinit var docAppSharedPreference: DocAppSharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySettingBasicInfoBinding=DataBindingUtil.setContentView(this,R.layout.activity_setting_basic_info)
        getProfileViewModel=ViewModelProvider(this).get(GetProfileViewModel::class.java)

        //shared preference
        docAppSharedPreference=DocAppSharedPreference.getInstance(this)!!
        access_token=docAppSharedPreference.access_token
        //subscriber
        subscribeObserver()
        activitySettingBasicInfoBinding.isProgressShown=true
        getProfileViewModel.init(access_token)
        //clickListener
        activitySettingBasicInfoBinding.rlBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein,R.anim.fadeout)
        }
        activitySettingBasicInfoBinding.rlEdit.setOnClickListener {
            val intent=Intent(this,SettingEditInfo::class.java)
            startActivity(intent)
        }
    }

    private fun subscribeObserver() {

        //getBankAccountDEtail
        if (getProfileViewModel != null) { //subscriber for get bank detail
            getProfileViewModel.observerServerResponse().observe(
                this,
                androidx.lifecycle.Observer<GetProfileExample> { response: GetProfileExample? ->

                    if (getProfileViewModel.observerServerResponse().value != null) {
                        var response: GetProfileExample =
                            getProfileViewModel.observerServerResponse().value!!
                        if (response.getSuccess() == true) {


                            response.getData()?.getUser()?.getFirstName()?.let {
                                activitySettingBasicInfoBinding.tvName.setText(it)
                            }

                            response.getData()?.getUser()?.getGender()?.let {
                                activitySettingBasicInfoBinding.tvSex.setText(it)
                            }
                            response.getData()?.getUser()?.getEmail()?.let {
                                activitySettingBasicInfoBinding.tvEmail.setText(it)
                            }
                            response.getData()?.getUser()?.getPhoneNumber()?.let {
                                activitySettingBasicInfoBinding.tvMobile.setText(it)
                            }
                            response.getData()?.getUser()?.getDob()?.let {
                                activitySettingBasicInfoBinding.tvDob.setText(it)
                            }
                            response.getData()?.getUser()?.getState()?.let {
                                activitySettingBasicInfoBinding.tvState.setText(it)
                            }
                            response.getData()?.getUser()?.getCountry()?.let {
                                activitySettingBasicInfoBinding.tvCountry.setText(it)
                            }


                        }

                    }
                })


            //subscriber for loader
            getProfileViewModel.getLoading()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {

                            if (!aBoolean) {

                                activitySettingBasicInfoBinding.setIsProgressShown(false)
                            }
                        }


                    })
            //subscriber for message
            getProfileViewModel.getMessage()
                ?.observe(this, androidx.lifecycle.Observer<String> { s: String? ->
                    if (s != null) {
                        if (!TextUtils.isEmpty(s)) {
                            ViewUtils.showSnackBar(
                                activitySettingBasicInfoBinding.getRoot(),
                                this,
                                s
                            )
                        }
                    }
                })
            //subscriber for message
            getProfileViewModel.getIsDataFound()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {
                            if (!aBoolean) {
                                ViewUtils.showSnackBar(
                                    activitySettingBasicInfoBinding.getRoot(),
                                    this,
                                    "Something went wrong"
                                )
                            }
                        }


                    })

        }
    }



}