package io.aakit.rx

import android.annotation.SuppressLint
import android.app.Application
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber

abstract class ReactiveApplication<C> :
    Application(),
    Publisher<C> {

    private val component = BehaviorProcessor.create<C>()

    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()
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

    override fun subscribe(subscriber: Subscriber<in C>) {
        component.subscribe(subscriber)
    }
}

