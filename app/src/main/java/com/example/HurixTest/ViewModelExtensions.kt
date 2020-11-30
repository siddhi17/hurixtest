package com.example.HurixTest

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> BaseActivity.getViewModel(): T {
    return ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.getViewModel(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}
