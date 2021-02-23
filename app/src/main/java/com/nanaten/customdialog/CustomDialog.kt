package com.nanaten.customdialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.nanaten.customdialog.databinding.LayoutCustomDialogBinding

class CustomDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        // ダイアログの背景を透過にする
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val binding = LayoutCustomDialogBinding.inflate(requireActivity().layoutInflater)
        binding.title.text = "タイトル"
        binding.message.text = "メッセージ"
        binding.positiveButton.text = "OK"
        binding.negativeButton.text = "キャンセル"
        dialog.setContentView(binding.root)
        return dialog
    }
}