plugins { // apply plugins with specific versions
    java // basic plugin for java projects
}

allprojects { // apply configuration from the common.gradle file to all subprojects
    apply {
        plugin("java")
        plugin("idea")
    }
    // source and target setup for all modules
    java.sourceCompatibility = JavaVersion.VERSION_21 // in most cases these options should have the same value,
    java.targetCompatibility = JavaVersion.VERSION_21 // although they differ in meaning

    configurations.all {
        resolutionStrategy {
            cacheChangingModulesFor(0, "seconds") // do not cache snapshot dependencies
        }
    }
}