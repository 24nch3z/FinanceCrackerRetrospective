package ru.s4nchez.financecrackerretrospective.presentation.wallet.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_transaction.view.*
import ru.s4nchez.financecrackerretrospective.R
import ru.s4nchez.financecrackerretrospective.data.model.Transaction

class TransactionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(transaction: Transaction) {
        val context = itemView.context
        with(itemView) {
            title_name.text = transaction.value.toString()

            if (adapterPosition % 2 == 0) {
                setBackgroundColor(ContextCompat.getColor(context, R.color.item_transaction_background_odd))
            } else {
                setBackgroundColor(ContextCompat.getColor(context, R.color.item_transaction_background_even))
            }
        }
    }
}