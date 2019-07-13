package ru.s4nchez.financecrackerretrospective.presentation.walletcreation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.StringRes
import androidx.core.view.children
import ru.s4nchez.financecrackerretrospective.R
import ru.s4nchez.financecrackerretrospective.utils.dpToPx

class CurrencyAdapter(private val context: Context) {

    private var radioGroup: RadioGroup? = null
    private var selectedCurrency: String? = null
    private val radioLayoutParams: RadioGroup.LayoutParams = RadioGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

    init {
        radioLayoutParams.bottomMargin = dpToPx(context, 8.0f).toInt()
    }

    fun setRadioGroup(radioGroup: RadioGroup?) {
        this.radioGroup = radioGroup
    }

    fun render(map: Map<String, Int>) {
        if (radioGroup == null) {
            throw UnsupportedOperationException("RadioGroup равен null")
        }

        map.forEach { addRadioButton(it.key, it.value) }
        selectedCurrency?.let { setCurrency(it) }
    }

    fun setCurrency(currency: String) {
        selectedCurrency = currency
        radioGroup?.let { group ->
            group.children.iterator().forEach { radio ->
                if (radio.tag.toString() == currency) {
                    (radio as RadioButton).isChecked = true
                }
            }
        }
    }

    fun getCurrency() = selectedCurrency

    private fun addRadioButton(id: String, @StringRes nameRes: Int) {
        val radioButton = getRadioButtonView().apply {
            tag = id
            setText(nameRes)
            setOnCheckedChangeListener { view, isChecked ->
                run {
                    if (isChecked) {
                        selectedCurrency = view.tag.toString()
                    }
                }
            }
        }
        radioGroup?.addView(radioButton, radioLayoutParams)
    }

    private fun getRadioButtonView(): RadioButton {
        return LayoutInflater.from(context)
                .inflate(R.layout.radio_button, null) as RadioButton
    }
}