package ru.programpark.test;

import java.io.Serializable;

public interface Entry<E extends Entry> extends Serializable {
    Object get(AttrMeta meta);
    void set(AttrMeta meta, Object value);
}
