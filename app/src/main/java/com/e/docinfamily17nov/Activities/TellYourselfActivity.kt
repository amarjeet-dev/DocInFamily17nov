package com.e.docinfamily17nov.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.e.docinfamily17nov.Adapter.TellAboutAdapter
import com.e.docinfamily17nov.Model.MedicalOptionModel

import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivityTellYourselfBinding

class TellYourselfActivity : AppCompatActivity() {
    var item_list: ArrayList<MedicalOptionModel> = ArrayList()
    lateinit var tellAboutAdapter: TellAboutAdapter
    lateinit var activityTellYourselfBinding: ActivityTellYourselfBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTellYourselfBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_tell_yourself)
        addTitle()
        tellAboutAdapter = TellAboutAdapter(this, item_list)
        val linearLayoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        activityTellYourselfBinding.rvRecyecle.layoutManager = linearLayoutManager
        activityTellYourselfBinding.rvRecyecle.adapter = tellAboutAdapter

        //skip
        activityTellYourselfBinding.rlSkip.setOnClickListener {
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }
        //next
        activityTellYourselfBinding.rlNext.setOnClickListener {
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }
    }

    fun addTitle() {
        var model: MedicalOptionModel =
            MedicalOptionModel("Medical", R.drawable.medicalic, R.drawable.rect_medi)
        item_list.add(model)
        model = MedicalOptionModel("Surgical", R.drawable.surgicalic, R.drawable.rect_surgi)
        item_list.add(model)
        model = MedicalOptionModel("Medications", R.drawable.medicationic, R.drawable.rect_medicat)
        item_list.add(model)
        model = MedicalOptionModel("Allergies", R.drawable.allergicic, R.drawable.rect_allergi)
        item_list.add(model)
        model = MedicalOptionModel("Social", R.drawable.socialic, R.drawable.rect_social)
        item_list.add(model)
        model =
            MedicalOptionModel("Notes/Upload\nImages", R.drawable.notesic, R.drawable.rect_notes)
        item_list.add(model)

    }
}