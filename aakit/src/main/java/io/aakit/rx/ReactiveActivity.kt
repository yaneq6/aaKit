package io.aakit.rx

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.processors.BehaviorProcessor
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber


abstract class ReactiveActivity<C> :
    AppCompatActivity(),
    Publisher<C> {

    private val component = BehaviorProcessor.create<C>()

    private val disposable: Disposable by lazy {
        createComponent().subscribe(
            component::onNext,
            this::onError,
            component::onComplete
        )
    }

    abstract fun createComponent(): Flowable<C>

    open fun onError(throwable: Throwable) {
        throw throwable
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposable
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    override fun subscribe(subscriber: Subscriber<in C>) {
        component.subscribe(subscriber)
    }
}

