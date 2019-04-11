package io.aakit.app.feature.start

import io.aakit.app.R
import io.aakit.app.appComponent
import io.aakit.app.databinding.StartLayoutBinding
import io.aakit.app.di.ActivityModule
import io.aakit.app.feature.AppNavigator
import io.aakit.lazyBinding
import io.kache.android.ReactiveActivity
import io.kache.android.ReactiveContainer
import io.kache.rxjava.flowable
import javax.inject.Inject

class StartActivity : ReactiveActivity<StartLayoutBinding, StartComponent>() {

    override val binding: StartLayoutBinding by lazyBinding(R.layout.start_layout)

    override val container = ReactiveContainer.Delegate<StartComponent>(this) {
        appComponent.flowable().map {
            DaggerStartComponent.builder()
                .appComponent(it)
                .activityModule(ActivityModule(this))
                .build()
        }!!
    }

    @Inject
    fun init(appNavigator: AppNavigator) {
        binding.navigate = appNavigator
    }
}