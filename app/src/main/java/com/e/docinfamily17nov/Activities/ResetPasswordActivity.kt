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
import com.e.docinfamily17nov.ViewModels.ForgotPAsswordViewModel
import com.e.docinfamily17nov.databinding.ActivityResetPasswordBinding
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class ResetPasswordActivity : AppCompatActivity(){
    var email=""
    lateinit var activityResetPasswordBinding: ActivityResetPasswordBinding
    lateinit var registerViewModel: ForgotPAsswordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityResetPasswordBinding=DataBindingUtil.setContentView(this,
            R.layout.activity_reset_password
        )
        registerViewModel= ViewModelProvider(this).get(ForgotPAsswordViewModel::class.java)
//subscribe observer
        subscribeObserver()
        //click listener
        activityResetPasswordBinding.rlBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein,R.anim.fadeout)
        }
        activityResetPasswordBinding.rlSendlink.setOnClickListener {
            email=activityResetPasswordBinding.editEmail.text.toString()
            if(email.equals(""))
            {

            }else {
                activityResetPasswordBinding.isProgressShown = true

                val jsonObj2 = JSONObject()
                jsonObj2.put("email", email)

                Log.e("datata", jsonObj2.toString())


                val mediaType =
                    MediaType.parse("application/json; charset=utf-8")

                val body =
                    RequestBody.create(mediaType, jsonObj2.toString())
                registerViewModel.init(
                    body)
            }
        }
    }



    private fun subscribeObserver() {

        //getBankAccountDEtail
        if (registerViewModel != null) { //subscriber for get bank detail
            registerViewModel.observerServerResponse().observe(
                this,
                androidx.lifecycle.Observer<RegisterExample> { response: RegisterExample? ->

                    if (registerViewModel.observerServerResponse().value != null) {
                        var response: RegisterExample =
                            registerViewModel.observerServerResponse().value!!
                        if (response.getSuccess() == true) {
                            Toast.makeText(
                                this,
                                response.getMessage().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@ResetPasswordActivity, SetNewPasswordActivity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.fadein,R.anim.fadeout)
                        }

                    }
                })


            //subscriber for loader
            registerViewModel.getLoading()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {

                            if (!aBoolean) {

                                activityResetPasswordBinding.setIsProgressShown(false)
                            }
                        }


                    })
            //subscriber for message
            registerViewModel.getMessage()
                ?.observe(this, androidx.lifecycle.Observer<String> { s: String? ->
                    if (s != null) {
                        if (!TextUtils.isEmpty(s)) {
                            ViewUtils.showSnackBar(
                                activityResetPasswordBinding.getRoot(),
                               this,
                                s
                            )
                        }
                    }
                })
            //subscriber for message
            registerViewModel.getIsDataFound()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {
                            if (!aBoolean) {
                                ViewUtils.showSnackBar(
                                    activityResetPasswordBinding.getRoot(),
                                   this,
                                    "Something went wrong"
                                )
                            }
                        }


                    })

        }
    }


}