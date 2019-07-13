package ru.s4nchez.financecrackerretrospective.presentation.main.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.s4nchez.financecrackerretrospective.R
import ru.s4nchez.financecrackerretrospective.presentation.common.adapter.ClickListener
import ru.s4nchez.financecrackerretrospective.presentation.common.adapter.ListItem
import ru.s4nchez.financecrackerretrospective.presentation.main.adapter.viewholder.AddWalletViewHolder

class AddWalletDelegate(
        private val clickListener: ClickListener
) : AbsListItemAdapterDelegate<AddWalletDelegate.Model, ListItem, AddWalletViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): AddWalletViewHolder {
        return AddWalletViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_wallet_add, parent, false))
    }

    override fun isForViewType(item: ListItem, items: MutableList<ListItem>, position: Int) = item is Model

    override fun onBindViewHolder(item: Model, holder: AddWalletViewHolder, payloads: MutableList<Any>) {
        holder.itemView.setOnClickListener { clickListener.onClick(item) }
    }

    data class Model(override val listId: String = MODEL_TAG) : ListItem

    companion object {
        private const val MODEL_TAG = "AddWalletDelegate.Model"
    }
}