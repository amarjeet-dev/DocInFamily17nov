package com.e.docinfamily17nov.Activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

import com.e.docinfamily17nov.Fragment.FragmentConsultant_signup
import com.e.docinfamily17nov.Fragment.FragmentPatient_signUp
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivitySignupSectionBinding


class SignupSectionActivity : AppCompatActivity() {
    lateinit var activitybinding: ActivitySignupSectionBinding
    private val tabIcons = intArrayOf(
        R.drawable.ic_patient,
        R.drawable.ic_consultant_log,

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitybinding = DataBindingUtil.setContentView(this, R.layout.activity_signup_section)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        //set viewpager
        setupViewPager(activitybinding.vpPager);
        //set view pager with tab layout
        activitybinding.tlLayout.setupWithViewPager(activitybinding.vpPager);
        //set tab icons
        setupTabIcons();
        //back click listener
        activitybinding.rlBack.setOnClickListener { finish() }
    }

    fun setupTabIcons() {
        activitybinding.tlLayout.getTabAt(0)!!.setIcon(tabIcons[0]);
        activitybinding.tlLayout.getTabAt(1)!!.setIcon(tabIcons[1]);

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(FragmentPatient_signUp("patient"), "As Patient")
        adapter.addFrag(FragmentConsultant_signup("consultant"), "As Consultant")

        viewPager.adapter = adapter
    }

    internal class ViewPagerAdapter(manager: FragmentManager?) : FragmentPagerAdapter(manager!!) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}