package com.nanaten.customdialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nanaten.customdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, MainFragment())
            .commit()

        setContentView(binding.root)
    }

}