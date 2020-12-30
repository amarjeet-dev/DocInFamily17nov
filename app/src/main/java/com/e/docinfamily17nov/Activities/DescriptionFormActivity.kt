package com.e.docinfamily17nov.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivityDescriptionFormBinding

class DescriptionFormActivity : AppCompatActivity() {
    lateinit var activityDescriptionFormBinding: ActivityDescriptionFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDescriptionFormBinding=DataBindingUtil.setContentView(this,R.layout.activity_description_form)
        activityDescriptionFormBinding.rlSubmit.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein,R.anim.fadeout)
        }
        activityDescriptionFormBinding.rlBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein,R.anim.fadeout)
        }
    }
}