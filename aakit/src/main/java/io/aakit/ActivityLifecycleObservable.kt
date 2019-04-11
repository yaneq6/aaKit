package io.aakit

import android.app.Activity
import android.app.Application
import android.os.Bundle
import io.aakit.OnActivity.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityLifecycleObservable @Inject constructor() : Observable<OnActivity>(),
    Application.ActivityLifecycleCallbacks {

    private val observers: Observers<in OnActivity> = mutableSetOf()

    override fun subscribeActual(observer: Observer<in OnActivity>) {
        observers += observer
        observer.onSubscribe(ActivityDisposable(observer))
    }

    private inner class ActivityDisposable(
        observer: Observer<in OnActivity>
    ) : Disposable {
        var tmpObserver: Observer<in OnActivity>? = observer

        override fun isDisposed(): Boolean = tmpObserver == null

        override fun dispose() {
            tmpObserver?.let {
                observers -= it
                tmpObserver = null
            }
        }
    }

    private fun OnActivity.next() = observers.onNext(this)

    override fun onActivityPaused(activity: Activity) = Paused(activity).next()

    override fun onActivityResumed(activity: Activity) = Resumed(activity).next()

    override fun onActivityStarted(activity: Activity) = Started(activity).next()

    override fun onActivityDestroyed(activity: Activity) = Destroyed(activity).next()

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) = SaveInstanceState(activity, outState).next()

    override fun onActivityStopped(activity: Activity) = Stopped(activity).next()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = Created(activity, savedInstanceState).next()
}

sealed class OnActivity {
    abstract val activity: Activity

    data class Paused(override val activity: Activity) : OnActivity()
    data class Resumed(override val activity: Activity) : OnActivity()
    data class Started(override val activity: Activity) : OnActivity()
    data class Destroyed(override val activity: Activity) : OnActivity()
    data class SaveInstanceState(override val activity: Activity, val outState: Bundle?) : OnActivity()
    data class Stopped(override val activity: Activity) : OnActivity()
    data class Created(override val activity: Activity, val savedInstanceState: Bundle?) : OnActivity()
}

private typealias Observers<T> = MutableSet<Observer<T>>

private fun <T : OnActivity> Observers<in T>.onNext(next: T) = forEach { it.onNext(next) }

inline fun <reified T : OnActivity> ActivityLifecycleObservable.filter() = filter { it is T }!!