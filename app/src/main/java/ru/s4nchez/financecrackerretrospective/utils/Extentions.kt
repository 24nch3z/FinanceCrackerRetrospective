package ru.s4nchez.financecrackerretrospective.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import ru.s4nchez.financecrackerretrospective.MyApp

val Activity.app: MyApp
    get() = this.application as MyApp

val Fragment.app: MyApp
    get() = this.activity?.app!!