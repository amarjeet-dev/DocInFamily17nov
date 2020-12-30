package com.e.docinfamily17nov.Activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.LayoutSignupCompleteBinding

class SignUpComplete : AppCompatActivity() {
    lateinit var layoutSignupCompleteBinding: LayoutSignupCompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutSignupCompleteBinding = DataBindingUtil.setContentView(this, R.layout.layout_signup_complete)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        layoutSignupCompleteBinding.ivLogin.setOnClickListener {
            val intent = Intent(this@SignUpComplete, TellYourselfActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fadein,R.anim.fadeout)
        }

    }
}