package com.jaae.mvvmandroidexample.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaae.mvvmandroidexample.R
import com.jaae.mvvmandroidexample.databinding.ActivityUserListBinding
import com.jaae.mvvmandroidexample.view.fragments.ListFragment

class UserListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerList, ListFragment())
            .commit()
    }
}