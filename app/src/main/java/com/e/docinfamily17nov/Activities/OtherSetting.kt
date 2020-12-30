package com.e.docinfamily17nov.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivityOtherSettingBinding

class OtherSetting : AppCompatActivity(),View.OnClickListener {

    lateinit var otherSettingBinding: ActivityOtherSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        otherSettingBinding=DataBindingUtil.setContentView(this,R.layout.activity_other_setting)
ClickListener()
    }
    public fun ClickListener()
    {
        otherSettingBinding.rlHelp.setOnClickListener(this)
                otherSettingBinding.rlBack.setOnClickListener(this)
                otherSettingBinding.rlNotification.setOnClickListener(this)
                otherSettingBinding.rlPrivacy.setOnClickListener(this)
                otherSettingBinding.rlShare.setOnClickListener(this)
                otherSettingBinding.rlTerm.setOnClickListener(this)
                otherSettingBinding.rlUser.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v!!.id)
        {
            R.id.rl_help->
            {
                val intent= Intent(this,HelpActivity::class.java)
                startActivity(intent)
            }
            R.id.rl_back->
            {
finish()
                overridePendingTransition(R.anim.fadein,R.anim.fadeout)
            }
            R.id.rl_user->
            {
                val intent=Intent(this,LinkActivity::class.java)
                intent.putExtra("name","User Guide")
                startActivity(intent)
            }
            R.id.rl_privacy->
            {
                val intent=Intent(this,LinkActivity::class.java)
                intent.putExtra("name","Privacy")
                startActivity(intent)
            }
            R.id.rl_term->
            {
                val intent=Intent(this,LinkActivity::class.java)
                intent.putExtra("name","Terms of use")
                startActivity(intent)
            }
            R.id.rl_notification->
            {
                val intent=Intent(this, NotificationActivity::class.java)
                startActivity(intent)
            }
            R.id.rl_share->
            {

            }
        }
    }
}