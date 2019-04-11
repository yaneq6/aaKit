package io.aakit.app.domain.interactor

import io.aakit.app.domain.model.AppConfig
import javax.inject.Inject

class ToggleMainType @Inject constructor(
    private val cache: AppConfig.Cache
) : () -> Unit {

    override fun invoke() = cache.run {
        value = value.toggleMainType()
    }
}
