package com.e.docinfamily17nov.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.e.docinfamily17nov.Model.RegisterExample
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.Utils.ViewUtils
import com.e.docinfamily17nov.ViewModels.ResetPAsswordViewModel
import com.e.docinfamily17nov.databinding.ActivitySetNewPasswordBinding
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class SetNewPasswordActivity : AppCompatActivity() {
    lateinit var activitySetNewPasswordBinding: ActivitySetNewPasswordBinding
    var token = ""
    var pass = ""
    var confirm_pass = ""
    lateinit var resetPAsswordViewModel: ResetPAsswordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySetNewPasswordBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_set_new_password)
        resetPAsswordViewModel = ViewModelProvider(this).get(ResetPAsswordViewModel::class.java)

        subscribeObserver()
        //click listener
        activitySetNewPasswordBinding.rlBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }
        activitySetNewPasswordBinding.rlSendlink.setOnClickListener {
            token = activitySetNewPasswordBinding.editToken.text.toString()
            pass = activitySetNewPasswordBinding.editNpass.text.toString()
            confirm_pass = activitySetNewPasswordBinding.editCnpass.text.toString()
            if(token.equals("")&&token.equals("")&&token.equals(""))
                ViewUtils.showSnackBar(
                    activitySetNewPasswordBinding.getRoot(),
                    this,
                    "Fill all fields"
                )else if(token.equals(""))
            {
                ViewUtils.showSnackBar(
                    activitySetNewPasswordBinding.getRoot(),
                    this,
                    "Enter OTP"
                )
            }
            else if(pass.equals(""))
            {
                ViewUtils.showSnackBar(
                    activitySetNewPasswordBinding.getRoot(),
                    this,
                    "Enter Password"
                )
            }
            else if(confirm_pass.equals(""))
            {
                ViewUtils.showSnackBar(
                    activitySetNewPasswordBinding.getRoot(),
                    this,
                    "Enter Confirm Password"
                )
            }
            else {
                if(pass.equals(confirm_pass)) {
                    activitySetNewPasswordBinding.isProgressShown = true
                    val jsonObj2 = JSONObject()
                    jsonObj2.put("token", token)
                    jsonObj2.put("password", pass)
                    jsonObj2.put("confirm_password", confirm_pass)
                    Log.e("datata", jsonObj2.toString())


                    val mediaType =
                        MediaType.parse("application/json; charset=utf-8")

                    val body =
                        RequestBody.create(mediaType, jsonObj2.toString())
                    resetPAsswordViewModel.init(
                      body
                    )
                }else{
                    ViewUtils.showSnackBar(
                        activitySetNewPasswordBinding.getRoot(),
                        this,
                        "Password Mismatch"
                    )
                }
            }
        }
    }

    private fun subscribeObserver() {

        //getBankAccountDEtail
        if (resetPAsswordViewModel != null) { //subscriber for get bank detail
            resetPAsswordViewModel.observerServerResponse().observe(
                this,
                androidx.lifecycle.Observer<RegisterExample> { response: RegisterExample? ->

                    if (resetPAsswordViewModel.observerServerResponse().value != null) {
                        var response: RegisterExample =
                            resetPAsswordViewModel.observerServerResponse().value!!
                        if (response.getSuccess() == true) {
                            Toast.makeText(
                                this,
                                response.getMessage().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(
                                this@SetNewPasswordActivity,
                                LoginRegisterActivity::class.java
                            )
                            startActivity(intent)
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                        }

                    }
                })


            //subscriber for loader
            resetPAsswordViewModel.getLoading()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {

                            if (!aBoolean) {

                                activitySetNewPasswordBinding.setIsProgressShown(false)
                            }
                        }


                    })
            //subscriber for message
            resetPAsswordViewModel.getMessage()
                ?.observe(this, androidx.lifecycle.Observer<String> { s: String? ->
                    if (s != null) {
                        if (!TextUtils.isEmpty(s)) {
                            ViewUtils.showSnackBar(
                                activitySetNewPasswordBinding.getRoot(),
                                this,
                                s
                            )
                        }
                    }
                })
            //subscriber for message
            resetPAsswordViewModel.getIsDataFound()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {
                            if (!aBoolean) {
                                ViewUtils.showSnackBar(
                                    activitySetNewPasswordBinding.getRoot(),
                                    this,
                                    "Something went wrong"
                                )
                            }
                        }


                    })

        }
    }


}