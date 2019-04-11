package io.aakit.app.domain.interactor

import io.aakit.app.domain.model.AppConfig
import javax.inject.Inject

class SetConfigInteractor @Inject constructor(
    private val cache: AppConfig.Cache
) : (AppConfig) -> Unit {

    override fun invoke(config: AppConfig) {
        cache.value = config
    }
}
