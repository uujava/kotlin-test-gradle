@file:Suppress("FINITE_BOUNDS_VIOLATION_IN_JAVA")

package ru.programpark.test

open class Storage<T: Entry<T>>{
    var entryClass: Class<T>? = null;
    fun <V> register(kSimpleProperty: KSimpleProperty<T, V>) {

    }
    inline fun <reified V> prop(): KSimpleProperty<T, V> {
        return KSimpleProperty(this, V::class.java)
    }
}
