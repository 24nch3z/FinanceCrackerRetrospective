package ru.s4nchez.financecrackerretrospective.presentation.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CardWidthItemDecoration(
        private val screenWidth: Int,
        private val cardViewPadding: Int,
        private val extraPadding: Int
) : RecyclerView.ItemDecoration() {

    // TODO: Надо триггерить этот метод, когда добавляем первый кошелёк и удаляем последний
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val params = view.layoutParams
        params.width = screenWidth - cardViewPadding
        if (state.itemCount > 1) {
            params.width -= extraPadding
        }
        view.layoutParams = params
    }
}