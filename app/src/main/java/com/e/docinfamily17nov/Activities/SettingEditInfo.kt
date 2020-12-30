package com.e.docinfamily17nov.Activities

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.e.docinfamily17nov.Adapter.CountryAdapter
import com.e.docinfamily17nov.Adapter.StateAdapter
import com.e.docinfamily17nov.Model.*
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.Utils.DocAppSharedPreference
import com.e.docinfamily17nov.Utils.ViewUtils
import com.e.docinfamily17nov.ViewModels.CountryViewModel
import com.e.docinfamily17nov.ViewModels.GetProfileViewModel
import com.e.docinfamily17nov.ViewModels.StateViewModel
import com.e.docinfamily17nov.ViewModels.UpdateProfileViewModel
import com.e.docinfamily17nov.databinding.ActivitySettingEditInfoBinding
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject


class SettingEditInfo : AppCompatActivity(), View.OnClickListener {
    lateinit var activitySettingEditInfoBinding: ActivitySettingEditInfoBinding
    lateinit var updateProfileViewModel: UpdateProfileViewModel
    lateinit var getProfileViewModel: GetProfileViewModel
    lateinit var docAppSharedPreference: DocAppSharedPreference
    lateinit var countryViewModel: CountryViewModel
    lateinit var stateViewModel: StateViewModel
    var countrylist: List<CountriesData> = ArrayList()
    var stateList: List<StatesData> = ArrayList()
    lateinit var countryAdapter: CountryAdapter
    lateinit var stateAdapter: StateAdapter
    var state = ""
    var first_name = ""
    var last_name = ""
    var dob = ""
    var mobile = ""
    var race = ""
    var pronon = ""
    var gender = ""
    var country = ""
    var check = "0"
    var country_id = ""
    var access_token = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySettingEditInfoBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_setting_edit_info)
        //initialize model
