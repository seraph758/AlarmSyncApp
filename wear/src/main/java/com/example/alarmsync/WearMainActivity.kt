package com.example.alarmsync

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alarmsync.databinding.ActivityWearMainBinding
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.Wearable

class WearMainActivity : AppCompatActivity(), MessageClient.OnMessageReceivedListener {

    private lateinit var binding: ActivityWearMainBinding
    private val path = "/alarm_sync"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWearMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Wearable.getMessageClient(this).addListener(this)
    }

    override fun onMessageReceived(event: MessageEvent) {
        if (event.path == path) {
            val msg = String(event.data)
            runOnUiThread {
                binding.textView.text = "Received: $msg"
            }
        }
    }
}
