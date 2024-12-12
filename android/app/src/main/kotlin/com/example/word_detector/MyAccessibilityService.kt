package com.example.word_detector

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class MyAccessibilityService : AccessibilityService() {
    companion object {
        private const val TAG = "WordDetectorService" // Use this tag for logging
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event?.let {
            if (event.eventType == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED ||
                event.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
                val text = when {
                    !event.text.isNullOrEmpty() -> event.text.joinToString(" ")
                    event.contentDescription != null -> event.contentDescription.toString()
                    else -> "No text or description found"
                }
                Log.d(TAG, "Captured text or description: $text")

                // Debugging statement
                //Log.d(TAG, "Captured text: $text")

                if (text.contains(Regex("M Ai", RegexOption.IGNORE_CASE))) {
                    Log.d(TAG, "WORD FOUND: NOFIL")
                    //Log.i(TAG, "Word Found: 'Nofil'")
                    sendNotification("The word 'M Ai' was detected on the screen.")
                }

                //if (text.contains("Nofil", ignoreCase = true)) {
                //    Log.i(TAG, "Word Found: 'M Ai'") // Print when the word is found
                //    sendNotification("The word 'M Ai' was detected on the screen.")
                //}
            }
        }
    }

    override fun onInterrupt() {
        Log.w(TAG, "Service was interrupted")
    }

    private fun sendNotification(message: String) {
        createNotificationChannel() // Ensure the notification channel exists
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(this, "word_detector_channel")
            .setContentTitle("Word Detected")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_notification) // Ensure this matches your resource
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager.notify(1, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Only for Android 8.0 or higher
            val channelId = "word_detector_channel"
            val channelName = "Word Detector Notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(channelId, channelName, importance)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}
