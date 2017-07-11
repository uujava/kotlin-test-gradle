package ru.programpark.test

open class FooEntry: Entry<FooEntry> {

    override fun get(meta: AttrMeta<out Entry<*>, *>): Any? {
        return values[meta.fieldIndex()]
    }

    override fun set(meta: AttrMeta<out Entry<*>, *>, value: Any?) {
        values[meta.fieldIndex()] = value
    }

    private val values = Array<Any?>(10, {null});

    companion object: Storage<FooEntry>()
}
