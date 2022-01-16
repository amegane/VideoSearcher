package com.amegane3231.moviesearch.flux.core

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.subject.publish.PublishSubject

object Dispatcher {
    private val _observer = PublishSubject<Action>()

    val observer: Observable<Action> = _observer

    fun dispatch(action: Action) {
        _observer.onNext(action)
    }
}