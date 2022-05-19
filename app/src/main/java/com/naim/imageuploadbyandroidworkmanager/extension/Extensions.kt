package com.naim.imageuploadbyandroidworkmanager.extension

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.naim.imageuploadbyandroidworkmanager.R

fun NotificationManager.createChannelForOreo(channelId: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            channelId,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.enableLights(true)
        channel.lightColor = R.color.teal_700
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 300)
        this.createNotificationChannel(channel)
    }
}
