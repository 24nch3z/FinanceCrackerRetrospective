package ru.s4nchez.financecrackerretrospective.presentation.wallet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.s4nchez.financecrackerretrospective.R
import ru.s4nchez.financecrackerretrospective.data.model.Transaction
import ru.s4nchez.financecrackerretrospective.presentation.common.adapter.RecyclerItemClickListener
import ru.s4nchez.financecrackerretrospective.presentation.common.adapter.ListItem

class TransactionDelegate(
        private val recyclerItemClickListener: RecyclerItemClickListener
) : AbsListItemAdapterDelegate<TransactionDelegate.Model, ListItem, TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): TransactionViewHolder {
        return TransactionViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false))
    }

    override fun isForViewType(item: ListItem, items: MutableList<ListItem>, position: Int) = item is Model

    override fun onBindViewHolder(item: Model, holder: TransactionViewHolder, payloads: MutableList<Any>) {
        holder.bind(item.transaction)
        holder.itemView.setOnClickListener { recyclerItemClickListener.onClick(item) }
    }

    data class Model(override val listId: String, val transaction: Transaction) : ListItem
}