package ru.s4nchez.financecrackerretrospective.presentation.common.adapter

interface ClickListener {
    fun onClick(listItem: ListItem, tag: String? = null)
}