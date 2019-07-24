package ru.s4nchez.financecrackerretrospective.presentation.wallet.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DeleteWalletDialog : DialogFragment() {

    private lateinit var listener: DialogListener

    companion object {
        fun newInstance() = DeleteWalletDialog()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = targetFragment as DialogListener
        } catch (error: ClassCastException) {
            throw ClassCastException()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context!!)
                .setMessage("Удалить кошёлек?")
                .setCancelable(true)
                .setPositiveButton("Да") { _, _ ->
                    listener.onDeleteWalletConfirm()
                }
                .setNegativeButton("Отмена") { _, _ -> }
                .create()
    }

    interface DialogListener {
        fun onDeleteWalletConfirm()
    }
}