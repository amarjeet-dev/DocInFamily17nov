package com.e.docinfamily17nov.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    lateinit var activityNotificationBinding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNotificationBinding=DataBindingUtil.setContentView(this,R.layout.activity_notification)
        activityNotificationBinding.rlBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein,R.anim.fadeout)
        }
    }
}