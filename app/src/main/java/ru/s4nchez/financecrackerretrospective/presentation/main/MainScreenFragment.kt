package ru.s4nchez.financecrackerretrospective.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.screen_main.*
import ru.s4nchez.financecrackerretrospective.R
import ru.s4nchez.financecrackerretrospective.presentation.common.adapter.ClickListener
import ru.s4nchez.financecrackerretrospective.presentation.common.adapter.DiffAdapter
import ru.s4nchez.financecrackerretrospective.presentation.common.adapter.ListItem
import ru.s4nchez.financecrackerretrospective.presentation.main.adapter.delegate.AddWalletDelegate
import ru.s4nchez.financecrackerretrospective.presentation.main.adapter.delegate.WalletDelegate
import ru.s4nchez.financecrackerretrospective.presentation.main.viewmodel.MainScreenViewModel
import ru.s4nchez.financecrackerretrospective.presentation.main.viewmodel.MainScreenViewModelFactory
import ru.s4nchez.financecrackerretrospective.utils.app
import ru.s4nchez.financecrackerretrospective.utils.dpToPx
import ru.s4nchez.financecrackerretrospective.utils.screenWidth
import javax.inject.Inject

class MainScreenFragment : Fragment(), ClickListener {

    @Inject
    lateinit var mainScreenViewModelFactory: MainScreenViewModelFactory

    private lateinit var viewModel: MainScreenViewModel

    private val walletsAdapter by lazy {
        DiffAdapter(listOf(
                WalletDelegate(this),
                AddWalletDelegate(this)
        ))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app.componentManager.buildFinanceComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(wallets_recycler_view) {
            layoutManager = LinearLayoutManager(activity,
                    LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(CardWidthItemDecoration(
                    screenWidth(activity!!),
                    dpToPx(activity!!, 16.0f).toInt().times(2), // Отступы у CardView,
                    dpToPx(activity!!, 32.0f).toInt()
            ))
            adapter = walletsAdapter
        }

        viewModel = ViewModelProviders.of(this, mainScreenViewModelFactory)
                .get(MainScreenViewModel::class.java)

        viewModel.items.observe(this, Observer { walletsAdapter.items = it })
    }

    override fun onDestroy() {
        app.componentManager.destroyFinanceComponent()
        super.onDestroy()
    }

    override fun onClick(listItem: ListItem, tag: String?) {
        when (listItem) {
            is WalletDelegate.Model -> viewModel.openWalletScreen(listItem.wallet.id!!)
            is AddWalletDelegate.Model -> viewModel.openWalletCreationScreen()
        }
    }
}