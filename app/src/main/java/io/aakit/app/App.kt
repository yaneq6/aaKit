package io.aakit.app

import android.app.Activity
import io.aakit.*
import io.aakit.app.di.AppComponent
import io.aakit.app.di.AppModule
import io.aakit.app.di.DaggerAppComponent
import io.aakit.app.di.CoreComponent
import io.aakit.app.di.CoreModule
import io.aakit.app.di.DaggerCoreComponent
import io.kache.android.ReactiveApplication
import io.kache.android.ReactiveContainer
import io.kache.rxjava.flowable
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

    override val container = ReactiveContainer.Delegate<AppComponent>(this) {
        appConfigCache.flowable().map { config ->
            DaggerAppComponent.builder()
                .appModule(AppModule(this, config, disposables))
                .coreComponent(coreComponent)
                .build()
        }!!
    }

    private fun CoreComponent.init() = apply {
        cacheGarbageCollector.invokeOnAny(appConfigCache)
    }

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