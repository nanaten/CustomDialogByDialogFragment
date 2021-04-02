package com.nanaten.customdialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.nanaten.customdialog.databinding.LayoutCustomDialogBinding

class CustomDialog : DialogFragment() {
    private var title: String = ""
    private var message: String = ""
    private var positiveButtonText: String = ""
    private var negativeButtonText: String = ""

    companion object {
        private const val DEFAULT_POSITIVE_BUTTON_TEXT = "OK"
        private const val DEFAULT_NEGATIVE_BUTTON_TEXT = "キャンセル"
        private const val TITLE_KEY = "TitleKey"
        private const val MESSAGE_KEY = "MessageKey"
        private const val REQUEST_POSITIVE_BUTTON_KEY = "RequestPositiveButtonKey"
        private const val REQUEST_NEGATIVE_BUTTON_KEY = "RequestNegativeButtonKey"
        private const val POSITIVE_BUTTON_TEXT_KEY = "PositiveButtonTextKey"
        private const val NEGATIVE_BUTTON_TEXT_KEY = "NegativeButtonTextKey"
    }

    class Builder(private val fragment: Fragment) {
        private val bundle = Bundle()

        fun setTitle(title: String): Builder {
            return this.apply {
                bundle.putString(TITLE_KEY, title)
            }
        }

        fun setMessage(message: String): Builder {
            return this.apply {
                bundle.putString(MESSAGE_KEY, message)
            }
        }

        fun setPositiveButton(buttonText: String, listener: (() -> Unit)? = null): Builder {
            return this.apply {
                bundle.putString(POSITIVE_BUTTON_TEXT_KEY, buttonText)
                fragment.apply {
                    this.childFragmentManager
                        .setFragmentResultListener(
                            REQUEST_POSITIVE_BUTTON_KEY,
                            this.viewLifecycleOwner
                        ) { _, _ ->
                            listener?.invoke()
                        }
                }
            }
        }

        fun setNegativeButton(buttonText: String, listener: (() -> Unit)? = null): Builder {
            return this.apply {
                bundle.putString(NEGATIVE_BUTTON_TEXT_KEY, buttonText)
                fragment.childFragmentManager
                    .setFragmentResultListener(
                        REQUEST_NEGATIVE_BUTTON_KEY,
                        fragment.viewLifecycleOwner
                    ) { _, _ ->
                        listener?.invoke()
                    }
            }
        }

        fun build(): CustomDialog {
            return CustomDialog().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        // ダイアログの背景を透過にする
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        arguments?.let {
            title = it.getString(TITLE_KEY, "")
            message = it.getString(MESSAGE_KEY, "")
            positiveButtonText =
                it.getString(POSITIVE_BUTTON_TEXT_KEY, DEFAULT_POSITIVE_BUTTON_TEXT)
            negativeButtonText =
                it.getString(NEGATIVE_BUTTON_TEXT_KEY, DEFAULT_NEGATIVE_BUTTON_TEXT)
        }
        val binding = LayoutCustomDialogBinding.inflate(requireActivity().layoutInflater)
        binding.title.text = title
        binding.message.text = message
        binding.positiveButton.text = positiveButtonText
        binding.negativeButton.text = negativeButtonText
        binding.positiveButton.setOnClickListener {
            dismiss()
            setFragmentResult(
                REQUEST_POSITIVE_BUTTON_KEY,
                bundleOf()
            )
        }
        binding.negativeButton.setOnClickListener {
            dismiss()
            setFragmentResult(
                REQUEST_NEGATIVE_BUTTON_KEY,
                bundleOf()
            )
        }
        dialog.setContentView(binding.root)
        return dialog
    }
}
