/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.low.level.api.fir.util

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.declarations.FirFile
import org.jetbrains.kotlin.fir.declarations.FirRegularClass
import org.jetbrains.kotlin.fir.declarations.utils.classId
import org.jetbrains.kotlin.fir.psi
import org.jetbrains.kotlin.fir.render
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid
import org.jetbrains.kotlin.psi.KtElement

object FirElementFinder {
    inline fun <reified E : FirElement> findElementIn(
        container: FirElement,
        crossinline goInside: (E) -> Boolean = { true },
        crossinline predicate: (E) -> Boolean,
    ): E? {
        var result: E? = null
        container.accept(object : FirVisitorVoid() {
            override fun visitElement(element: FirElement) {
                if (result != null) return
                when {
                    element !is E || element is FirFile -> {
                        element.acceptChildren(this)
                    }
                    predicate(element) -> {
                        result = element
                    }
                    goInside(element) -> {
                        element.acceptChildren(this)
                    }
                }
            }

            override fun visitRegularClass(regularClass: FirRegularClass) {
                // Checking the rest super types that weren't resolved on the first OUTER_CLASS_ARGUMENTS_REQUIRED check in FirTypeResolver
                val oldResolvePhase = regularClass.resolvePhase
                val oldList = regularClass.superTypeRefs.toList()

                try {
                    super.visitRegularClass(regularClass)
                } catch (e: ConcurrentModificationException) {
                    val newResolvePhase = regularClass.resolvePhase
                    val newList = regularClass.superTypeRefs.toList()

                    throw IllegalStateException(
                        """
                        CME while traversing superTypeRefs of declaration=${regularClass.render()}:
                        classId: ${regularClass.classId},
                        oldPhase: $oldResolvePhase, oldList: ${oldList.joinToString(",", "[", "]") { it.render() }},
                        newPhase: $newResolvePhase, newList: ${newList.joinToString(",", "[", "]") { it.render() }}
                        """.trimIndent(), e
                    )
                }
            }
        })
        return result
    }

    inline fun <reified E : FirElement> findElementByPsiIn(container: FirElement, ktElement: KtElement): E? =
        findElementIn(container) { it.psi === ktElement }
}