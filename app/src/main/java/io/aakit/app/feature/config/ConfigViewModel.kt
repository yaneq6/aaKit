package io.aakit.app.feature.config

import io.aakit.app.domain.model.PersistentModel
import io.aakit.app.domain.model.WeakModel
import io.aakit.rx.field
import io.kache.core.proxy
import javax.inject.Inject

class ConfigViewModel @Inject constructor(
    memory: WeakModel.Cache,
    persistent: PersistentModel.Cache
) {
    val text1 = memory.proxy(
        WeakModel::setText1,
        WeakModel::text1
    ).field()

    val text2 = memory.proxy(
        WeakModel::setText2,
        WeakModel::text2
    ).field()

    val text3 = persistent.proxy(
        PersistentModel::setText,
        PersistentModel::text
    ).field()
}