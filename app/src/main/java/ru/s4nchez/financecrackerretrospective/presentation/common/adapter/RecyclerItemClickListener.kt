package ru.s4nchez.financecrackerretrospective.presentation.common.adapter

interface RecyclerItemClickListener {
    fun onClick(listItem: ListItem, tag: String? = null)
}