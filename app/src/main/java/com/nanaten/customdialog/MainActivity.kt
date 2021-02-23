package com.nanaten.customdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.nanaten.customdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.dialogButton.setOnClickListener {
            CustomDialog().show(supportFragmentManager, CustomDialog::class.simpleName)
        }
        setContentView(binding.root)
    }
}