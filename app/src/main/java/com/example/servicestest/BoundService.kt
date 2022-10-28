package com.example.servicestest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlin.random.Random

private const val TAG = "BoundService"
class BoundService: Service() {

    private var binder: IBinder = MyBinder()

    var number = 0

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG, "onBind: ")
        number = Random.nextInt(10)
        return binderAidl
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d(TAG, "onStartCommand: ")
        number = Random.nextInt(10)

        return START_NOT_STICKY
    }

    private val binderAidl = object: ICommonPlayMusic.Stub(){
        override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {

        }

        override fun getNumber(seedInit: Int): String {
            number = Random.nextInt(seedInit)
            return number.toString()
        }

    }


   inner class MyBinder : Binder() {
        fun getService(): BoundService {
           return this@BoundService
        }
    }
}