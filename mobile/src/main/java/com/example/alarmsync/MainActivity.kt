package com.example.alarmsync

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alarmsync.databinding.ActivityMainBinding
import com.google.android.gms.wearable.Wearable
import com.google.android.gms.wearable.MessageClient

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val path = "/alarm_sync"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSendAlarm.setOnClickListener {
            val messageClient: MessageClient = Wearable.getMessageClient(this)
            val msg = "Alarm set at ${System.currentTimeMillis()}"
            messageClient.sendMessage("wear", path, msg.toByteArray())
            binding.textView.text = "Sent alarm to Wear OS"
        }
    }
}
