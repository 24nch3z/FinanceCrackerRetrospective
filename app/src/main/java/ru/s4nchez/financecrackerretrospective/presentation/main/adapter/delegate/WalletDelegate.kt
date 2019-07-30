package ru.s4nchez.financecrackerretrospective.presentation.main.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.s4nchez.financecrackerretrospective.R
import ru.s4nchez.financecrackerretrospective.data.model.Wallet
import ru.s4nchez.financecrackerretrospective.presentation.common.adapter.RecyclerItemClickListener
import ru.s4nchez.financecrackerretrospective.presentation.common.adapter.ListItem
import ru.s4nchez.financecrackerretrospective.presentation.main.adapter.viewholder.WalletViewHolder

class WalletDelegate(
        private val recyclerItemClickListener: RecyclerItemClickListener
) : AbsListItemAdapterDelegate<WalletDelegate.Model, ListItem, WalletViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): WalletViewHolder {
        return WalletViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_wallet, parent, false))
    }

    override fun isForViewType(item: ListItem, items: MutableList<ListItem>, position: Int) = item is Model

    override fun onBindViewHolder(item: Model, holder: WalletViewHolder, payloads: MutableList<Any>) {
        holder.bind(item.wallet)
        holder.itemView.setOnClickListener { recyclerItemClickListener.onClick(item) }
    }

    data class Model(override val listId: String, val wallet: Wallet) : ListItem
}