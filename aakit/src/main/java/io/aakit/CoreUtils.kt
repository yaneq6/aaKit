package io.aakit

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import io.kache.rxjava.flowable
import io.reactivex.BackpressureStrategy
import org.reactivestreams.Publisher


fun CacheGarbageCollector.invokeOnAny(publisher: Publisher<*>) = invoke(
    publisher.flowable().map { Unit }
)

fun CacheGarbageCollector.invokeOnDestroyed(lifecycleObservable: ActivityLifecycleObservable) = invoke(
    lifecycleObservable
        .filter<OnActivity.Destroyed>()
        .toFlowable(BackpressureStrategy.LATEST)
        .map { Unit }
)

inline fun <reified VB : ViewDataBinding> Activity.lazyBinding(
    @LayoutRes layoutId: Int
): Lazy<VB> = lazy { createBinding<VB>(layoutId) }

fun <VB : ViewDataBinding> Activity.createBinding(
    @LayoutRes layoutId: Int
): VB = DataBindingUtil.setContentView(this, layoutId)