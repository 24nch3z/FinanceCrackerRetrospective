package ru.s4nchez.financecrackerretrospective.presentation.common.adapter

interface ListItem {
    val listId: String
    fun calculatePayload(oldItem: ListItem): Any? = null
}