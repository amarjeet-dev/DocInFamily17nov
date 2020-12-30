package com.e.docinfamily17nov.Activities

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivityLinkBinding

class LinkActivity : AppCompatActivity() {
    var tag: String? = null
    var link = "https://www.google.com/"
    lateinit var wv_view: WebView
    lateinit var linkBinding: ActivityLinkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linkBinding = DataBindingUtil.setContentView(this, R.layout.activity_link)
        tag=intent.getStringExtra("name")
        linkBinding.tvTitle.setText(tag)
        linkBinding.rlBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }
        wv_view = findViewById<View>(R.id.wv_view) as WebView
//
//        wv_view = WebView(this)
//        wv_view!!.settings.javaScriptEnabled = true
        wv_view!!.loadUrl(link)

    }

    class myWebClient : WebViewClient() {
        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon)
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            // TODO Auto-generated method stub

            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url)

        }
    }

    // To handle "Back" key press event for WebView to go back to previous screen.
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && wv_view!!.canGoBack()) {
            wv_view!!.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}