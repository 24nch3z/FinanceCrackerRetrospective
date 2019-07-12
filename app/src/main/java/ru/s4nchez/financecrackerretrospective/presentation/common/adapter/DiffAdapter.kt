package ru.s4nchez.financecrackerretrospective.presentation.common.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class DiffAdapter(
        delegates: List<AdapterDelegate<List<ListItem>>>
) : AsyncListDifferDelegationAdapter<ListItem>(ListDiffCallback()) {

    init {
        delegates.forEach { delegatesManager.addDelegate(it) }
    }

    private class ListDiffCallback<T : ListItem> : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.listId == newItem.listId
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.equals(newItem)
        }

        override fun getChangePayload(oldItem: T, newItem: T): Any? {
            return newItem.calculatePayload(oldItem) // TODO: Разобраться с методом
        }
    }
}