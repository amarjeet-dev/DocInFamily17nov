package com.e.docinfamily17nov.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.e.docinfamily17nov.Adapter.ChatListAdapter
import com.e.docinfamily17nov.R
import com.e.docinfamily17nov.databinding.ActivityChatListBinding

class ChatListActivity : AppCompatActivity() {
    lateinit var activityChatListBinding: ActivityChatListBinding
    lateinit var chatListAdapter: ChatListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityChatListBinding=DataBindingUtil.setContentView(this,R.layout.activity_chat_list)
        chatListAdapter = ChatListAdapter(this@ChatListActivity)
        val linearLayoutManager =
            GridLayoutManager(this@ChatListActivity,2, GridLayoutManager.VERTICAL,false)
        activityChatListBinding.rvChat.layoutManager = linearLayoutManager
        activityChatListBinding.rvChat.adapter = chatListAdapter
    }
}