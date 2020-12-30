package com.e.docinfamily17nov.Activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.e.docinfamily17nov.Fragment.*
import com.e.docinfamily17nov.Presenter.basePackage.DocAppPresenter
import com.e.docinfamily17nov.Presenter.basePackage.DocAppPresenterImpl
import com.e.docinfamily17nov.Presenter.basePackage.DocAppView
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivityContainerBinding


open class BaseActivity : AppCompatActivity(), DocAppView, View.OnClickListener {
    var docAppPresenter: DocAppPresenter? = null
    var activityContainerBinding: ActivityContainerBinding? = null
lateinit var previos_frag:Fragment
    companion object {
        lateinit var rl_toolbar: RelativeLayout
        lateinit var rl_menu: RelativeLayout
        lateinit var rl_social: RelativeLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setStatusBarTransparent(this)
        activityContainerBinding = DataBindingUtil.setContentView(this, R.layout.activity_container)
        docAppPresenter = DocAppPresenterImpl(this, this)
        docAppPresenter!!.navigateTo(FragmentConsultantion.newInstance())
        clickListener()
    }

    public fun clickListener() {

        activityContainerBinding!!.llAppoint.setOnClickListener(this)
        activityContainerBinding!!.llConsult.setOnClickListener(this)
        activityContainerBinding!!.llHistory.setOnClickListener(this)
        activityContainerBinding!!.llNotes.setOnClickListener(this)
        activityContainerBinding!!.llSettings.setOnClickListener(this)
    }

    override fun onBackPressed() {
        docAppPresenter!!.oneStepBack()
//        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun changeTitle(title: String?) {

    }

    override fun hideNavigationButton() {

    }

    override fun showNavigationButton() {

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ll_appoint -> {
                activityContainerBinding!!.ivCalender.setImageResource(R.drawable.calendar_blue)
                activityContainerBinding!!.tvCalender.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.royal_blue_whit
                    )
                )
                activityContainerBinding!!.ivCons.setImageResource(R.drawable.ic_consult_blue)
                activityContainerBinding!!.tvCons.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                activityContainerBinding!!.ivHistory.setImageResource(R.drawable.history)
                activityContainerBinding!!.tvHistory.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                activityContainerBinding!!.ivNotes.setImageResource(R.drawable.notes)
                activityContainerBinding!!.tvNotes.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                activityContainerBinding!!.ivSettings.setImageResource(R.drawable.settings)
                activityContainerBinding!!.tvSettings.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )


                docAppPresenter!!.navigateToClearAllPreviousFragment(FragmentAppointment.newInstance())
            }
            R.id.ll_consult -> {
                activityContainerBinding!!.ivCalender.setImageResource(R.drawable.calendar)
                activityContainerBinding!!.tvCalender.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )
                activityContainerBinding!!.ivCons.setImageResource(R.drawable.consult_blue)
                activityContainerBinding!!.tvCons.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.royal_blue_whit
                    )
                )

                activityContainerBinding!!.ivHistory.setImageResource(R.drawable.history)
                activityContainerBinding!!.tvHistory.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                activityContainerBinding!!.ivNotes.setImageResource(R.drawable.notes)
                activityContainerBinding!!.tvNotes.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                activityContainerBinding!!.ivSettings.setImageResource(R.drawable.settings)
                activityContainerBinding!!.tvSettings.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                docAppPresenter!!.navigateToClearAllPreviousFragment(FragmentConsultantion.newInstance())
            }
            R.id.ll_notes -> {
                activityContainerBinding!!.ivCalender.setImageResource(R.drawable.calendar)
                activityContainerBinding!!.tvCalender.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )
                activityContainerBinding!!.ivCons.setImageResource(R.drawable.ic_consult_blue)
                activityContainerBinding!!.tvCons.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                activityContainerBinding!!.ivHistory.setImageResource(R.drawable.history)
                activityContainerBinding!!.tvHistory.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                activityContainerBinding!!.ivNotes.setImageResource(R.drawable.ic_notes_blue)
                activityContainerBinding!!.tvNotes.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.royal_blue_whit
                    )
                )

                activityContainerBinding!!.ivSettings.setImageResource(R.drawable.settings)
                activityContainerBinding!!.tvSettings.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                docAppPresenter!!.navigateToClearAllPreviousFragment(FragmentNotesList.newInstance())
            }
            R.id.ll_history -> {
                activityContainerBinding!!.ivCalender.setImageResource(R.drawable.calendar)
                activityContainerBinding!!.tvCalender.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )
                activityContainerBinding!!.ivCons.setImageResource(R.drawable.ic_consult_blue)
                activityContainerBinding!!.tvCons.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                activityContainerBinding!!.ivHistory.setImageResource(R.drawable.ic_history_blue)
                activityContainerBinding!!.tvHistory.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.royal_blue_whit
                    )
                )

                activityContainerBinding!!.ivNotes.setImageResource(R.drawable.notes)
                activityContainerBinding!!.tvNotes.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                activityContainerBinding!!.ivSettings.setImageResource(R.drawable.settings)
                activityContainerBinding!!.tvSettings.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                docAppPresenter!!.navigateToClearAllPreviousFragment(FragmentHistory.newInstance())
            }
            R.id.ll_settings -> {
                activityContainerBinding!!.ivCalender.setImageResource(R.drawable.calendar)
                activityContainerBinding!!.tvCalender.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )
                activityContainerBinding!!.ivCons.setImageResource(R.drawable.ic_consult_blue)
                activityContainerBinding!!.tvCons.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                activityContainerBinding!!.ivHistory.setImageResource(R.drawable.history)
                activityContainerBinding!!.tvHistory.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                activityContainerBinding!!.ivNotes.setImageResource(R.drawable.notes)
                activityContainerBinding!!.tvNotes.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white_black
                    )
                )

                activityContainerBinding!!.ivSettings.setImageResource(R.drawable.ic_settings_blue)
                activityContainerBinding!!.tvSettings.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.royal_blue_whit
                    )
                )

                docAppPresenter!!.navigateToClearAllPreviousFragment(FragmentSettings.newInstance())
            }

        }
    }


}