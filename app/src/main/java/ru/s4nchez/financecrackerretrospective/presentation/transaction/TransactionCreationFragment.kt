package ru.s4nchez.financecrackerretrospective.presentation.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.screen_transaction_creation.*
import ru.s4nchez.financecrackerretrospective.R
import ru.s4nchez.financecrackerretrospective.presentation.transaction.viewmodel.TransactionCreationViewModel
import ru.s4nchez.financecrackerretrospective.presentation.transaction.viewmodel.TransactionCreationViewModelFactory
import ru.s4nchez.financecrackerretrospective.utils.app
import java.util.*
import javax.inject.Inject

class TransactionCreationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: TransactionCreationViewModelFactory

    private lateinit var viewModel: TransactionCreationViewModel

    private var walletId: Long? = null

    companion object {
        private const val ARG_WALLET_ID = "walletId"

        fun createNewTransaction(walletId: Long): TransactionCreationFragment {
            return TransactionCreationFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_WALLET_ID, walletId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app.componentManager.buildFinanceComponent().inject(this)
        walletId = arguments?.getLong(ARG_WALLET_ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_transaction_creation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TransactionCreationViewModel::class.java)

        // type_minus_radio
        save_button.setOnClickListener {
            viewModel.saveTransaction(
                    id = null,
                    categoryId = 1,
                    date = Date().time,
                    description = description_input.text.toString(),
                    value = value_input.text.toString(),
                    walletId = walletId!!
            )
        }

        viewModel.categoriesLiveData.observe(this, Observer { })
    }
}