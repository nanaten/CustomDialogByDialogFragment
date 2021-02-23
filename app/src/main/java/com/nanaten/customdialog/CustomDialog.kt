package com.nanaten.customdialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nanaten.customdialog.databinding.LayoutCustomDialogBinding

class CustomDialog() : DialogFragment() {
    private var title: String = ""
    private var message: String = ""

    companion object {
        fun create(title: String, message: String): CustomDialog {
            return CustomDialog().apply {
                val bundle = Bundle()
                bundle.putString("TitleKey", title)
                bundle.putString("MessageKey", message)
                arguments = bundle
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        // ダイアログの背景を透過にする
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        arguments?.let {
            title = it.getString("TitleKey", "")
            message = it.getString("MessageKey", "")
        }
        val binding = LayoutCustomDialogBinding.inflate(requireActivity().layoutInflater)
        binding.title.text = title
        binding.message.text = message
        binding.positiveButton.text = "OK"
        binding.negativeButton.text = "キャンセル"
        dialog.setContentView(binding.root)
        return dialog
    }
}