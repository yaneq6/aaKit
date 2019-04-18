package io.aakit.app.feature.start

import dagger.Component
import io.aakit.app.base.Injector
import io.aakit.app.di.ActivityModule
import io.aakit.app.di.AppComponent
import io.aakit.scope.ActivityScope

@ActivityScope
@Component(
    modules = [ActivityModule::class],
    dependencies = [AppComponent::class]
)
interface StartComponent : Injector<StartActivity>