package ru.nsu.khubanov;

public class CustomAnotomicBoolean {
    private volatile boolean value;

    public CustomAnotomicBoolean(boolean initVal){
        this.value = initVal;
    }

    public boolean get() {
        return value;
    }
    public void set(boolean newValue) {
        synchronized (this) {
            value = newValue;
        }
    }
}
