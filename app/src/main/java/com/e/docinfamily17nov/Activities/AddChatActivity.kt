package com.e.docinfamily17nov.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.e.docinfamily17nov.Adapter.ChatListAdapter
import com.e.docinfamily17nov.Adapter.HistoryAdapter
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivityAddChatBinding
import com.e.docinfamily17nov.databinding.ActivityChatListBinding

class AddChatActivity : AppCompatActivity() {
    lateinit var activityAddChatBinding: ActivityAddChatBinding
    lateinit var chatListAdapter: ChatListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddChatBinding=DataBindingUtil.setContentView(this,R.layout.activity_add_chat)
        chatListAdapter = ChatListAdapter(this@AddChatActivity)
        val linearLayoutManager =
            GridLayoutManager(this@AddChatActivity,2, GridLayoutManager.VERTICAL,false)
        activityAddChatBinding.rvChat.layoutManager = linearLayoutManager
        activityAddChatBinding.rvChat.adapter = chatListAdapter
    }
}