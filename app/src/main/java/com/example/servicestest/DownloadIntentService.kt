package com.example.servicestest

import android.app.IntentService
import android.content.Intent

const val ACTION_COMPLETE_DOWNLOAD= "com.example.servicetest.DOWNLOAD"
const val EXTRA_DATA= "com.example.servicetest.EXTRA"

class DownloadIntentService: IntentService("Download") {
    override fun onHandleIntent(p0: Intent?) {
        //todo after Downloading
        val dataRepresentation = "Hola"

        val intent = Intent()
        intent.action = ACTION_COMPLETE_DOWNLOAD
        intent.putExtra(EXTRA_DATA, dataRepresentation)
        sendBroadcast(intent)
    }
}