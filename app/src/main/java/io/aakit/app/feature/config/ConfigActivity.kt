package io.aakit.app.feature.config

import io.aakit.app.R
import io.aakit.app.appComponent
import io.aakit.app.databinding.ConfigLayoutBinding
import io.aakit.app.di.ActivityModule
import io.aakit.lazyBinding
import io.kache.android.ReactiveActivity
import io.kache.android.ReactiveContainer
import io.kache.rxjava.flowable
import javax.inject.Inject

class ConfigActivity : ReactiveActivity<ConfigLayoutBinding, ConfigComponent>() {

    override val binding: ConfigLayoutBinding by lazyBinding(R.layout.config_layout)

    override val container = ReactiveContainer.Delegate<ConfigComponent>(this) {
        appComponent.flowable().map {
            DaggerConfigComponent.builder()
                .appComponent(it)
                .activityModule(ActivityModule(this))
                .build()
        }!!
    }

    @Inject
    fun init(
        configViewModel: ConfigViewModel
    ) {
        binding.model = configViewModel
    }
}