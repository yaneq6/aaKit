package io.aakit.app.feature

import io.aakit.BaseNavigator
import io.aakit.Navigator

import io.aakit.app.feature.config.ConfigActivity
import io.aakit.app.feature.main.MainActivity
import io.aakit.startActivity


class AppNavigator(owner: Any) : Navigator by BaseNavigator(owner) {

    fun mainActivity(): Unit = startActivity<MainActivity>()

    fun configActivity(): Unit = startActivity<ConfigActivity>()

}


