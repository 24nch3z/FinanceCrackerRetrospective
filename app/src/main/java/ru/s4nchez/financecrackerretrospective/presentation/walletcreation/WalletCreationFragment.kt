package ru.s4nchez.financecrackerretrospective.presentation.walletcreation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.screeen_wallet_creation.*
import ru.s4nchez.financecrackerretrospective.MyApp
import ru.s4nchez.financecrackerretrospective.R
import ru.s4nchez.financecrackerretrospective.data.model.Wallet
import ru.s4nchez.financecrackerretrospective.data.store.CurrencyStore
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.adapter.CurrencyAdapter
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.viewmodel.WalletCreationViewModel
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.viewmodel.WalletCreationViewModelFactory
import javax.inject.Inject

class WalletCreationFragment : Fragment(), WalletCreationView {

    @Inject
    lateinit var walletCreationViewModelFactory: WalletCreationViewModelFactory

    private var walletId = Wallet.NEW_WALLET_ID
    private lateinit var viewModel: WalletCreationViewModel
    private val currencyAdapter by lazy { CurrencyAdapter(context!!) }

    companion object {
        private const val ARG_WALLET_ID = "walletId"

        fun newInstance(walletId: Long = Wallet.NEW_WALLET_ID): WalletCreationFragment {
            return WalletCreationFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_WALLET_ID, walletId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApp).componentManager.buildFinanceComponent().inject(this)
        walletId = arguments?.getLong(ARG_WALLET_ID) ?: Wallet.NEW_WALLET_ID
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screeen_wallet_creation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, walletCreationViewModelFactory)
                .get(WalletCreationViewModel::class.java)
        viewModel.bindView(this)

        if (savedInstanceState == null) {
            viewModel.getWallet(walletId)
        }

        currencyAdapter.setRadioGroup(radio_group)
        currencyAdapter.render(CurrencyStore.getNamesMap())

        viewModel.walletLiveData.observe(this, Observer {
            wallet_name_input.setText(it.name)
            currencyAdapter.setCurrency(it.currency)
        })

        save_button.setOnClickListener {
            viewModel.saveWallet(
                    id = walletId,
                    name = wallet_name_input.text.toString(),
                    currency = currencyAdapter.getCurrency() ?: "RUB"
            )
        }

        viewModel.validationErrorLiveData.observe(this, Observer {
            Snackbar.make(activity!!.findViewById(android.R.id.content),
                    R.string.wallet_creation_error, Snackbar.LENGTH_SHORT).show()
        })
    }

    override fun closeScreen() {
        fragmentManager?.popBackStack()
    }
}