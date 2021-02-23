package com.nanaten.customdialog

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nanaten.customdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.dialogButton.setOnClickListener {
            CustomDialog.Builder()
                .setTitle("タイトル")
                .setMessage("カスタムメッセージ")
                .setPositiveButton("はい") { showToast("はいが押されました") }
                .setNegativeButton("いいえ") { showToast("いいえが押されました") }
                .build()
                .show(supportFragmentManager, CustomDialog::class.simpleName)
        }
        setContentView(binding.root)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}