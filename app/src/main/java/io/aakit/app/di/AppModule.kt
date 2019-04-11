package io.aakit.app.di

import android.app.Application
import dagger.Module
import dagger.Provides
import io.aakit.app.App
import io.aakit.app.domain.model.AppConfig
import io.aakit.app.CacheProviderProxy
import io.aakit.scope.AppScope
import io.kache.core.Kache
import io.kache.core.KacheManager
import io.reactivex.disposables.CompositeDisposable

@Module
class AppModule(
    private val app: App,
    private val config: AppConfig,
    private val disposables: CompositeDisposable
) {

    @Provides
    @AppScope
    fun application(): Application = app

    @Provides
    @AppScope
    fun app() = app

    @Provides
    fun disposables() = disposables

    @Provides
    fun config() = config

    @Provides
    @AppScope
    fun kacheProvider(kacheManager: KacheManager): Kache.Provider = CacheProviderProxy(kacheManager)

}