//        update profile
        updateProfileViewModel = ViewModelProvider(this).get(UpdateProfileViewModel::class.java)

        //getprofile
        getProfileViewModel = ViewModelProvider(this).get(GetProfileViewModel::class.java)
        //country model
        countryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        //state model
        stateViewModel = ViewModelProvider(this).get(StateViewModel::class.java)
        //shared preference
        docAppSharedPreference = DocAppSharedPreference.getInstance(this)!!
        access_token = docAppSharedPreference.access_token

        //subscribe observer
        subscribeObserver()

        //get details
        activitySettingEditInfoBinding.isProgressShown = true
        getProfileViewModel.init(access_token)
        countryViewModel.init()
        //clcik listeners
        ClickListener()


    }

    public fun ClickListener() {
        activitySettingEditInfoBinding.rlBack.setOnClickListener(this)
        activitySettingEditInfoBinding.editCountry.setOnClickListener(this)
        activitySettingEditInfoBinding.editState.setOnClickListener(this)
//recycler touch listener
        activitySettingEditInfoBinding.rvCountry.addOnItemTouchListener(
            RecyclerTouchListener(this,
                activitySettingEditInfoBinding.rvCountry, object : ClickListener {
                    override fun onClick(view: View?, position: Int) {
                        activitySettingEditInfoBinding.editCountry.setText(
                            countrylist.get(position).getTitle()
                        )
                        country_id = countrylist.get(position).getId().toString()
                        activitySettingEditInfoBinding.editCountry.visibility = View.VISIBLE
                        activitySettingEditInfoBinding.rvCountry.visibility = View.GONE
                        activitySettingEditInfoBinding.isProgressShown = true
                        stateViewModel.init(country_id)
                        if (stateList.size == 0) {
                            activitySettingEditInfoBinding.editState.setText(
                                countrylist.get(
                                    position
                                ).getTitle()
                            )


                        }
                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })
        )

        //recycler touch listener
        activitySettingEditInfoBinding.rvState.addOnItemTouchListener(
            RecyclerTouchListener(this,
                activitySettingEditInfoBinding.rvState, object : ClickListener {
                    override fun onClick(view: View?, position: Int) {
                        activitySettingEditInfoBinding.editState.visibility = View.VISIBLE
                        activitySettingEditInfoBinding.rvState.visibility = View.GONE
                        activitySettingEditInfoBinding.editState.setText(
                            stateList.get(position).getTitle().toString()
                        )

                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })
        )

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.edit_country -> {
                activitySettingEditInfoBinding.editCountry.visibility = View.GONE
                activitySettingEditInfoBinding.rvCountry.visibility = View.VISIBLE
            }
            R.id.edit_state -> {
                if (stateList.size == 0) {

                } else {
                    activitySettingEditInfoBinding.editState.visibility = View.GONE
                    activitySettingEditInfoBinding.rvState.visibility = View.VISIBLE
                }
            }
            R.id.rl_back -> {
                finish()
                overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            }
            R.id.rl_update -> {

                country = activitySettingEditInfoBinding.editCountry.text.toString()
                state = activitySettingEditInfoBinding.editState.text.toString()
                first_name = activitySettingEditInfoBinding.editFirstname.text.toString()
                last_name = activitySettingEditInfoBinding.editLastname.text.toString()
                gender = "Male"
                pronon = activitySettingEditInfoBinding.editPronoun.text.toString()
                race = activitySettingEditInfoBinding.editRace.text.toString()
                dob = activitySettingEditInfoBinding.editDate.text.toString()
                mobile = activitySettingEditInfoBinding.editMobile.text.toString()

                if (country.equals("") && state.equals("") && dob.equals("") && first_name.equals("") && last_name.equals(
                        ""
                    ) && gender.equals("")
                    && pronon.equals("") && race.equals("") && mobile.equals("")
                ) {
                    ViewUtils.showSnackBar(
                        activitySettingEditInfoBinding.getRoot(),
                        this,
                        "Please fill all field"
                    )
                } else if (country.equals("")) {
                    ViewUtils.showSnackBar(
                        activitySettingEditInfoBinding.getRoot(),
                        this,
                        "Enter email"
                    )
                } else if (state.equals("")) {
                    ViewUtils.showSnackBar(
                        activitySettingEditInfoBinding.getRoot(),
                        this,
                        "Enter Password"
                    )
                } else if (first_name.equals("")) {
                    ViewUtils.showSnackBar(
                        activitySettingEditInfoBinding.getRoot(),
                        this,
                        "Enter First name"
                    )
                } else if (last_name.equals("")) {
                    ViewUtils.showSnackBar(
                        activitySettingEditInfoBinding.getRoot(),
                        this,
                        "Enter Last name"
                    )
                } else if (dob.equals("")) {
                    ViewUtils.showSnackBar(
                        activitySettingEditInfoBinding.getRoot(),
                        this,
                        "Select Date"
                    )
                } else if (gender.equals("")) {
                    ViewUtils.showSnackBar(
                        activitySettingEditInfoBinding.getRoot(),
                        this,
                        "Select Gender"
                    )
                } else if (pronon.equals("")) {
                    ViewUtils.showSnackBar(
                        activitySettingEditInfoBinding.getRoot(),
                        this,
                        "Enter Pronoun"
                    )
                } else if (race.equals("")) {
                    ViewUtils.showSnackBar(
                        activitySettingEditInfoBinding.getRoot(),
                        this,
                        "Enter Race"
                    )
                } else if (mobile.equals("")) {
                    ViewUtils.showSnackBar(
                        activitySettingEditInfoBinding.getRoot(),
                        this,
                        "Enter Mobile Number"
                    )
                } else {

                    activitySettingEditInfoBinding.isProgressShown = true


                    val jsonObj2 = JSONObject()

                    jsonObj2.put("first_name", first_name)
                    jsonObj2.put("last_name", last_name)
                    jsonObj2.put("state", "")
                    jsonObj2.put("country", country)
                    jsonObj2.put("phone_number", mobile)
                    jsonObj2.put("prefered_pronoun", pronon)
                    jsonObj2.put("race", race)
                    jsonObj2.put("dob", dob)
                    jsonObj2.put("gender", gender)
                    Log.e("datata", jsonObj2.toString())

                    val mediaType =
                        MediaType.parse("application/json; charset=utf-8")

                    val body =
                        RequestBody.create(mediaType, jsonObj2.toString())


                    updateProfileViewModel.init(
                        access_token,
                        body
                    )


                }
            }
        }
    }

    private fun subscribeObserver() {

        //get Profile
        if (getProfileViewModel != null) { //subscriber for get bank detail
            getProfileViewModel.observerServerResponse().observe(
                this,
                androidx.lifecycle.Observer<GetProfileExample> { response: GetProfileExample? ->

                    if (getProfileViewModel.observerServerResponse().value != null) {
                        var response: GetProfileExample =
                            getProfileViewModel.observerServerResponse().value!!
                        if (response.getSuccess() == true) {


                            response.getData()?.getUser()?.getFirstName()?.let {
                                activitySettingEditInfoBinding.editFirstname.setText(it)
                            }
                            response.getData()?.getUser()?.getLastName()?.let {
                                activitySettingEditInfoBinding.editLastname.setText(it)
                            }
                            response.getData()?.getUser()?.getGender()?.let {
                                activitySettingEditInfoBinding.editGender.setText(it)
                            }
                            response.getData()?.getUser()?.getEmail()?.let {
                                activitySettingEditInfoBinding.editEmail.setText(it)
                            }
                            response.getData()?.getUser()?.getPhoneNumber()?.let {
                                activitySettingEditInfoBinding.editMobile.setText(it)
                            }
                            response.getData()?.getUser()?.getDob()?.let {
                                activitySettingEditInfoBinding.editDate.setText(it)
                            }
                            response.getData()?.getUser()?.getState()?.let {
                                activitySettingEditInfoBinding.editState.setText(it)
                            }
                            response.getData()?.getUser()?.getCountry()?.let {
                                activitySettingEditInfoBinding.editCountry.setText(it)
                            }




                        }

                    }
                })


            //subscriber for loader
            getProfileViewModel.getLoading()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {

                            if (!aBoolean) {

                                activitySettingEditInfoBinding.setIsProgressShown(false)
                            }
                        }


                    })
            //subscriber for message
            getProfileViewModel.getMessage()
                ?.observe(this, androidx.lifecycle.Observer<String> { s: String? ->
                    if (s != null) {
                        if (!TextUtils.isEmpty(s)) {
                            ViewUtils.showSnackBar(
                                activitySettingEditInfoBinding.getRoot(),
                                this,
                                s
                            )
                        }
                    }
                })
            //subscriber for message
            getProfileViewModel.getIsDataFound()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {
                            if (!aBoolean) {
                                ViewUtils.showSnackBar(
                                    activitySettingEditInfoBinding.getRoot(),
                                    this,
                                    "Something went wrong"
                                )
                            }
                        }


                    })

        }

        //update profile
        if (updateProfileViewModel != null) { //subscriber for get bank detail
            updateProfileViewModel.observerServerResponse().observe(
                this,
                androidx.lifecycle.Observer<RegisterExample> { response: RegisterExample? ->

                    if (updateProfileViewModel.observerServerResponse().value != null) {
                        var response: RegisterExample =
                            updateProfileViewModel.observerServerResponse().value!!
                        if (response.getSuccess() == true) {

                            finish()
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                        }

                    }
                })


            //subscriber for loader
            updateProfileViewModel.getLoading()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {

                            if (!aBoolean) {

                                activitySettingEditInfoBinding.setIsProgressShown(false)
                            }
                        }


                    })
            //subscriber for message
            updateProfileViewModel.getMessage()
                ?.observe(this, androidx.lifecycle.Observer<String> { s: String? ->
                    if (s != null) {
                        if (!TextUtils.isEmpty(s)) {
                            ViewUtils.showSnackBar(
                                activitySettingEditInfoBinding.getRoot(),
                                this,
                                s
                            )
                        }
                    }
                })
            //subscriber for message
            updateProfileViewModel.getIsDataFound()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {
                            if (!aBoolean) {
                                ViewUtils.showSnackBar(
                                    activitySettingEditInfoBinding.getRoot(),
                                    this,
                                    "Something went wrong"
                                )
                            }
                        }


                    })

        }


        //country list
        if (countryViewModel != null) { //subscriber for get bank detail
            countryViewModel.observerServerResponse().observe(
                this,
                androidx.lifecycle.Observer<CountryExample> { response: CountryExample? ->

                    if (countryViewModel.observerServerResponse().value != null) {
                        var response: CountryExample =
                            countryViewModel.observerServerResponse().value!!
                        if (response.getSuccess() == true) {
                            countrylist = response.getData()!!.getCountries() as List<CountriesData>
                            countryAdapter =
                                CountryAdapter(this@SettingEditInfo, countrylist)

                            val linearLayoutManager = LinearLayoutManager(
                                this@SettingEditInfo, LinearLayoutManager.VERTICAL,
                                false
                            )
                            activitySettingEditInfoBinding.rvCountry.setLayoutManager(
                                linearLayoutManager
                            )
                            activitySettingEditInfoBinding.rvCountry.setAdapter(countryAdapter)
                        }

                    }
                })


            //subscriber for loader
            countryViewModel.getLoading()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {

                            if (!aBoolean) {

                                activitySettingEditInfoBinding.setIsProgressShown(false)
                            }
                        }


                    })
            //subscriber for message
            countryViewModel.getMessage()
                ?.observe(this, androidx.lifecycle.Observer<String> { s: String? ->
                    if (s != null) {
                        if (!TextUtils.isEmpty(s)) {
                            ViewUtils.showSnackBar(
                                activitySettingEditInfoBinding.getRoot(),
                                this,
                                s
                            )
                        }
                    }
                })
            //subscriber for message
            countryViewModel.getIsDataFound()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {
                            if (!aBoolean) {
                                ViewUtils.showSnackBar(
                                    activitySettingEditInfoBinding.getRoot(),
                                    this,
                                    "Something went wrong"
                                )
                            }
                        }


                    })

        }

        //state list
        if (stateViewModel != null) { //subscriber for get bank detail
            stateViewModel.observerServerResponse().observe(
                this,
                androidx.lifecycle.Observer<StateExample> { response: StateExample? ->

                    if (stateViewModel.observerServerResponse().value != null) {
                        var response: StateExample =
                            stateViewModel.observerServerResponse().value!!
                        if (response.getSuccess() == true) {
                            stateList = response.getData()!!.getStates() as List<StatesData>
                            stateAdapter =
                                StateAdapter(this@SettingEditInfo, stateList)

                            val linearLayoutManager = LinearLayoutManager(
                                this@SettingEditInfo, LinearLayoutManager.VERTICAL,
                                false
                            )
                            activitySettingEditInfoBinding.rvState.setLayoutManager(
                                linearLayoutManager
                            )
                            activitySettingEditInfoBinding.rvState.setAdapter(stateAdapter)
                        }

                    }
                })


            //subscriber for loader
            stateViewModel.getLoading()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {

                            if (!aBoolean) {

                                activitySettingEditInfoBinding.setIsProgressShown(false)
                            }
                        }


                    })
            //subscriber for message
            stateViewModel.getMessage()
                ?.observe(this, androidx.lifecycle.Observer<String> { s: String? ->
                    if (s != null) {
                        if (!TextUtils.isEmpty(s)) {
                            ViewUtils.showSnackBar(
                                activitySettingEditInfoBinding.getRoot(),
                                this,
                                s
                            )
                        }
                    }
                })
            //subscriber for message
            stateViewModel.getIsDataFound()
                ?.observe(
                    this,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {
                            if (!aBoolean) {
                                ViewUtils.showSnackBar(
                                    activitySettingEditInfoBinding.getRoot(),
                                    this,
                                    "Something went wrong"
                                )
                            }
                        }


                    })

        }


    }

    interface ClickListener {
        fun onClick(view: View?, position: Int)
        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(
        context: Context?,
        recycleView: RecyclerView,
        private val clicklistener: ClickListener?
    ) :
        OnItemTouchListener {
        private val gestureDetector: GestureDetector
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val child = rv.findChildViewUnder(e.getX(), e.getY())
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

        init {
            gestureDetector = GestureDetector(
                context,
                object : GestureDetector.SimpleOnGestureListener() {
                    override fun onSingleTapUp(e: MotionEvent?): Boolean {
                        return true
                    }

                    override fun onLongPress(e: MotionEvent) {
                        val child = recycleView.findChildViewUnder(e.getX(), e.getY())
                        if (child != null && clicklistener != null) {
                            clicklistener.onLongClick(
                                child,
                                recycleView.getChildAdapterPosition(child)
                            )
                        }
                    }
                })
        }
    }
}