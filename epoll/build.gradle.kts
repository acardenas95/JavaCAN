plugins {
    id("tel.schich.javacan.convention.native")
}

dependencies {
    api(project(":core"))
    testImplementation(testFixtures(project(":core")))
}
