package io.aakit.app

import io.aakit.app.di.CoreComponent
import io.aakit.app.domain.model.AppConfig
import io.kache.core.invoke

val CoreComponent.appConfigCache get() = kacheManager<AppConfig>(Unit)