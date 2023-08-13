plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.2.0-alpha15").apply(false)
    id("com.android.library").version("8.2.0-alpha15").apply(false)
    kotlin("android").version("1.9.0").apply(false)
    kotlin("multiplatform").version("1.9.0").apply(false)
    id("com.squareup.sqldelight").version("1.5.4").apply(false)
    id("com.google.dagger.hilt.android").version("2.41").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
