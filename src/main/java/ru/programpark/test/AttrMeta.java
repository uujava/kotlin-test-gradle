package ru.programpark.test;

/**
 * Created by kozyr on 06.07.2017.
 */
public abstract class AttrMeta<E extends Entry, T> {
    private String name;
    private Class<T> type;
    private Class<E> entryClass;

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    private boolean mandatory;

    public boolean mandatory() {
        return mandatory;
    }

    public void mandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    private int fieldIndex;

    public int fieldIndex() {
        return fieldIndex;
    }

    public void fieldIndex(int fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    abstract void build();

    public void type(Class<T> type) {
        this.type = type;
    }

    public void ofClass(Class<E> entryClass){
        this.entryClass =entryClass;
    }
}
