package io.aakit.app.feature.main.di

import dagger.Module
import dagger.Provides
import io.aakit.app.domain.model.AppConfig
import io.aakit.app.domain.model.MainType
import io.aakit.app.feature.main.BarFragment
import io.aakit.app.feature.main.FooFragment

@Module
class MainModule {

    @Provides
    fun fragment(config: AppConfig) = when (config.mainType) {
        MainType.Foo -> FooFragment()
        MainType.Bar -> BarFragment()
    }
}