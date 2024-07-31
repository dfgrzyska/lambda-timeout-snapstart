plugins {
    application // <1>
}

repositories {
    mavenCentral() // <2>
}
dependencies { // dependencies for module app
    implementation("com.amazonaws:aws-lambda-java-core:1.2.2")
    implementation("org.crac:crac:1.4.0")
}


tasks.jar {
    archiveFileName = "${rootProject.name}.jar"
    manifest.attributes["Main-Class"] = "test.lambda.timeout.application.SimpleHandler"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
