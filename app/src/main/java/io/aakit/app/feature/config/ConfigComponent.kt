package io.aakit.app.feature.config

import dagger.Component
import io.kache.android.Injector
import io.aakit.app.di.ActivityModule
import io.aakit.app.di.AppComponent
import io.aakit.scope.ActivityScope

@ActivityScope
@Component(
    modules = [ActivityModule::class],
    dependencies = [AppComponent::class]
)
interface ConfigComponent : Injector<ConfigActivity>