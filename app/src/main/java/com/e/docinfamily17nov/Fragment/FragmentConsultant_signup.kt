package com.e.docinfamily17nov.Fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bruce.pickerview.popwindow.DatePickerPopWin
import com.e.docinfamily17nov.Activities.SignUpComplete
import com.e.docinfamily17nov.Model.LoginExample
import com.e.docinfamily17nov.Model.RegisterExample
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.Utils.ViewUtils
import com.e.docinfamily17nov.ViewModels.RegisterViewModel
import com.e.docinfamily17nov.ViewModels.SocialLoginViewModel
import com.e.docinfamily17nov.databinding.LayoutAspatientSignupBinding
import com.facebook.*
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class FragmentConsultant_signup(internal var consultant: String) : Fragment(),View.OnClickListener {
    lateinit var layoutaspatient: LayoutAspatientSignupBinding
    lateinit var registerViewModel: RegisterViewModel
    var email=""
    var first_name=""
    var last_name=""
    var dob=""
    var mobile=""
    var race=""
    var pronon=""
    var gender=""
    var password=""
    var check="0"
    lateinit var socialLoginViewModel: SocialLoginViewModel
    var loginButton: LoginButton? = null
    var loggedOut = false
    private var googleSignInButton: SignInButton? = null
    private var googleSignInClient: GoogleSignInClient? = null
    var callbackManager: CallbackManager? = null
    private var firebaseAuth: FirebaseAuth? = null
    var EMAIL_ADDRESS = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    companion object {

        fun newInstance(): FragmentConsultant_signup {
            return FragmentConsultant_signup("consultant")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutaspatient =
            LayoutAspatientSignupBinding.inflate(inflater, container, false)
        callbackManager = CallbackManager.Factory.create()
        registerViewModel= ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)
        socialLoginViewModel =
            ViewModelProvider(requireActivity()).get(SocialLoginViewModel::class.java)
        ClickListener()
        subscribeObserver()
        //social login
        social_login()
        return layoutaspatient.getRoot()
    }
    fun ClickListener() {
// signup click listener
        layoutaspatient.rlSignupcl.setOnClickListener(this)
        layoutaspatient.rlUncheck.setOnClickListener(this)
        layoutaspatient.editDate.setOnClickListener {
            val todayDate = Calendar.getInstance().time
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val todayString = formatter.format(todayDate)
            Log.e("today_string", todayString)
            val pickerPopWin = DatePickerPopWin.Builder(
                requireActivity()
            ) { year, month, day, dateDesc ->
                layoutaspatient.editDate.setText(""+year+"-"+month+"-"+day+"")

                Toast.makeText(
                    requireActivity(),
                    dateDesc,
                    Toast.LENGTH_SHORT
                ).show()
            }.textConfirm("CONFIRM") //text of confirm button
                .textCancel("CANCEL") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#009900")) //color of confirm button
                .minYear(1990) //min year in loop
                .maxYear(2550) // max year in loop
                .dateChose(todayString) // date chose when init popwindow
                .build()
            pickerPopWin.showPopWin(requireActivity())
        }
    }

    private fun subscribeObserver() {

        //getBankAccountDEtail
        if (registerViewModel != null) { //subscriber for get bank detail
            registerViewModel.observerServerResponse().observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer<RegisterExample> { response: RegisterExample? ->

                    if (registerViewModel.observerServerResponse().value != null) {
                        var response: RegisterExample =
                            registerViewModel.observerServerResponse().value!!
                        if (response.getSuccess() == true) {
                            Toast.makeText(
                                context,
                                response.getMessage().toString(),
                                Toast.LENGTH_SHORT
                            ).show()

                            var intent = Intent(requireActivity(), SignUpComplete::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                            requireActivity().overridePendingTransition(
                                R.anim.fadein,
                                R.anim.fadeout
                            )
                        }

                    }
                })


            //subscriber for loader
            registerViewModel.getLoading()
                ?.observe(
                    viewLifecycleOwner,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {

                            if (!aBoolean) {

                                layoutaspatient.setIsProgressShown(false)
                            }
                        }


                    })
            //subscriber for message
            registerViewModel.getMessage()
                ?.observe(viewLifecycleOwner, androidx.lifecycle.Observer<String> { s: String? ->
                    if (s != null) {
                        if (!TextUtils.isEmpty(s)) {
                            ViewUtils.showSnackBar(
                                layoutaspatient.getRoot(),
                                requireActivity(),
                                s
                            )
                        }
                    }
                })
            //subscriber for message
            registerViewModel.getIsDataFound()
                ?.observe(
                    viewLifecycleOwner,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {
                            if (!aBoolean) {
                                ViewUtils.showSnackBar(
                                    layoutaspatient.getRoot(),
                                    requireActivity(),
                                    "Something went wrong"
                                )
                            }
                        }


                    })

        }

        //social login detail
        if (socialLoginViewModel != null) { //subscriber for get bank detail
            socialLoginViewModel.observerServerResponse().observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer<LoginExample> { response: LoginExample? ->

                    if (socialLoginViewModel.observerServerResponse().value != null) {
                        var response: LoginExample =
                            socialLoginViewModel.observerServerResponse().value!!
                        if (response.getStatus() == true) {
                            var intent = Intent(requireActivity(), SignUpComplete::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                            requireActivity().overridePendingTransition(
                                R.anim.fadein,
                                R.anim.fadeout
                            )

                        }

                    }
                })


            //subscriber for loader
            socialLoginViewModel.getLoading()
                ?.observe(
                    viewLifecycleOwner,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {

                            if (!aBoolean) {

                                layoutaspatient.setIsProgressShown(false)
                            }
                        }


                    })
            //subscriber for message
            socialLoginViewModel.getMessage()
                ?.observe(viewLifecycleOwner, androidx.lifecycle.Observer<String> { s: String? ->
                    if (s != null) {
                        if (!TextUtils.isEmpty(s)) {
                            ViewUtils.showSnackBar(
                                layoutaspatient.getRoot(),
                                requireActivity(),
                                s
                            )
                        }
                    }
                })
            //subscriber for message
            socialLoginViewModel.getIsDataFound()
                ?.observe(
                    viewLifecycleOwner,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {
                            if (!aBoolean) {
                                ViewUtils.showSnackBar(
                                    layoutaspatient.getRoot(),
                                    requireActivity(),
                                    "Something went wrong"
                                )
                            }
                        }


                    })

        }
    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.rl_google -> {
                loginWithGoogle()
            }
            R.id.rl_fb -> {
                loginButton!!.performClick()
            }
            R.id.rl_uncheck->
            {
                if (check.equals("0")) {
                    layoutaspatient.ivUncheck.visibility = View.VISIBLE
                    check = "1"
                }else{
                    layoutaspatient.ivUncheck.visibility = View.GONE
                }
            }
            R.id.rl_signupcl -> {
                email = layoutaspatient.editEmail.text.toString()
                password = layoutaspatient.editPassword.text.toString()
                first_name = layoutaspatient.editFirstname.text.toString()
                last_name = layoutaspatient.editLastname.text.toString()
                gender = "Male"
                pronon = layoutaspatient.editPronoun.text.toString()
                race = layoutaspatient.editRace.text.toString()
                dob = layoutaspatient.editDate.text.toString()
                mobile = layoutaspatient.editMobile.text.toString()

                if (email.equals("") && password.equals("")&&dob.equals("") && first_name.equals("")&&last_name.equals("") && gender.equals("")
                    &&pronon.equals("") && race.equals("")&&mobile.equals("") ) {
                    ViewUtils.showSnackBar(
                        layoutaspatient.getRoot(),
                        requireActivity(),
                        "Please fill all field"
                    )
                } else if (email.equals("")) {
                    ViewUtils.showSnackBar(
                        layoutaspatient.getRoot(),
                        requireActivity(),
                        "Enter email"
                    )
                } else if (password.equals("")) {
                    ViewUtils.showSnackBar(
                        layoutaspatient.getRoot(),
                        requireActivity(),
                        "Enter Password"
                    )
                }
                else if (first_name.equals("")) {
                    ViewUtils.showSnackBar(
                        layoutaspatient.getRoot(),
                        requireActivity(),
                        "Enter First name"
                    )
                } else if (last_name.equals("")) {
                    ViewUtils.showSnackBar(
                        layoutaspatient.getRoot(),
                        requireActivity(),
                        "Enter Last name"
                    )
                }
                else if (dob.equals("")) {
                    ViewUtils.showSnackBar(
                        layoutaspatient.getRoot(),
                        requireActivity(),
                        "Select Date"
                    )
                } else if (gender.equals("")) {
                    ViewUtils.showSnackBar(
                        layoutaspatient.getRoot(),
                        requireActivity(),
                        "Select Gender"
                    )
                }
                else if (pronon.equals("")) {
                    ViewUtils.showSnackBar(
                        layoutaspatient.getRoot(),
                        requireActivity(),
                        "Enter Pronoun"
                    )
                } else if (race.equals("")) {
                    ViewUtils.showSnackBar(
                        layoutaspatient.getRoot(),
                        requireActivity(),
                        "Enter Race"
                    )
                }
                else if (mobile.equals("")) {
                    ViewUtils.showSnackBar(
                        layoutaspatient.getRoot(),
                        requireActivity(),
                        "Enter Mobile Number"
                    )
                }else {
                    if(EMAIL_ADDRESS.matcher(email.trim()).matches()) {
                        if(check.equals("1")) {
                            layoutaspatient.isProgressShown = true


                            val jsonObj2 = JSONObject()
                            jsonObj2.put("user_type", consultant)
                            jsonObj2.put("first_name", first_name)
                            jsonObj2.put("last_name", last_name)
                            jsonObj2.put("email", email)
                            jsonObj2.put("password", password)
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


                            registerViewModel.init(
                                body
                            )
                        }else{
                            ViewUtils.showSnackBar(
                                layoutaspatient.getRoot(),
                                requireActivity(),
                                "Please agree with Terms of use and Privacy Policy"
                            )
                        }
                    }else{
                        ViewUtils.showSnackBar(
                            layoutaspatient.getRoot(),
                            requireActivity(),
                            "Inavlid Email Pattern"
                        )
                    }
                }
            }

        }
    }

    fun social_login() {

        firebaseAuth = FirebaseAuth.getInstance()
        googleSignInButton = layoutaspatient.loginWithGoogle
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(resources.getString(R.string.web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        googleSignInButton!!.setOnClickListener {
            val signInIntent = googleSignInClient!!.getSignInIntent()
            startActivityForResult(signInIntent, 101)
        }

        loginButton = layoutaspatient.loginButton
        loginButton?.setFragment(this)
        loginButton?.setReadPermissions(Arrays.asList("email"))
        loginButton?.loginBehavior = LoginBehavior.WEB_ONLY

        loggedOut = AccessToken.getCurrentAccessToken() == null

        loginButton?.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                val loggedIn = AccessToken.getCurrentAccessToken() == null
                Log.d("API123", "$loggedIn ??")
                getUserProfile(AccessToken.getCurrentAccessToken())
            }

            override fun onCancel() {
            }

            override fun onError(exception: FacebookException) {
            }
        })
    }

    private fun getUserProfile(currentAccessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(
            currentAccessToken
        ) { `object`, response ->
            Log.d("TAG", `object`.toString())
            try {
                val first_name = `object`.getString("first_name")
                val email_id = `object`.getString("email")

                layoutaspatient.isProgressShown = true

                val jsonObj2 = JSONObject()
                jsonObj2.put("email", email_id)
                jsonObj2.put("name", first_name)
                jsonObj2.put("user_type", "patient")
                Log.e("datata", jsonObj2.toString())


                val mediaType =
                    MediaType.parse("application/json; charset=utf-8")

                val body =
                    RequestBody.create(mediaType, jsonObj2.toString())
                socialLoginViewModel.init(body)

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "first_name,last_name,email,id")
        request.parameters = parameters
        request.executeAsync()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            try {
                // The Task returned from this call is always completed, no need to attach
                // a listener.
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java)
                Log.e("user_id", account!!.displayName + "")
                onLoggedIn(account)
            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                Log.e("signInResult", e.statusCode.toString())
            }
        } else {
            callbackManager!!.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun onLoggedIn(googleSignInAccount: GoogleSignInAccount) {
        val name = googleSignInAccount.displayName.toString()
        val email_id = googleSignInAccount.email.toString()
        layoutaspatient.isProgressShown = true

        val jsonObj2 = JSONObject()
        jsonObj2.put("email", email_id)
        jsonObj2.put("name", name)
        jsonObj2.put("user_type", consultant)
        Log.e("datata", jsonObj2.toString())


        val mediaType =
            MediaType.parse("application/json; charset=utf-8")

        val body =
            RequestBody.create(mediaType, jsonObj2.toString())
        socialLoginViewModel.init(body)
    }

    fun loginWithGoogle() {
        try {
            val signInIntent: Intent = googleSignInClient!!.getSignInIntent()
            startActivityForResult(signInIntent, 101)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}