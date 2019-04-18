package io.aakit.app.feature.main

import io.aakit.app.domain.interactor.ToggleMainType
import io.aakit.app.domain.model.PersistentModel
import io.aakit.app.domain.model.WeakModel
import io.aakit.rx.field
import io.kache.core.proxy
import javax.inject.Inject

class MainViewModel @Inject constructor(
    weak: WeakModel.Cache,
    persistent: PersistentModel.Cache,
    private val toggle: ToggleMainType
) {
    val text1 = weak.proxy { text1 }.field()
    val text2 = weak.proxy { text2 }.field()
    val text3 = persistent.proxy { text }.field()

    fun toggleConfig() = toggle()
}