// IGNORE_FIR
// EXPECTED_REACHABLE_NODES: 1283
// MODULE: lib
// FILE: lib.kt

package utils

inline
public fun <T, R> apply(x: T, fn: (T)->R): R =
        fn(x)

// MODULE: main(lib)
// FILE: main.kt

import utils.*

// CHECK_CONTAINS_NO_CALLS: test except=imul TARGET_BACKENDS=JS

// FIXME: Not inlined on IR BE
// CHECK_CONTAINS_NO_CALLS: test except=multiplyBy2 IGNORED_BACKENDS=JS

internal inline fun multiplyBy2(x: Int): Int = x * 2

internal fun test(x: Int): Int = apply(x, ::multiplyBy2)

fun box(): String {
    assertEquals(6, test(3))

    return "OK"
}
