package ru.s4nchez.financecrackerretrospective.presentation.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CardWidthItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val params = view.layoutParams
        params.width = 1080
        view.layoutParams = params
    }
}