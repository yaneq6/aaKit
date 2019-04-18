package io.aakit.app.feature.main.di

import dagger.Component
import io.aakit.app.base.Injector
import io.aakit.app.di.ActivityModule
import io.aakit.app.di.AppComponent
import io.aakit.app.feature.main.MainActivity
import io.aakit.scope.ActivityScope

@ActivityScope
@Component(
    modules = [
        ActivityModule::class,
        MainModule::class
    ],
    dependencies = [
        AppComponent::class
    ]
)
interface MainComponent : Injector<MainActivity>