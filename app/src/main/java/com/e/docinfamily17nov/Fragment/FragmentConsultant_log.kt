package com.e.docinfamily17nov.Fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.docinfamily17nov.Activities.BaseActivity
import com.e.docinfamily17nov.Activities.ResetPasswordActivity
import com.e.docinfamily17nov.Activities.SignupSectionActivity
import com.e.docinfamily17nov.Model.LoginExample
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.Utils.DocAppSharedPreference
import com.e.docinfamily17nov.Utils.ViewUtils
import com.e.docinfamily17nov.ViewModels.LoginViewModel
import com.e.docinfamily17nov.ViewModels.SocialLoginViewModel
import com.e.docinfamily17nov.databinding.LayoutAspatientLogBinding
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
import java.util.*

class FragmentConsultant_log() : Fragment(),View.OnClickListener {

    lateinit var layoutaspatient: LayoutAspatientLogBinding
    lateinit var loginViewModel: LoginViewModel
    lateinit var socialLoginViewModel: SocialLoginViewModel
    var email = ""
    var password = ""
    lateinit var docAppSharedPreference: DocAppSharedPreference
    var access_token = ""
    var loginButton: LoginButton? = null
    var loggedOut = false
    private var googleSignInButton: SignInButton? = null
    private var googleSignInClient: GoogleSignInClient? = null
    var callbackManager: CallbackManager? = null
    private var firebaseAuth: FirebaseAuth? = null

    companion object {

        fun newInstance(): FragmentPatient_log {
            return FragmentPatient_log()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        layoutaspatient =
            LayoutAspatientLogBinding.inflate(inflater, container, false)
        callbackManager = CallbackManager.Factory.create()
        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        socialLoginViewModel =
            ViewModelProvider(requireActivity()).get(SocialLoginViewModel::class.java)
        //shared preference
        docAppSharedPreference = DocAppSharedPreference.getInstance(requireActivity())!!
//edit text focus listener
        subscribeObserver()
        //click listener
        ClickListener()
        //social login
        social_login()
        return layoutaspatient.getRoot()
    }

    fun ClickListener() {
        layoutaspatient.rlGoogle.setOnClickListener(this)
        layoutaspatient.rlFb.setOnClickListener(this)
        layoutaspatient.ivLogin.setOnClickListener(this)
        layoutaspatient.tvForgot.setOnClickListener {
            val intent = Intent(requireActivity(), ResetPasswordActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout)

        }

        layoutaspatient.rlSignup.setOnClickListener {
            val intent = Intent(requireActivity(), SignupSectionActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout)

        }

    }


    private fun subscribeObserver() {

        //login detail
        if (loginViewModel != null) { //subscriber for get bank detail
            loginViewModel.observerServerResponse().observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer<LoginExample> { response: LoginExample? ->

                    if (loginViewModel.observerServerResponse().value != null) {
                        var response: LoginExample =
                            loginViewModel.observerServerResponse().value!!
                        if (response.getStatus() == true) {
                            Toast.makeText(
                                context,
                                response.getMessage().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            access_token = response.getAccessToken().toString();
                            if (access_token.equals("")) {

                            } else {
                                docAppSharedPreference.access_token = access_token
                            }
                            val intent = Intent(requireActivity(), BaseActivity::class.java)
                            startActivity(intent)
                            requireActivity().finishAffinity()
                            requireActivity().overridePendingTransition(
                                R.anim.fadein,
                                R.anim.fadeout
                            )
                        }

                    }
                })


            //subscriber for loader
            loginViewModel.getLoading()
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
            loginViewModel.getMessage()
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
            loginViewModel.getIsDataFound()
                ?.observe(
                    viewLifecycleOwner,
                    androidx.lifecycle.Observer<Boolean> { aBoolean: Boolean? ->
                        if (aBoolean != null) {
                            if (!aBoolean) {
                                ViewUtils.showSnackBar(
                                    layoutaspatient.getRoot(),
                                    requireActivity(),
                                    "The email or password is incorrect."
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
                            Toast.makeText(
                                context,
                                response.getMessage().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            access_token = response.getAccessToken().toString();
                            if (access_token.equals("")) {

                            } else {
                                docAppSharedPreference.access_token = access_token
                            }
                            val intent = Intent(requireActivity(), BaseActivity::class.java)
                            startActivity(intent)
                            requireActivity().finishAffinity()
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
        when (v!!.id) {
            R.id.rl_google -> {
                loginWithGoogle()
            }
            R.id.rl_fb -> {
                loginButton!!.performClick()
            }
            R.id.iv_login -> {
                email = layoutaspatient.editUsername.text.toString()
                password = layoutaspatient.editPassword.text.toString()
                if (email.equals("") && password.equals("")) {

                } else if (email.equals("")) {

                } else if (password.equals("")) {

                } else {
                    layoutaspatient.isProgressShown = true

                    val jsonObj2 = JSONObject()
                    jsonObj2.put("email", email)
                    jsonObj2.put("password", password)

                    Log.e("datata", jsonObj2.toString())


                    val mediaType =
                        MediaType.parse("application/json; charset=utf-8")

                    val body =
                        RequestBody.create(mediaType, jsonObj2.toString())
                    loginViewModel.init(body)
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
        jsonObj2.put("user_type", "patient")
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