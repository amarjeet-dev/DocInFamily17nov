package com.e.docinfamily17nov.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivitySettingPlanBinding

class SettingPlan : AppCompatActivity() {
    lateinit var activitySettingPlanBinding: ActivitySettingPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySettingPlanBinding=DataBindingUtil.setContentView(this,R.layout.activity_setting_plan)
        activitySettingPlanBinding.rlEdit.setOnClickListener {
            val intent =Intent(this,PlanUpdateActivity::class.java)
            startActivity(intent)
        }
        activitySettingPlanBinding.rlBack.setOnClickListener {
          finish()
            overridePendingTransition(R.anim.fadein,R.anim.fadeout)
        }
    }
}