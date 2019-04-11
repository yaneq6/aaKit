package io.aakit.app.di

import dagger.Component
import io.aakit.ActivityLifecycleObservable
import io.aakit.CacheGarbageCollector
import io.kache.core.KacheManager
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {

    val kacheManager: KacheManager

    val mainScheduler: Scheduler @Named(MAIN_SCHEDULER) get

    val backgroundScheduler: Scheduler @Named(BACKGROUND_SCHEDULER) get

    val activityLifecycleObservable: ActivityLifecycleObservable

    val cacheGarbageCollector: CacheGarbageCollector
}