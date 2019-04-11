package io.aakit.app.domain.model

import io.aakit.app.domain.model.MainType.Bar
import io.aakit.app.domain.model.MainType.Foo
import io.kache.core.Kache
import io.kache.core.invoke
import io.aakit.scope.AppScope
import javax.inject.Inject

data class AppConfig(
    val value: String = "",
    val mainType: MainType = Foo
) {
    fun toggleMainType() = copy(
        mainType = when (mainType) {
            Foo -> Bar
            Bar -> Foo
        }
    )

    @AppScope
    class Cache @Inject constructor(provide: Kache.Provider) : Kache<AppConfig> by provide(Unit)
}

enum class MainType { Foo, Bar }