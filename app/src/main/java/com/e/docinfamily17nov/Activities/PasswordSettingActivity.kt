package com.e.docinfamily17nov.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.e.docinfamily17nov.Model.RegisterExample
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.Utils.DocAppSharedPreference
import com.e.docinfamily17nov.Utils.ViewUtils
import com.e.docinfamily17nov.ViewModels.ChangePasswordViewModel
import com.e.docinfamily17nov.databinding.ActivityPasswordSettingBinding
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class PasswordSettingActivity : AppCompatActivity() {
    lateinit var changePasswordViewModel: ChangePasswordViewModel
    var current_pass = ""
    var new_password = ""
    var confirm_password = ""
    var access_token = ""
    lateinit var docAppSharedPreference: DocAppSharedPreference
    lateinit var activityPasswordSettingBinding: ActivityPasswordSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPasswordSettingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_password_setting)
        changePasswordViewModel=ViewModelProvider(this).get(ChangePasswordViewModel::class.java)
        //shared preference
        docAppSharedPreference = DocAppSharedPreference.getInstance(this)!!
       //access token
        access_token = docAppSharedPreference.access_token
        //subscribe
        subscribeObserver()

        activityPasswordSettingBinding.rlBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }

        activityPasswordSettingBinding.rlUpdate.setOnClickListener {
            current_pass = activityPasswordSettingBinding.editOldpass.text.toString()
            new_password = activityPasswordSettingBinding.editNpass.text.toString()
            confirm_password = activityPasswordSettingBinding.editCnpass.text.toString()
            if (current_pass.equals("")&&new_password.equals("")&&confirm_password.equals("")) {

                ViewUtils.showSnackBar(
                    activityPasswordSettingBinding.getRoot(),
                    this,
                    "Please fill all fields"
                )
            } else  if (current_pass.equals("")){
                ViewUtils.showSnackBar(
                    activityPasswordSettingBinding.getRoot(),
                    this,
                    "Please fill all fields")

            }
            else  if (new_password.equals("")){
                ViewUtils.showSnackBar(
                    activityPasswordSettingBinding.getRoot(),
                    this,
                    "Please fill all fields"
                )
            }
            else  if (confirm_password.equals("")){
                ViewUtils.showSnackBar(
                    activityPasswordSettingBinding.getRoot(),
                    this,
                    "Please fill all fields"
                )
            }
            else {
                activityPasswordSettingBinding.isProgressShown = true

                val jsonObj2 = JSONObject()
                jsonObj2.put("current_password", current_pass)
                jsonObj2.put("new_password", new_password)
                jsonObj2.put("new_confirm_password", confirm_password)
                Log.e("datata", jsonObj2.toString())


                val mediaType =
                    MediaType.parse("application/json; charset=utf-8")

                val body =
                    RequestBody.create(mediaType, jsonObj2.toString())
                changePasswordViewModel.init(
                    access_token,body
                )
            }
        }


    }


    private fun subscribeObserver() {

        //getBankAccountDEtail
        if (changePasswordViewModel != null) { //subscriber for get bank detail
            changePasswordViewModel.observerServerResponse().observe(
                this,
                androidx.lifecycle.Observer<RegisterExample> { response: RegisterExample? ->

                    if (changePasswordViewModel.observerServerResponse().value != null) {
                        var response: RegisterExample =
                            changePasswordViewModel.observerServerResponse().value!!
                        if (response.getSuccess() == true) {
                            Toast.makeText(
                                this,
                                response.getMessage().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                        }

                    }
                })


            //subscriber for loader
            changePasswordViewModel.getLoading()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {

                            if (!aBoolean) {

                                activityPasswordSettingBinding.setIsProgressShown(false)
                            }
                        }


                    })
            //subscriber for message
            changePasswordViewModel.getMessage()
                ?.observe(this, androidx.lifecycle.Observer<String> { s: String? ->
                    if (s != null) {
                        if (!TextUtils.isEmpty(s)) {
                            ViewUtils.showSnackBar(
                                activityPasswordSettingBinding.getRoot(),
                                this,
                                s
                            )
                        }
                    }
                })
            //subscriber for message
            changePasswordViewModel.getIsDataFound()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {
                            if (!aBoolean) {
                                ViewUtils.showSnackBar(
                                    activityPasswordSettingBinding.getRoot(),
                                    this,
                                    "Something went wrong"
                                )
                            }
                        }


                    })

        }
    }
}