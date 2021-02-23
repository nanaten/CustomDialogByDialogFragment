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
    }

    class Builder() {
        private val bundle = Bundle()
        fun setTitle(title: String): Builder {
            return this.apply {
                bundle.putString("TitleKey", title)
            }
        }

        fun setMessage(message: String): Builder {
            return this.apply {
                bundle.putString("MessageKey", message)
            }
        }

        fun setPositiveButton(buttonText: String, listener: () -> Unit): Builder {
            return this.apply {
                bundle.putString("PositiveButtonTextKey", buttonText)
                val positiveButtonListener = object : ButtonClickListener {
                    override fun onClick() {
                        listener.invoke()
                    }
                }
                bundle.putSerializable("PositiveButtonListenerKey", positiveButtonListener)
            }
        }

        fun setNegativeButton(buttonText: String, listener: () -> Unit): Builder {
            return this.apply {
                bundle.putString("NegativeButtonTextKey", buttonText)
                val negativeButtonListener = object : ButtonClickListener {
                    override fun onClick() {
                        listener.invoke()
                    }
                }
                bundle.putSerializable("NegativeButtonListenerKey", negativeButtonListener)
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
            title = it.getString("TitleKey", "")
            message = it.getString("MessageKey", "")
            positiveButtonText = it.getString("PositiveButtonTextKey", DEFAULT_POSITIVE_BUTTON_TEXT)
            negativeButtonText = it.getString("NegativeButtonTextKey", DEFAULT_NEGATIVE_BUTTON_TEXT)
            positiveButtonClickListener =
                it.getSerializable("PositiveButtonListenerKey") as ButtonClickListener
            negativeButtonClickListener =
                it.getSerializable("NegativeButtonListenerKey") as ButtonClickListener
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
