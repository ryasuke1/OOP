package ru.nsu.khubanov.dsl

class SettingsBuilder {
    private final Map<String,Object> settings
    SettingsBuilder(Map<String,Object> settings){ this.settings = settings }

    /** записываем любое свойство вида  key value  в map */
    def methodMissing(String name, Object args){
        Object[] a = args as Object[]
        if(a.length==1) settings[name] = a[0]
        else throw new MissingMethodException(name, this.class, a)
    }
}
