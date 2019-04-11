package io.aakit

import android.content.Context
import android.content.SharedPreferences
import io.kache.core.Kache
import javax.inject.Inject

class CacheSharedPreferencesFactory @Inject constructor(
    private val context: Context
) : (Kache.Key<*>, Int) -> SharedPreferences {
    override fun invoke(name: Kache.Key<*>, mode: Int) = context.getSharedPreferences(name.toString(), mode)!!
}