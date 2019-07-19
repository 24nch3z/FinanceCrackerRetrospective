package ru.s4nchez.financecrackerretrospective.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.s4nchez.financecrackerretrospective.data.model.Wallet
import ru.s4nchez.financecrackerretrospective.data.repository.FinanceRepository
import ru.s4nchez.financecrackerretrospective.executor.Executor
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.MODE_EDIT
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.MODE_NEW

class FinanceInteractorImpl(
        private val financeRepository: FinanceRepository,
        private val executor: Executor
) : FinanceInteractor {

    override fun getWallets(): LiveData<List<Wallet>> {
        return financeRepository.getWallets()
    }

    override fun getWallet(id: Long?, mode: Int): LiveData<Wallet> {
        return when (mode) {
            MODE_EDIT -> financeRepository.getWallet(id!!)
            MODE_NEW -> generateNewWallet()
            else -> generateNewWallet()
        }
    }

    private fun generateNewWallet(): LiveData<Wallet> {
        return MutableLiveData<Wallet>().also { it.value = Wallet.empty() }
    }

    override fun saveWallet(wallet: Wallet, mode: Int) {
        executor.run {
            when (mode) {
                MODE_EDIT -> financeRepository.updateWallet(wallet)
                MODE_NEW -> financeRepository.saveWallet(wallet)
                else -> financeRepository.saveWallet(wallet)
            }
        }
    }
}