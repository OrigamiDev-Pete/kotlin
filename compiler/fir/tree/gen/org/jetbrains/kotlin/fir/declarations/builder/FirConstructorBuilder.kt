/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

@file:Suppress("DuplicatedCode")

package org.jetbrains.kotlin.fir.declarations.builder

import kotlin.contracts.*
import org.jetbrains.kotlin.KtSourceElement
import org.jetbrains.kotlin.fir.FirModuleData
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl
import org.jetbrains.kotlin.fir.declarations.DeprecationsPerUseSite
import org.jetbrains.kotlin.fir.declarations.FirConstructor
import org.jetbrains.kotlin.fir.declarations.FirContextReceiver
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef
import org.jetbrains.kotlin.fir.declarations.FirValueParameter
import org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder
import org.jetbrains.kotlin.fir.declarations.impl.FirConstructorImpl
import org.jetbrains.kotlin.fir.expressions.FirAnnotation
import org.jetbrains.kotlin.fir.expressions.FirBlock
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.visitors.*
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

@FirBuilderDsl
open class FirConstructorBuilder : FirAbstractConstructorBuilder, FirAnnotationContainerBuilder {
    override var source: KtSourceElement? = null
    override lateinit var moduleData: FirModuleData
    override var resolvePhase: FirResolvePhase = FirResolvePhase.RAW_FIR
    override lateinit var origin: FirDeclarationOrigin
    override var attributes: FirDeclarationAttributes = FirDeclarationAttributes()
    override val typeParameters: MutableList<FirTypeParameterRef> = mutableListOf()
    override lateinit var status: FirDeclarationStatus
    override lateinit var returnTypeRef: FirTypeRef
    override var receiverTypeRef: FirTypeRef? = null
    override var deprecation: DeprecationsPerUseSite? = null
    override var containerSource: DeserializedContainerSource? = null
    override var dispatchReceiverType: ConeSimpleKotlinType? = null
    override val contextReceivers: MutableList<FirContextReceiver> = mutableListOf()
    override val valueParameters: MutableList<FirValueParameter> = mutableListOf()
    override val annotations: MutableList<FirAnnotation> = mutableListOf()
    override lateinit var symbol: FirConstructorSymbol
    override var delegatedConstructor: FirDelegatedConstructorCall? = null
    override var body: FirBlock? = null

    override fun build(): FirConstructor {
        return FirConstructorImpl(
            source,
            moduleData,
            resolvePhase,
            origin,
            attributes,
            typeParameters,
            status,
            returnTypeRef,
            receiverTypeRef,
            deprecation,
            containerSource,
            dispatchReceiverType,
            contextReceivers,
            valueParameters,
            annotations,
            symbol,
            delegatedConstructor,
            body,
        )
    }


    @Deprecated("Modification of 'controlFlowGraphReference' has no impact for FirConstructorBuilder", level = DeprecationLevel.HIDDEN)
    override var controlFlowGraphReference: FirControlFlowGraphReference?
        get() = throw IllegalStateException()
        set(_) {
            throw IllegalStateException()
        }
}

@OptIn(ExperimentalContracts::class)
inline fun buildConstructor(init: FirConstructorBuilder.() -> Unit): FirConstructor {
    contract {
        callsInPlace(init, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
    }
    return FirConstructorBuilder().apply(init).build()
}

@OptIn(ExperimentalContracts::class)
inline fun buildConstructorCopy(original: FirConstructor, init: FirConstructorBuilder.() -> Unit): FirConstructor {
    contract {
        callsInPlace(init, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
    }
    val copyBuilder = FirConstructorBuilder()
    copyBuilder.source = original.source
    copyBuilder.moduleData = original.moduleData
    copyBuilder.resolvePhase = original.resolvePhase
    copyBuilder.origin = original.origin
    copyBuilder.attributes = original.attributes.copy()
    copyBuilder.typeParameters.addAll(original.typeParameters)
    copyBuilder.status = original.status
    copyBuilder.returnTypeRef = original.returnTypeRef
    copyBuilder.receiverTypeRef = original.receiverTypeRef
    copyBuilder.deprecation = original.deprecation
    copyBuilder.containerSource = original.containerSource
    copyBuilder.dispatchReceiverType = original.dispatchReceiverType
    copyBuilder.contextReceivers.addAll(original.contextReceivers)
    copyBuilder.valueParameters.addAll(original.valueParameters)
    copyBuilder.annotations.addAll(original.annotations)
    copyBuilder.symbol = original.symbol
    copyBuilder.delegatedConstructor = original.delegatedConstructor
    copyBuilder.body = original.body
    return copyBuilder.apply(init).build()
}
