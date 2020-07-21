
# Guide for development of this app.

### Using Dhaka for generation of documents.
Apply the plugin to the `build.gradle` of the module(s) for which you would like to generate documentation

    apply plugin: 'org.jetbrains.dokka'
    dokka {
        outputFormat = 'html' // use 'javadoc' to get standard java docs
        outputDirectory = "$buildDir/javadoc"
        configuration {
            includeNonPublic = false
            skipEmptyPackages = true
            skipDeprecated = true
            reportUndocumented = true
            jdkVersion = 8
        }
    }
