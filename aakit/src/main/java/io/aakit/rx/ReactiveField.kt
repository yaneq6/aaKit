package io.aakit.rx

import android.databinding.Observable
import android.databinding.ObservableField
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import timber.log.Timber


fun <T : Any> Publisher<T>.field(
    onError: (Throwable) -> Unit = Timber::e
) = ReactiveField(
    publisher = this,
    onError = onError
)


class ReactiveField<T : Any>(
    private val publisher: Publisher<T>,
    private val onError: (Throwable) -> Unit = Timber::e
) : ObservableField<T>() {

    private val callbacks = mutableSetOf<Observable.OnPropertyChangedCallback>()

    private var subscription: Subscription? = null

    private var value: T? = null

    override fun get(): T? = value

    override fun set(value: T) {
        if (value != this.value) {
            this.value = value
            (publisher as? (T) -> Unit)?.invoke(value)
            notifyChange()
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) =
        synchronized(callbacks) {
            callbacks += callback
            super.addOnPropertyChangedCallback(callback)
            if (callbacks.size == 1) {
                publisher.subscribe(subscriber)
            }
        }

    private val subscriber = object : Subscriber<T> {
        override fun onComplete() {}

        override fun onSubscribe(s: Subscription) {
            subscription = s
        }

        override fun onNext(next: T) {
            if (next != value) {
                value = next
                notifyChange()
            }
        }

        override fun onError(t: Throwable) = onError.invoke(t)

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks -= callback
        super.removeOnPropertyChangedCallback(callback)
        if (callbacks.size == 0) {
            subscription?.cancel()
            subscription = null
        }
    }
}