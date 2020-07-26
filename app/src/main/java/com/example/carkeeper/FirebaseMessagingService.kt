package com.example.carkeeper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.content.Context;
import android.os.Build
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseMessagingService : FirebaseMessagingService() {
  override fun onMessageReceived(remoteMessage: RemoteMessage) {
    Log.d("Tmp", "From: ${remoteMessage.from}")
    remoteMessage.notification?.body?.let {
      remoteMessage.notification?.title?.let { it1 -> sendNotification(it1, it) }
    }
  }

  override fun onNewToken(token: String) {
    val db = Firebase.firestore
    db.collection("users").document(FirebaseAuth.getInstance()
        .currentUser?.uid.toString()).update("registration_token", token)
    Log.d("New token", token)
  }

  private fun sendNotification(messageTitle: String, messageBody: String) {
    val intent = Intent(this, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
        PendingIntent.FLAG_ONE_SHOT)

    val channelId = "default_channel_id"
    val notificationBuilder = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(messageTitle)
        .setContentText(messageBody)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)

    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Since android Oreo notification channel is needed.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(channelId,
          "Channel human readable title",
          NotificationManager.IMPORTANCE_DEFAULT)
      notificationManager.createNotificationChannel(channel)
    }

    notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
  }
}