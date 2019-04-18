package io.aakit.rx

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes

abstract class DatabindingReactiveActivity<C, B : ViewDataBinding> : ReactiveActivity<C>() {

    abstract val layoutId: Int @LayoutRes get

    val binding: B by lazy {
        DataBindingUtil.setContentView<B>(this, layoutId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }
}