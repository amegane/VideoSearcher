package com.amegane3231.videosearcher.flux.core

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.filter
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.subject.publish.PublishSubject
import io.ktor.util.reflect.instanceOf
import kotlin.reflect.KClass

object Dispatcher {
    private val _observer = PublishSubject<Action>()

    val observer: Observable<Action> = _observer

    fun dispatch(action: Action) {
        _observer.onNext(action)
    }

    inline fun <reified T : Action> on(clazz: KClass<T>): Observable<T> {
        return observer.filter { it.instanceOf(clazz) }.map { it as T }
    }
}
