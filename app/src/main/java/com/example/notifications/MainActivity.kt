package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //step 1
    val channel_ID = "ChannelID"
    val channel_Name = "ChannelName"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonNotification = findViewById<Button>(R.id.btnNotification)

        //calling the channel , which has been created (Step 3)
        createNotificationChannel()

        // Step 4, describe things what you wanted to give then build
        val notification = NotificationCompat.Builder(this, channel_ID)
            .setContentTitle("30 Days of Android Dev")
            .setContentText("Topic : Notification")
            .setSmallIcon(R.drawable.baseline_code_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
    }


    // a fun for creating channel (Step 2)
    //checking if build version is > Oreo, then we create channel else it exists by default
    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channel_ID, channel_Name, NotificationManager.IMPORTANCE_DEFAULT)
                .apply {
                    //we have different things, we can set according to our choice
                    description = "Notification Concept"    // give info
                    lightColor = Color.GREEN
                    enableLights(true)      // need to enable once the color is given
                }


            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }// created channel and given our parameters
        // We have created channel and manager through this fun
    }
}