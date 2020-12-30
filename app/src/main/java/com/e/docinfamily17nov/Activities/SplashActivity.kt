package com.e.docinfamily17nov.Activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.Utils.DocAppSharedPreference
import java.util.*

class SplashActivity : AppCompatActivity() {
    private var timer: Timer? = null
    private var i = 0
    var access_token=""
    lateinit var docAppSharedPreference: DocAppSharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        //shared preference
        //DocAppSharedPreference= DocAppSharedPreference.
        docAppSharedPreference = DocAppSharedPreference.getInstance(this)
    DocAppSharedPreference.printHashKey(this)
        access_token=docAppSharedPreference.access_token
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {

                timer!!.cancel()
                if (access_token.equals("")) {
                    val intent = Intent(this@SplashActivity, LoginRegisterActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                    finish()
                } else {
                    val intent = Intent(this@SplashActivity, BaseActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                    finish()
                }
            }
        }, 2000)
    }


}