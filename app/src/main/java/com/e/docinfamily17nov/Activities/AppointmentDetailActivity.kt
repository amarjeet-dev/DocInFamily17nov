package com.e.docinfamily17nov.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivityAppointmentDetailBinding

class AppointmentDetailActivity : AppCompatActivity() {
    lateinit var activityAppointmentDetailBinding: ActivityAppointmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAppointmentDetailBinding=DataBindingUtil.setContentView(this,R.layout.activity_appointment_detail)
        activityAppointmentDetailBinding.rlBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein,R.anim.fadeout)
        }
    }
}