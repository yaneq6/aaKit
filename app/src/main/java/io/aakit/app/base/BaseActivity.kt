package io.aakit.app.base

import android.databinding.ViewDataBinding
import io.aakit.rx.DatabindingReactiveActivity

abstract class BaseActivity<C, B : ViewDataBinding> : DatabindingReactiveActivity<C, B>()