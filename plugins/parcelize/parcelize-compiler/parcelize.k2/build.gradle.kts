description = "Parcelize compiler plugin (Backend)"

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    implementation(project(":plugins:parcelize:parcelize-compiler:parcelize.common"))

    compileOnly(intellijCore())
    compileOnly(project(":compiler:fir:cones"))
    compileOnly(project(":compiler:fir:tree"))
    compileOnly(project(":compiler:fir:resolve"))
    compileOnly(project(":compiler:fir:checkers"))
    compileOnly(project(":compiler:fir:checkers:checkers.jvm"))
    compileOnly(project(":compiler:ir.backend.common"))
    compileOnly(project(":compiler:ir.tree.impl"))
    compileOnly(project(":compiler:fir:entrypoint"))
    compileOnly(project(":kotlin-reflect-api"))
}

sourceSets {
    "main" { projectDefault() }
    "test" { none() }
}

runtimeJar()
javadocJar()
sourcesJar()
