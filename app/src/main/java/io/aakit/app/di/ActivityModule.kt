package io.aakit.app.di

import android.app.Activity
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import io.aakit.scope.ActivityScope
import io.aakit.app.feature.AppNavigator

@Module
class ActivityModule(
    private val activity: AppCompatActivity
) {
    @Provides
    @ActivityScope
    fun activity(): Activity = activity

    @Provides
    @ActivityScope
    fun fragmentActivity(): FragmentActivity = activity

    @Provides
    @ActivityScope
    fun appCompatActivity(): AppCompatActivity = activity

    @Provides
    @ActivityScope
    fun fragmentManager(): FragmentManager = activity.supportFragmentManager

    @Provides
    @ActivityScope
    fun navigator(activity: Activity) = AppNavigator(activity)
}
