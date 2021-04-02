package com.nanaten.customdialog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nanaten.customdialog.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val binding = FragmentMainBinding.bind(view)

        binding.dialogButton.setOnClickListener {
            CustomDialog.Builder(this)
                .setTitle("タイトル")
                .setMessage("カスタムメッセージ")
                .setPositiveButton("はい") { showToast("はいが押されました") }
                .setNegativeButton("いいえ") { showToast("いいえが押されました") }
                .build()
                .show(childFragmentManager, CustomDialog::class.simpleName)
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}