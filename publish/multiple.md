# Multiple
When your project have multiple modules, you can use [multiple.gradle](multiple.gradle) file.

This file is ready to share some of maven properties.

## Setup
The setup is done in two levels, to avoid duplicated settings and possible mistakes.

### Root
In your root `build.gradle` add this:
```gradle
ext.mavenRoot = [
        group   : "<com.group>",
        version : '<version>',
        repo    : "maven_repo",
        name    : "Presentable Name",
        artifact: "artifactname",
        site    : "http://group.com/artifactname",
        git     : "git@github.com:user/artifactname.git",
        tag     : "<tag>"
]
```
name      | description | Sample
----------|-------------|-------
group     | Usually the same as application package | com.brunodles
version   | The version of this project, [read this](http://semver.org/) | 1.0.0
repo      | The name of the repository on [bintray](https://bintray.com/) | TestRepo
name¹     | Name of the project | Environment Mods
artifact¹ | Used to compound the usage url | environmentmods
site      | Site of the library, maybe shown for the users | http://brunodles.github.io/environmentmods
git       | Git url for the Source code | git@github.com:brunodles/environmentmods.git
tag       | Something to identify the release | rc
*1 - it will be used just to help the module setup*

### Module
In your module `build.gradle` add this:
```gradle
ext.mavenModule = [
        artifact: "${mavenRoot.artifact}-submodule",
        name    : "${mavenRoot.name} Sub-Module"
]
apply from: rootProject.file('gradle/publish.gradle')
```
name     | description | sample
---------|-------------|-------
artifact | Used to compound the usage url | ${maven.artifact}-processor
name     | Name of the module | ${maven.name} Processor

Here can use the `root` values to compose the module values.
