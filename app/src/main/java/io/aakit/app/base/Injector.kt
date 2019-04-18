package io.aakit.app.base

interface Injector<T> {
    fun inject(instance: T)
}