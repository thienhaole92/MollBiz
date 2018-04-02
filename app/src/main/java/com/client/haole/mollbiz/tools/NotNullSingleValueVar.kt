package com.client.haole.mollbiz.tools

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class NotNullSingleValueVar<T>: ReadWriteProperty<Any?, T> {

    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("This object not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("Object already initialized")
    }

    object DelegatesExt {
        fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> {
            return NotNullSingleValueVar()
        }
    }

}