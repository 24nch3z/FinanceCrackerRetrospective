package ru.s4nchez.financecrackerretrospective.domain

import androidx.lifecycle.LiveData
import ru.s4nchez.financecrackerretrospective.data.model.Wallet
import ru.s4nchez.financecrackerretrospective.data.repository.FinanceRepository

class FinanceInteractorImpl(
        private val financeRepository: FinanceRepository
) : FinanceInteractor {

//    private val wallets = mutableListOf(
//            Wallet(1, "Оффшоры", "USD"),
//            Wallet(2, "Карта Сбербанка", "RUB"),
//            Wallet(3, "Карта Альфа-Банка", "EUR")
//    )
//
//    private val walletsLiveData = MutableLiveData<List<Wallet>>()

    override fun getWallets(): LiveData<List<Wallet>> {
        return financeRepository.getWallets()
    }

    override fun getWallet(id: Long): LiveData<Wallet> {
//        return MutableLiveData<Wallet>()
//                .also {
//                    it.value = wallets.find { wallet -> wallet.id == id } ?: Wallet.empty()
//                }

        return financeRepository.getWallet(id)
    }

    override fun saveWallet(wallet: Wallet) {
        financeRepository.saveWallet(wallet)
    }
}