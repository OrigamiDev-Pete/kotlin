// SKIP_TXT

class Foo

fun main1() = when {
    else -> <!TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER, TYPE_MISMATCH!>Foo::plus<!>
}

fun main2() = if (true) <!TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>Foo::<!UNRESOLVED_REFERENCE!>minus<!><!> else <!TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>Foo::<!UNRESOLVED_REFERENCE!>times<!><!>

fun main3() = if (true) { Foo::<!UNRESOLVED_REFERENCE!>minus<!> } else { Foo::<!UNRESOLVED_REFERENCE!>times<!> }

fun main4() = try { Foo::<!UNRESOLVED_REFERENCE!>minus<!> } finally { Foo::<!UNRESOLVED_REFERENCE!>times<!> }

fun main5() = <!TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>Foo::<!UNRESOLVED_REFERENCE!>minus<!><!> ?: <!TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>Foo::<!UNRESOLVED_REFERENCE!>times<!><!>
