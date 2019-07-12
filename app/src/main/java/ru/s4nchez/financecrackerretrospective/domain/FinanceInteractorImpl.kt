package ru.s4nchez.financecrackerretrospective.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.s4nchez.financecrackerretrospective.data.model.Wallet

class FinanceInteractorImpl : FinanceInteractor {

    override fun getWallets(): LiveData<List<Wallet>> {
        val liveData = MutableLiveData<List<Wallet>>()
        liveData.value = mutableListOf(
                Wallet(1, "Оффшоры", "USD"),
                Wallet(2, "Карта Сбербанка", "RUB"),
                Wallet(3, "Карта Альфа-Банка", "RUB")
        )
        return liveData
    }
}