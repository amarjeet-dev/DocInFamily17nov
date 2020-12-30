package com.e.docinfamily17nov.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var activityHelpBinding: ActivityHelpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHelpBinding=DataBindingUtil.setContentView(this,R.layout.activity_help)
        clickListener()
    }
    public fun clickListener()
    {
        activityHelpBinding.rl1.setOnClickListener(this)
        activityHelpBinding.rl2.setOnClickListener(this)
        activityHelpBinding.rl3.setOnClickListener(this)
        activityHelpBinding.rl4.setOnClickListener(this)
        activityHelpBinding.rl5.setOnClickListener(this)
        activityHelpBinding.rl6.setOnClickListener(this)
        activityHelpBinding.rl7.setOnClickListener(this)
        activityHelpBinding.rlBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.rl1->{
//startAnimation()
                activityHelpBinding.tvAns1.visibility=View.VISIBLE
                activityHelpBinding.tvAns2.visibility=View.GONE
                activityHelpBinding.tvAns3.visibility=View.GONE
                activityHelpBinding.tvAns4.visibility=View.GONE
                activityHelpBinding.tvAns5.visibility=View.GONE
                activityHelpBinding.tvAns6.visibility=View.GONE
                activityHelpBinding.tvAns7.visibility=View.GONE


            }
            R.id.rl2->{
                activityHelpBinding.tvAns1.visibility=View.GONE
                activityHelpBinding.tvAns2.visibility=View.VISIBLE
                activityHelpBinding.tvAns3.visibility=View.GONE
                activityHelpBinding.tvAns4.visibility=View.GONE
                activityHelpBinding.tvAns5.visibility=View.GONE
                activityHelpBinding.tvAns6.visibility=View.GONE
                activityHelpBinding.tvAns7.visibility=View.GONE
            }
            R.id.rl3->{
                activityHelpBinding.tvAns1.visibility=View.GONE
                activityHelpBinding.tvAns2.visibility=View.GONE
                activityHelpBinding.tvAns3.visibility=View.VISIBLE
                activityHelpBinding.tvAns4.visibility=View.GONE
                activityHelpBinding.tvAns5.visibility=View.GONE
                activityHelpBinding.tvAns6.visibility=View.GONE
                activityHelpBinding.tvAns7.visibility=View.GONE
            }
            R.id.rl4->{
                activityHelpBinding.tvAns1.visibility=View.GONE
                activityHelpBinding.tvAns2.visibility=View.GONE
                activityHelpBinding.tvAns3.visibility=View.GONE
                activityHelpBinding.tvAns4.visibility=View.VISIBLE
                activityHelpBinding.tvAns5.visibility=View.GONE
                activityHelpBinding.tvAns6.visibility=View.GONE
                activityHelpBinding.tvAns7.visibility=View.GONE
            }
            R.id.rl5->{
                activityHelpBinding.tvAns1.visibility=View.GONE
                activityHelpBinding.tvAns2.visibility=View.GONE
                activityHelpBinding.tvAns3.visibility=View.GONE
                activityHelpBinding.tvAns4.visibility=View.GONE
                activityHelpBinding.tvAns5.visibility=View.VISIBLE
                activityHelpBinding.tvAns6.visibility=View.GONE
                activityHelpBinding.tvAns7.visibility=View.GONE
            }
            R.id.rl6->{
                activityHelpBinding.tvAns1.visibility=View.GONE
                activityHelpBinding.tvAns2.visibility=View.GONE
                activityHelpBinding.tvAns3.visibility=View.GONE
                activityHelpBinding.tvAns4.visibility=View.GONE
                activityHelpBinding.tvAns5.visibility=View.GONE
                activityHelpBinding.tvAns6.visibility=View.VISIBLE
                activityHelpBinding.tvAns7.visibility=View.GONE
            }
            R.id.rl7->{
                activityHelpBinding.tvAns1.visibility=View.GONE
                activityHelpBinding.tvAns2.visibility=View.GONE
                activityHelpBinding.tvAns3.visibility=View.GONE
                activityHelpBinding.tvAns4.visibility=View.GONE
                activityHelpBinding.tvAns5.visibility=View.GONE
                activityHelpBinding.tvAns6.visibility=View.GONE
                activityHelpBinding.tvAns7.visibility=View.VISIBLE
            }
            R.id.rl_back->{
finish()
                overridePendingTransition(R.anim.fadein,R.anim.fadeout)
            }
        }
    }
    public fun startAnimation()
    {
       if(activityHelpBinding.tvAns1.visibility==View.VISIBLE)
       {
          // activityHelpBinding.
       }else{

       }
    }
}