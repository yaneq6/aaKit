package io.aakit.app.feature.main

import android.support.v4.app.Fragment
import io.aakit.app.R
import io.aakit.app.appComponent
import io.aakit.app.base.BaseActivity
import io.aakit.app.databinding.MainLayoutBinding
import io.aakit.app.di.ActivityModule
import io.aakit.app.feature.AppNavigator
import io.aakit.app.feature.main.di.DaggerMainComponent
import io.aakit.app.feature.main.di.MainComponent
import io.kache.rxjava.flowable
import javax.inject.Inject

class MainActivity : BaseActivity<MainComponent, MainLayoutBinding>() {

    override val layoutId: Int get() = R.layout.main_layout

    override fun createComponent() = appComponent.flowable().map {
        DaggerMainComponent.builder()
            .appComponent(it)
            .activityModule(ActivityModule(this))
            .build()
    }!!

    @Inject
    fun init(
        mainViewModel: MainViewModel,
        appNavigator: AppNavigator,
        fragment: Fragment
    ) {
        binding.run {
            model = mainViewModel
            navigate = appNavigator
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment)
            .commit()
    }
}
