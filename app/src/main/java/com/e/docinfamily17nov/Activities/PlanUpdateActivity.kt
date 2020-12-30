package com.e.docinfamily17nov.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivityPlanUpdateBinding

class PlanUpdateActivity : AppCompatActivity() {
    lateinit var activityPlanUpdateBinding: ActivityPlanUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPlanUpdateBinding=DataBindingUtil.setContentView(this,R.layout.activity_plan_update)
        activityPlanUpdateBinding.rlBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein,R.anim.fadeout)
        }
    }
}