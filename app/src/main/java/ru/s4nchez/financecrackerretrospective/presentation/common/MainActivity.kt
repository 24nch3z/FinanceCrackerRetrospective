package ru.s4nchez.financecrackerretrospective.presentation.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.s4nchez.financecrackerretrospective.R
import ru.s4nchez.financecrackerretrospective.utils.app
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    /*
        Фичи:
        1. О приложении
        2. Курс доллара
        3. Список кошельков
        4. Список категорий
        5. Список расходов\доходов
     */

    @Inject
    lateinit var router: Router

    private val navigator = SupportAppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        app.componentManager.rootComponent.inject(this)

        if (savedInstanceState == null) {
            router.newRootScreen(MainListScreen())
        }
    }

    override fun onResume() {
        super.onResume()
        app.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onStop() {
        app.getNavigatorHolder().removeNavigator()
        super.onStop()
    }
}
