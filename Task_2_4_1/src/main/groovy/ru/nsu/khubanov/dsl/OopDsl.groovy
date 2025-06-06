package ru.nsu.khubanov.dsl

/** основная точка входа: course { … } или oop { … } */
def course(Closure c)  { callInternal(c) }
def oop(Closure c)     { callInternal(c) }

private def callInternal(Closure c) {
    def b = new ConfigBuilder()
    c.delegate = b; c.resolveStrategy = Closure.DELEGATE_FIRST; c()
    return b.build()
}
