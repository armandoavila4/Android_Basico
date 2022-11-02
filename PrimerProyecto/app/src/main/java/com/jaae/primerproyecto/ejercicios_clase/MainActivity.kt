package com.jaae.primerproyecto.ejercicios_clase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jaae.primerproyecto.R

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e(TAG, "Entre en onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "Entre en onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "Entre en onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "Entre en onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "Entre en onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "Entre en onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "Entre en onDestroy")
    }

}