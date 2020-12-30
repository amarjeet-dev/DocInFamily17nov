package com.e.docinfamily17nov.Activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.e.docinfamily17nov.Fragment.logRegFrag1
import com.e.docinfamily17nov.Fragment.logRegFrag2
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.Utils.OnSwipeTouchListener
import com.e.docinfamily17nov.databinding.ActivityLoginRegisterBinding


class LoginRegisterActivity : AppCompatActivity() {
    lateinit var dots: Array<TextView>
    var page_position: Int = 0

    lateinit var loginRegisterBinding: ActivityLoginRegisterBinding
    var adapterViewPager: FragmentPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        loginRegisterBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login_register)

        //adapter for viewpager content
        adapterViewPager = MyPagerAdapter(getSupportFragmentManager())
        loginRegisterBinding.vvPager.setAdapter(adapterViewPager)

        //tab layout for indicators
        loginRegisterBinding.tabLayout.setupWithViewPager(loginRegisterBinding.vvPager, true);

        //login button click
        loginRegisterBinding.ivLogin.setOnClickListener {
            var intent = Intent(this@LoginRegisterActivity, LoginSectionActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }

        //signup button click
        loginRegisterBinding.ivRegister.setOnClickListener {
            var intent = Intent(this@LoginRegisterActivity, SignupSectionActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }
        loginRegisterBinding.vvPager.addOnPageChangeListener(object : OnPageChangeListener {
            // This method will be invoked when a new page becomes selected.
            override fun onPageSelected(position: Int) {

                loginRegisterBinding.vvPager.setCurrentItem(position)
            }

            // This method will be invoked when the current page is scrolled
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            override fun onPageScrollStateChanged(state: Int) {
                // Code goes here
            }
        })
    }

    //..............................................................


    class MyPagerAdapter(fragmentManager: FragmentManager?) :
        FragmentPagerAdapter(fragmentManager!!) {
        // Returns total number of pages
        override fun getCount(): Int {
            return NUM_ITEMS
        }

        // Returns the fragment to display for that page
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    logRegFrag1.newInstance()
                }
                1 -> {
                    logRegFrag2.newInstance()
                }

                else -> {
                    null!!
                }
            }
        }


        companion object {
            private const val NUM_ITEMS = 2
        }
    }

}