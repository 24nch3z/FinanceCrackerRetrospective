package ru.s4nchez.financecrackerretrospective.presentation.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.s4nchez.financecrackerretrospective.R
import ru.s4nchez.financecrackerretrospective.presentation.main.MainFragment

class MainActivity : AppCompatActivity() {

    /*

        Фичи:
        1. О приложении
        2. Курс доллара
        3. Список кошельков
        4. Список категорий
        5. Список расходов\доходов

     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment())
                    .commit()
        }
    }
}
