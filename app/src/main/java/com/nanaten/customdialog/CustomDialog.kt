package com.nanaten.customdialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nanaten.customdialog.databinding.LayoutCustomDialogBinding
import java.io.Serializable

class CustomDialog() : DialogFragment() {
    private var title: String = ""
    private var message: String = ""
    private var positiveButtonText: String = ""
    private var negativeButtonText: String = ""
    private var positiveButtonClickListener: ButtonClickListener? = null
    private var negativeButtonClickListener: ButtonClickListener? = null

    companion object {
        private const val DEFAULT_POSITIVE_BUTTON_TEXT = "OK"
        private const val DEFAULT_NEGATIVE_BUTTON_TEXT = "キャンセル"
        private const val TITLE_KEY = "TitleKey"
        private const val MESSAGE_KEY = "MessageKey"
        private const val POSITIVE_BUTTON_TEXT_KEY = "PositiveButtonTextKey"
        private const val POSITIVE_BUTTON_LISTENER_KEY = "PositiveButtonListenerKey"
        private const val NEGATIVE_BUTTON_TEXT_KEY = "NegativeButtonTextKey"
        private const val NEGATIVE_BUTTON_LISTENER_KEY = "NegativeButtonListenerKey"
    }

    class Builder() {
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
                val positiveButtonListener = object : ButtonClickListener {
                    override fun onClick() {
                        listener?.invoke()
                    }
                }
                bundle.putSerializable(POSITIVE_BUTTON_LISTENER_KEY, positiveButtonListener)
            }
        }

        fun setNegativeButton(buttonText: String, listener: (() -> Unit)? = null): Builder {
            return this.apply {
                bundle.putString(NEGATIVE_BUTTON_TEXT_KEY, buttonText)
                val negativeButtonListener = object : ButtonClickListener {
                    override fun onClick() {
                        listener?.invoke()
                    }
                }
                bundle.putSerializable(NEGATIVE_BUTTON_LISTENER_KEY, negativeButtonListener)
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
            positiveButtonClickListener =
                it.getSerializable(POSITIVE_BUTTON_LISTENER_KEY) as? ButtonClickListener
            negativeButtonClickListener =
                it.getSerializable(NEGATIVE_BUTTON_LISTENER_KEY) as? ButtonClickListener
        }
        val binding = LayoutCustomDialogBinding.inflate(requireActivity().layoutInflater)
        binding.title.text = title
        binding.message.text = message
        binding.positiveButton.text = positiveButtonText
        binding.negativeButton.text = negativeButtonText
        binding.positiveButton.setOnClickListener {
            dismiss()
            positiveButtonClickListener?.onClick()
        }
        binding.negativeButton.setOnClickListener {
            dismiss()
            negativeButtonClickListener?.onClick()
        }
        dialog.setContentView(binding.root)
        return dialog
    }
}


interface ButtonClickListener : Serializable {
    fun onClick()
}
