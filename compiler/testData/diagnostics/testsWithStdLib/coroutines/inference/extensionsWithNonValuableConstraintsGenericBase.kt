// FIR_IDENTICAL
// !DIAGNOSTICS: -UNUSED_PARAMETER
// !OPT_IN: kotlin.RequiresOptIn

@file:OptIn(ExperimentalTypeInference::class)

import kotlin.experimental.ExperimentalTypeInference

interface Base<K>

interface Controller<T> : Base<T> {
    suspend fun yield(t: T) {}
}

interface SpecificController<T> : Base<String> {
    suspend fun yield(t: T) {}
}

fun <S> generate(@BuilderInference g: suspend Controller<S>.() -> Unit): S = TODO()
fun <S> generateSpecific(@BuilderInference g: suspend SpecificController<S>.() -> Unit): S = TODO()

fun Base<*>.starBase() {}
fun Base<String>.stringBase() {}

val test1 = generate {
    starBase()
    yield("foo")
}

val test2 = <!TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>generate<!> {
    starBase()
}

val test3 = generate {
    yield("bar")
    stringBase()
}

val test4 = generateSpecific {
    yield(42)
    starBase()
}

val test5 = generateSpecific {
    yield(42)
    stringBase()
}

val test6 = <!TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>generateSpecific<!> {
    stringBase()
}
