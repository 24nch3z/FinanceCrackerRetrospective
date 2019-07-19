package ru.s4nchez.financecrackerretrospective.executor

import java.util.concurrent.Executors

class Executor {

    private val executor = Executors.newCachedThreadPool()

    fun run(func: () -> Unit) {
        executor.execute(func)
    }
}