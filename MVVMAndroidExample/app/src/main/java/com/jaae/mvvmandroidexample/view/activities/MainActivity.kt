package com.jaae.mvvmandroidexample.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaae.mvvmandroidexample.R
import com.jaae.mvvmandroidexample.databinding.ActivityDetailBinding
import com.jaae.mvvmandroidexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private lateinit var binding2 : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ejemplo.text = ""
        binding2.tvDetalleName.text= "123"
    }

    override fun onResume() {
        super.onResume()

        binding.ejemplo.text = "dsdasdasdad"
    }
}