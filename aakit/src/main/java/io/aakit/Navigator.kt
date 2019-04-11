package io.aakit

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment

interface Navigator {
    fun intent(type: Class<*>, init: Intent.() -> Unit = {}): Intent
    fun startActivity(intent: Intent)
    fun startActivityForResult(intent: Intent, requestCode: Int)
}

class BaseNavigator(
    private val owner: Any
) : Navigator {

    private val context: Context?
        get() = when (owner) {
            is Activity -> owner
            is Fragment -> owner.activity
            is Service -> owner.baseContext
            else -> throw Exception()
        }

    override fun intent(
        type: Class<*>,
        init: Intent.() -> Unit
    ) = Intent(this.context, type).apply(init)

    override fun startActivity(intent: Intent) = when (owner) {
        is Activity -> owner.startActivity(intent)
        is Fragment -> owner.startActivity(intent)
        is Service -> owner.startActivity(intent)
        else -> throw Exception()
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) = when (owner) {
        is Activity -> owner.startActivityForResult(intent, requestCode)
        is Fragment -> owner.startActivityForResult(intent, requestCode)
        else -> throw Exception()
    }
}

inline fun <reified T> Navigator.startActivity(
    noinline init: Intent.() -> Unit = {}
) = startActivity(
    intent = intent<T>(init)
)

inline fun <reified T> Navigator.startActivityForResult(
    requestCode: Int,
    noinline init: Intent.() -> Unit = {}
) = startActivityForResult(
    intent = intent<T>(init),
    requestCode = requestCode
)

inline fun <reified T> Navigator.intent(
    noinline init: Intent.() -> Unit = {}
) = intent(T::class.java, init)