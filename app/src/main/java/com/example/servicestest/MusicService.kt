package com.example.servicestest

import android.app.IntentService
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

private const val TAG = "MusicService"
class MusicService : IntentService("MusicService") {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
//        mediaPlayer.setDataSource(
//            get
//        )
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onHandleIntent(p0: Intent?) {
        Log.d(TAG, "onHandleIntent: ")
        mediaPlayer.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d(TAG, "onStartCommand: ")
        mediaPlayer.start()
        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
        mediaPlayer.stop()
    }
}