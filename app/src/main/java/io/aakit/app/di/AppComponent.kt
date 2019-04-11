package io.aakit.app.di

import android.app.Application
import dagger.Component
import io.aakit.app.App
import io.aakit.app.domain.model.AppConfig
import io.aakit.scope.AppScope
import io.kache.android.Injector
import io.kache.core.Kache

@AppScope
@Component(
    modules = [AppModule::class],
    dependencies = [CoreComponent::class]
)
interface AppComponent : Injector<App> {

    val application: Application

    val app: App

    val config: AppConfig

    val cache: AppConfig.Cache

    val kacheProvider: Kache.Provider

    override fun inject(instance: App)
}