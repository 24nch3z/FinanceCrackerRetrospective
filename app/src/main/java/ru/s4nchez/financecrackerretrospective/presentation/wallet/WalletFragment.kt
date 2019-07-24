package ru.s4nchez.financecrackerretrospective.presentation.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.screen_wallet.*
import ru.s4nchez.financecrackerretrospective.R
import ru.s4nchez.financecrackerretrospective.presentation.wallet.dialog.DeleteWalletDialog
import ru.s4nchez.financecrackerretrospective.presentation.wallet.viewmodel.WalletViewModel
import ru.s4nchez.financecrackerretrospective.presentation.wallet.viewmodel.WalletViewModelFactory
import ru.s4nchez.financecrackerretrospective.utils.app
import javax.inject.Inject

class WalletFragment : Fragment(), DeleteWalletDialog.DialogListener {

    @Inject
    lateinit var walletViewModelFactory: WalletViewModelFactory

    private var walletId: Long = -1

    private lateinit var viewModel: WalletViewModel

    companion object {
        private const val ARG_WALLET_ID = "walletId"

        fun newInstance(walletId: Long): WalletFragment {
            return WalletFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_WALLET_ID, walletId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app.componentManager.buildFinanceComponent().inject(this)
        walletId = arguments?.getLong(ARG_WALLET_ID)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, walletViewModelFactory)
                .get(WalletViewModel::class.java)
        viewModel.walletLiveData.observe(this, Observer { wallet ->
            wallet?.let { wallet_title_view.text = it.name }
        })

        wallet_title_view.isSelected = true
        delete_wallet_button.setOnClickListener {
            val dialog = DeleteWalletDialog.newInstance()
            dialog.setTargetFragment(this, -1)
            dialog.show(fragmentManager, null)
        }

        viewModel.getWallet(walletId)
    }

    override fun onDeleteWalletConfirm() {
        viewModel.deleteWallet(walletId)
    }
}