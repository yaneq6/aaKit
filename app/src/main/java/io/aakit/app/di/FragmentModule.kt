package io.aakit.app.di

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import io.aakit.scope.ActivityScope
import io.aakit.app.feature.AppNavigator

@Module
class FragmentModule(
    private val fragment: Fragment
) {
    @Provides
    @ActivityScope
    fun fragment() = fragment

    @Provides
    @ActivityScope
    fun navigator(fragment: Fragment) = AppNavigator(fragment)
}