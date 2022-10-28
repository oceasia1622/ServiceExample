package com.example.servicestest

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.servicestest.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBR()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity,
                MusicService::class.java)
            startService(intent)
        }
        binding.btnStop.setOnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity,
                MusicService::class.java)
            stopService(intent)
        }
        binding.btnRandomButton.setOnClickListener {
            startService(Intent(this, BoundService::class.java))
//            binding.btnRandomButton.text = myBoundService?.number.toString()
            binding.btnRandomButton.text = myAIDLService?.getNumber(45)
            startService(Intent(this, DownloadIntentService::class.java))
        }
    }

    private fun registerBR() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION_COMPLETE_DOWNLOAD)

        registerReceiver(broadcastR, intentFilter)
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        Log.d(TAG, "onResume: ${myBoundService?.number}")
    }

    private var myBoundService: BoundService? = null
    private var myAIDLService: ICommonPlayMusic? = null

    private val serviceConnection=
        object: ServiceConnection{
            override fun onServiceDisconnected(p0: ComponentName?) {
                myBoundService = null
                Log.d(TAG, "onServiceDisconnected: ")
            }

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
//                val binder: BoundService.MyBinder = p1 as BoundService.MyBinder
//                myBoundService = binder.getService()
                myAIDLService = ICommonPlayMusic.Stub.asInterface(p1)
                Log.d(TAG, "onServiceConnected: ")
            }
        }

//    private val callback = object : ICommonPlayMusic.Stub() {
//        override fun basicTypes(
//            anInt: Int,
//            aLong: Long,
//            aBoolean: Boolean,
//            aFloat: Float,
//            aDouble: Double,
//            aString: String?
//        ) {
//
//        }
//
//        override fun getNumber(seedInit: Int): String {
//
//        }
//
//    }


    val broadcastR = object: BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.d(TAG, "onReceive: ")
            if(p1?.action == ACTION_COMPLETE_DOWNLOAD) {
                binding.btnRandomButton.text = p1?.getStringExtra(EXTRA_DATA)
            }
        }

    }

}