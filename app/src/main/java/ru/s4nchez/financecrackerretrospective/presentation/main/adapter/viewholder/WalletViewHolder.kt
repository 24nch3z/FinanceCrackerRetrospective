package ru.s4nchez.financecrackerretrospective.presentation.main.adapter.viewholder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_wallet.view.*
import ru.s4nchez.financecrackerretrospective.data.model.Wallet

@SuppressLint("All")
class WalletViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(wallet: Wallet) {
        with(itemView) {
            title_view.text = wallet.name
            balance_view.text = "$456.43"
        }
    }
}