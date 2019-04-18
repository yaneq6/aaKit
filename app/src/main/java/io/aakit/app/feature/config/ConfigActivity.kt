package io.aakit.app.feature.config

import io.aakit.app.R
import io.aakit.app.appComponent
import io.aakit.app.base.BaseActivity
import io.aakit.app.databinding.ConfigLayoutBinding
import io.aakit.app.di.ActivityModule
import io.kache.rxjava.flowable
import javax.inject.Inject

class ConfigActivity : BaseActivity<ConfigComponent, ConfigLayoutBinding>() {

    override val layoutId: Int get() = R.layout.config_layout

    override fun createComponent() = appComponent.flowable().map {
        DaggerConfigComponent.builder()
            .appComponent(it)
            .activityModule(ActivityModule(this))
            .build()
    }!!

    @Inject
    fun init(
        configViewModel: ConfigViewModel
    ) {
        binding.model = configViewModel
    }
}