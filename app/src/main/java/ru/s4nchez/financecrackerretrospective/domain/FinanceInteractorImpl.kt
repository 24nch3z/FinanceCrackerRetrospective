package ru.s4nchez.financecrackerretrospective.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.s4nchez.financecrackerretrospective.data.model.Wallet

class FinanceInteractorImpl : FinanceInteractor {

    private val wallets = mutableListOf(
            Wallet(1, "Оффшоры", "USD"),
            Wallet(2, "Карта Сбербанка", "RUB"),
            Wallet(3, "Карта Альфа-Банка", "EUR")
    )

    private val walletsLiveData = MutableLiveData<List<Wallet>>()

    override fun getWallets(): LiveData<List<Wallet>> {
        return walletsLiveData.also { it.value = wallets }
    }

    override fun getWallet(id: Long): LiveData<Wallet> {
        return MutableLiveData<Wallet>()
                .also {
                    it.value = wallets.find { wallet -> wallet.id == id } ?: Wallet.empty()
                }
    }

    override fun saveWallet(wallet: Wallet) {
        wallets.add(wallet)
        walletsLiveData.value = wallets
    }
}