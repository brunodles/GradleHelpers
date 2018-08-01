# Multiple
When your project have multiple modules, you can use [multiple.gradle](multiple.gradle) file.

This file is ready to share some of maven properties.

## sample
The setup is done in two levels, to avoid duplicated settings and possible mistakes.

### Root
In your root `build.gradle` add this:
```gradle
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        // add this plugin in your classpath
        // https://github.com/bintray/gradle-bintray-plugin
        classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.7.3'
    }
}

// replace data below by yours
def gitPath = "github.com/your user name/your project name"
ext.publishingRoot = [
        group          : "com.brunodles",
        version        : '0.1.1',
        repo           : "your repo on bintray",
        name           : "My awesome library",
        artifact       : "my-awesome-library",
        gitPath        : gitPath,
        site           : "https://$gitPath",
        issueTrackerUrl: "https://$gitPath/issues",
        tag            : 'git rev-parse --abbrev-ref HEAD'.execute().text.trim(),
        override       : false,
        desc           : "a description",
        license        : [
                short: "mit",
                name : "MIT License",
                url  : "http://www.opensource.org/licenses/mit-license.php"
        ],
        developer      : [
                name : "your name",
                email: "email"
        ]
]
```

### Module
In your module `build.gradle` add this:
```gradle
ext.publishingModule = [
        artifact: "${publishingRoot.artifact}-submodule",
        name    : "${publishingRoot.name} Sub-Module"
]
apply from: rootProject.file('gradle/publish.gradle')
```
Here can use the `root` values to compose the module values.
