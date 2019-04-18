package io.aakit.app

import android.app.Activity
import io.aakit.ActivityLifecycleObservable
import io.aakit.CacheGarbageCollector
import io.aakit.app.di.*
import io.aakit.invokeOnAny
import io.aakit.invokeOnDestroyed
import io.aakit.rx.ReactiveApplication
import io.kache.rxjava.flowable
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import org.reactivestreams.Publisher
import javax.inject.Inject

class App : ReactiveApplication<AppComponent>() {

    private val disposables = CompositeDisposable()

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
            .coreModule(CoreModule(this))
            .build()
            .init()
    }

    private val appConfigCache by lazy {
        coreComponent.appConfigCache
    }

    private fun CoreComponent.init() = apply {
        cacheGarbageCollector.invokeOnAny(appConfigCache)
    }

    override fun createComponent(): Flowable<AppComponent> = appConfigCache.flowable().map { config ->
        DaggerAppComponent.builder()
            .appModule(AppModule(this, config, disposables))
            .coreComponent(coreComponent)
            .build()
    }!!

    @Inject
    fun CompositeDisposable.init(
        lifecycleObservable: ActivityLifecycleObservable,
        cacheGarbageCollector: CacheGarbageCollector
    ) {
        registerActivityLifecycleCallbacks(lifecycleObservable)
        clear()
        add(cacheGarbageCollector.invokeOnDestroyed(lifecycleObservable))
    }
}

val Activity.appComponent: Publisher<AppComponent> get() = application as App