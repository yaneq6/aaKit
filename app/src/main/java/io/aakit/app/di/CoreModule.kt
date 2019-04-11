package io.aakit.app.di

import android.content.Context
import android.os.Handler
import dagger.Module
import dagger.Provides
import io.aakit.CacheGarbageCollector
import io.aakit.CacheSharedPreferencesFactory
import io.kache.android.SharedPreferencesKache
import io.kache.core.KacheManager
import io.kache.core.MemoryKache
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class CoreModule(
    private val context: Context
) {

    @Provides
    @Singleton
    fun context() = context

    @Provides
    @Singleton
    fun handler() = Handler()

    @Provides
    @Singleton
    fun kacheManager(
        factory: CacheSharedPreferencesFactory
    ) = KacheManager(
        SharedPreferencesKache.Factory(factory),
        MemoryKache.Factory
    )

    @Provides
    @Singleton
    @Named(MAIN_SCHEDULER)
    fun mainScheduler() = AndroidSchedulers.mainThread()!!

    @Provides
    @Singleton
    @Named(BACKGROUND_SCHEDULER)
    fun backgroundScheduler() = Schedulers.newThread()

    @Provides
    @Singleton
    fun cacheGarbageCollector(
        manager: KacheManager,
        @Named(MAIN_SCHEDULER) scheduler: Scheduler
    ) = CacheGarbageCollector(
        manager = manager,
        scheduler = scheduler
    )
}

const val MAIN_SCHEDULER = "MAIN_SCHEDULER"
const val BACKGROUND_SCHEDULER = "BACKGROUND_SCHEDULER"


