package com.example.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //step 1
    val channel_ID = "ChannelID"
    val channel_Name = "ChannelName"
    val notificationID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //step 5
        val notificationManager = NotificationManagerCompat.from(this)
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


        //step 6
        buttonNotification.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                Toast.makeText(this, "Please Allow Permissions", Toast.LENGTH_SHORT).show()
            }
            notificationManager.notify(notificationID, notification)
        }
    }


    // a fun for creating channel (Step 2)
    //checking if build version is > Oreo, then we create channel else it exists by default
    private fun     createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channel_ID, channel_Name, NotificationManager.IMPORTANCE_DEFAULT)
                .apply {
                    //we have different things, we can set according to our choice
                    description = "Notification Channel"    // give info
                    lightColor = Color.GREEN
                    enableLights(true)      // need to enable once the color is given
                }


            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }// created channel and given our parameters
        // We have created channel and manager through this fun
    }
}

// Notes
// NotificationManagerCompat is an enhanced version of Notification Manager,