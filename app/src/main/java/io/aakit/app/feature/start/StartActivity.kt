package io.aakit.app.feature.start

import io.aakit.app.R
import io.aakit.app.appComponent
import io.aakit.app.base.BaseActivity
import io.aakit.app.databinding.StartLayoutBinding
import io.aakit.app.di.ActivityModule
import io.aakit.app.feature.AppNavigator
import io.kache.rxjava.flowable
import javax.inject.Inject

class StartActivity : BaseActivity<StartComponent, StartLayoutBinding>() {

    override val layoutId: Int get() = R.layout.start_layout

    override fun createComponent() = appComponent.flowable().map {
        DaggerStartComponent.builder()
            .appComponent(it)
            .activityModule(ActivityModule(this))
            .build()

    }!!

    @Inject
    fun init(appNavigator: AppNavigator) {
        binding.navigate = appNavigator
    }
}