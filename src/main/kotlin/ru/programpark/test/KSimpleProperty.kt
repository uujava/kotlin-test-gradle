package ru.programpark.test

import kotlin.properties.ReadWriteProperty

//import kotlin.properties.StatelessProperty
import kotlin.reflect.KProperty

/**
 * Плоское свойство - хранится в таблице Entry
 * Изменение свойства вызывает update объекта
 */
@Suppress("FINITE_BOUNDS_VIOLATION_IN_JAVA", "UNCHECKED_CAST")
//@StatelessProperty
class KSimpleProperty<T : Entry<T>, V>(var storage: Storage<T>, type: Class<V>, primitive: Boolean = false) :
        AttrMeta<T, V>(),
        ReadWriteProperty<T, V>{

    private var mandatoryByUser:Boolean = false

    override fun mandatory(mandatory: Boolean) {
        super.mandatory(mandatory)
        mandatoryByUser = true
    }

    override fun getValue(thisRef: T, property: KProperty<*>): V {
        return thisRef.get(this) as V
    }

    override fun setValue(thisRef: T, property: KProperty<*>, value: V) {
        thisRef.set(this, value)
    }

    init {
        // no way to tell from reified KClass if its primitive
        val adjustedType = if (primitive) {
            when (type) {
                java.lang.Long::class.java -> java.lang.Long.TYPE
                java.lang.Integer::class.java -> java.lang.Integer.TYPE
                java.lang.Double::class.java -> java.lang.Double.TYPE
                java.lang.Float::class.java -> java.lang.Float.TYPE
                java.lang.Byte::class.java -> java.lang.Byte.TYPE
                java.lang.Short::class.java -> java.lang.Integer.TYPE
                java.lang.Character::class.java -> java.lang.Character.TYPE
                java.lang.Boolean::class.java -> java.lang.Boolean.TYPE
                else -> type
            }
        } else {
            type
        }
        this.type(adjustedType as Class<V>)
        this.ofClass(storage.entryClass)
    }

    constructor(storage: Storage<T>, type: Class<V>, block: AttrMeta<T, V>.() -> Unit, primitive: Boolean = false) : this(storage, type, primitive) {
        this.apply(block)
    }

    operator fun provideDelegate(
            thisRef: T?,
            prop: KProperty<*>
    ): KSimpleProperty<T, V> {
        // name set here - recalculate qualifiedName and propId
        name(prop.name)
        if(!this.mandatoryByUser){
            // get mandatory from type
            mandatory(!prop.returnType.isMarkedNullable)
        }
        storage.register(this)
        return this
    }

    override fun build(): Unit {
        // do nothing
    }
}