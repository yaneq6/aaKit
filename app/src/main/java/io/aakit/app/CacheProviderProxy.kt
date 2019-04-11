package io.aakit.app

import io.aakit.app.domain.model.AppConfig
import io.kache.core.Kache
import io.kache.core.getter
import io.kache.core.invoke

class CacheProviderProxy(
    private val provide: Kache.Provider
) : Kache.Provider {

    private val config = provide<AppConfig>(Unit).getter()

    override fun <T : Any> kache(key: Kache.Key<T>) = provide.kache(key.copy(tag = key.tag ?: config()))
}