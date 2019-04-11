package io.aakit.app.domain.model

import io.kache.core.Kache
import io.kache.core.invoke
import javax.inject.Inject

data class WeakModel(
    val text1: String = "",
    val text2: String = ""
) {

    fun setText1(text1: String) = copy(
        text1 = text1
    )

    fun setText2(text2: String) = copy(
        text2 = text2
    )

    class Cache @Inject constructor(provide: Kache.Provider) : Kache<WeakModel> by provide()
}