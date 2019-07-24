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
import ru.s4nchez.financecrackerretrospective.R
import ru.s4nchez.financecrackerretrospective.data.store.CurrencyStore
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.adapter.CurrencyAdapter
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.viewmodel.WalletCreationViewModel
import ru.s4nchez.financecrackerretrospective.presentation.walletcreation.viewmodel.WalletCreationViewModelFactory
import ru.s4nchez.financecrackerretrospective.utils.app
import javax.inject.Inject

class WalletCreationFragment : Fragment() {

    @Inject
    lateinit var walletCreationViewModelFactory: WalletCreationViewModelFactory

    private var walletId: Long? = null
    private var mode = MODE_NEW
    private lateinit var viewModel: WalletCreationViewModel
    private val currencyAdapter by lazy { CurrencyAdapter(context!!) }

    companion object {
        private const val ARG_WALLET_ID = "walletId"
        private const val ARG_MODE = "mode"

        fun createNewWallet(): WalletCreationFragment {
            return WalletCreationFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_MODE, MODE_NEW)
                }
            }
        }

        fun updateWallet(walletId: Long): WalletCreationFragment {
            return WalletCreationFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_MODE, MODE_EDIT)
                    putLong(ARG_WALLET_ID, walletId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app.componentManager.buildFinanceComponent().inject(this)
        walletId = arguments?.getLong(ARG_WALLET_ID)
        mode = arguments?.getInt(ARG_MODE) ?: MODE_NEW
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screeen_wallet_creation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, walletCreationViewModelFactory)
                .get(WalletCreationViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.getWallet(walletId, mode)
        }

        if (mode == MODE_NEW) {
            currencyAdapter.setRadioGroup(radio_group)
            currencyAdapter.render(CurrencyStore.getNamesMap())
            choose_currency_label.visibility = View.VISIBLE
        } else {
            viewModel.walletLiveData.observe(this, Observer {
                wallet_name_input.setText(it.name)
                currencyAdapter.setCurrency(it.currency)
            })
            choose_currency_label.visibility = View.GONE
        }

        save_button.setOnClickListener {
            viewModel.saveWallet(
                    id = walletId,
                    name = wallet_name_input.text.toString(),
                    currency = currencyAdapter.getCurrency(),
                    mode = mode
            )
        }

        viewModel.validationErrorLiveData.observe(this, Observer {
            Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    R.string.wallet_creation_error, Snackbar.LENGTH_SHORT
            ).show()
        })

        viewModel.saveWalletCompleteLiveData.observe(this, Observer {
            viewModel.openWalletScreen(it)
        })
    }
}