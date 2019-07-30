package ru.s4nchez.financecrackerretrospective.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.s4nchez.financecrackerretrospective.data.model.Category
import ru.s4nchez.financecrackerretrospective.data.model.Transaction
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

    override fun getWallet(id: Long): LiveData<Wallet> {
        return financeRepository.getWallet(id)
    }

    override fun getEmptyWallet(): LiveData<Wallet> {
        return MutableLiveData<Wallet>().also { it.value = Wallet.empty() }
    }

    override fun saveWallet(wallet: Wallet, mode: Int): LiveData<Long> {
        val liveData = MutableLiveData<Long>()
        executor.run {
            when (mode) {
                MODE_EDIT -> liveData.postValue(financeRepository.updateWallet(wallet))
                MODE_NEW -> liveData.postValue(financeRepository.saveWallet(wallet))
                else -> liveData.postValue(financeRepository.saveWallet(wallet))
            }
        }
        return liveData
    }

    override fun deleteWallet(id: Long) {
        executor.run {
            financeRepository.deleteWallet(id)
        }
    }

    override fun getCategories(): LiveData<List<Category>> {
        return financeRepository.getCategories()
    }

    override fun getCategory(id: Long): Category {
        return financeRepository.getCategory(id)
    }

    override fun getTransactions(walletId: Long): LiveData<List<Transaction>> {
        return financeRepository.getTransactions(walletId)
    }

    override fun saveTransaction(id: Long?, value: Double, description: String, categoryId: Long, walletId: Long, date: Long): LiveData<Long> {
        val liveData = MutableLiveData<Long>()
        executor.run {
            liveData.postValue(financeRepository.saveTransaction(Transaction(id, value,
                    description, categoryId, walletId, date)))
        }
        return liveData
    }
}