package com.example.HurixTest

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner, Observer { it?.apply(observer) })
}

fun <ResultType> Resource<ResultType>.load(list: CompleteRecyclerView, f: (ResultType?) -> Unit) {
    list.showState(status)
    load(f)
}

fun <ResultType> Resource<ResultType>.load(f: (ResultType?) -> Unit) {
    if (!status.isLoading()) {
        f(data)
    }
}