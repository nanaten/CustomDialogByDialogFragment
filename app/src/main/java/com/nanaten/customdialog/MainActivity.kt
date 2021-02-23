package com.nanaten.customdialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nanaten.customdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.dialogButton.setOnClickListener {
            CustomDialog
                .create("カスタムタイトル", "カスタムメッセージ")
                .show(supportFragmentManager, CustomDialog::class.simpleName)
        }
        setContentView(binding.root)
    }
}