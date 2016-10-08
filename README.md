# Gradle plugin

This repo is intended to have all my gradle plugins, tasks and any other gradle files.

Here I'll add custom tasks and build examples of how to use then.

The idea was simple, find and provide a way to reuse those tasks without messing up my `build.gradle`.

This could also be used as a helper to show how to use some of gradle capabilities.

# Setup

# What is in this Repo?

## Cexec Task - Custom Execute

This is a simple executor, sometimes the default `exec` task does not work as expected.
I wrote this simple task just to trigger some terminal commands from a gradle task.

It's on another repo to simplify the releases, the link is [here](https://github.com/brunodles/cexec).
There are few examples about how to use it.

## JitPack

This is just a simple gradle file that can be used to keep some jitpack tasks.

The idea around it is to just import this file, instead of copy the tasks.

```gradle
apply from: 'jitPack.gradle'
apply from: rootProject.file('jitPack.gradle')
apply from: uri('https://raw.githubusercontent.com/brunodles/GradleHelpers/master/jitpack/jitPack.gradle')
```

In the code above we have 3 ways to import a gradle file into another.

1. Apply from relative path.
2. Apply from root project path.
3. Apply from a uri, won't work without internet.

I prefer to copy the script into the `gradle` dir, you will be able to see some projects using this way too.

```gradle
apply from: rootProject.file('gradle/jitPack.gradle')
```

## Ip Getter

This is a script that I found on internet, with it the app is able to change the API address depending on build type.

### Setup
To use it you just need to apply the script.
Take a look on what we did for [jitpack](## JitPack).

```gradle
apply from: 'ip.gradle'
apply from: rootProject.file('ip.gradle')
apply from: uri('https://raw.githubusercontent.com/brunodles/GradleHelpers/master/ipgetter/ip.gradle')
```

### How to use
After the `apply` you need to use the `getApiUrl` method. To do so, you need to create a `buildConfigField` inside your **app level gradle file** `project/app/build.gradle`.

```gradle
apply from: rootProject.file('gradle/ip.gradle')
...
android {
  ...
  defaultConfig {
    ...
    buildConfigField "String", "API_URL", '"http://localhost"'
  }
  buildTypes {
    ...
    debug {
      ...
      buildConfigField "String", "API_URL", "\"${getApiUrl()}\""
    }
  }
}
```

### Another Way to use and more Examples
I also release this as a [gradle plugin](https://github.com/brunodles/IpGetter), with makes it easy to use.

## preBuildSetup - Dynamic Project Properties
Change project properties before build the app.  
This sample is intended to be used as a template for the other tasks.
With it you will be able to replace some project properties dynamically.

### How to use
Just create a new task

```gradle
task preBuildSetup() {
   description "Setup project with dynamic properties before build the app"
   group 'build'
   doLast {
       android.applicationVariants.all { variant ->
           // if you are planning to have multiple dynamic properties changed here, add a verification for the parameter.
           // if (project.hasProperty("<parameter name>"))
           variant.mergedFlavor.<property> = <parameter name>
       }
   }
   // if you are planning to have multiple dynamic properties remove the `project.hasProperty("versionName") &&` bellow. Just check the android.
   onlyIf { project.hasProperty("versionName") && it.hasProperty("android") }
}
project.tasks.getByName('preBuild').dependsOn('preBuildSetup')
```

On the terminal you can type
```bash
./gradlew assembleRelease -PversionName=42
```

### Sample
I use a similar task to change my version name based on my current pr, with this I can build a *pr-release* version.

```gradle
task changeVersionName() {
   description "Change the version name before build the app"
   group 'build'
   doLast {
       android.applicationVariants.all { variant ->
           variant.mergedFlavor.versionName = versionName
           println "Changed the versionName to ${versionName}"
       }
   }
   onlyIf { project.hasProperty("versionName") && it.hasProperty("android") }
}
project.tasks.getByName('preBuild').dependsOn('changeVersionName')
```

# My Gradle Plugins

Here are the links to my other gradle plugins/tasks.

* [Custom Execute](https://github.com/brunodles/cexec)
* [Slack Upload](https://github.com/brunodles/SlackUpload)
* [IpGetter](https://github.com/brunodles/IpGetter)

# Contributing

You can contribute but showing your custom tasks, or submitting a new task.
Issues are welcome, create one and we will discourse about it.
If you saw any error, please reports, it will be a great help.

# Licence
You can use any code you found here, some of then I found on the internet too.

I'm using the MIT Licence, take a look on [Licence](LICENCE.md).

If you're using this plugin, please give me some credits too.

# Sources

## Gradle
* [Writing Custom Task Classes](https://docs.gradle.org/current/userguide/custom_tasks.html)
* [Writing Custom Plugins](https://docs.gradle.org/current/userguide/custom_plugins.html)

## Ip Inject
This one have many sources, I don't remember where I found it at first, it was on 2014.
Now we have many articles about it, so I'll link then.
* [Bartinger](http://bartinger.at/inject-dynamic-host-ip-address-with-gradle/)
* [jeremie-martinez](http://jeremie-martinez.com/2015/05/05/inject-host-gradle/)

## VersionName
Thanks to Barry, he wrote a sample about how to write a plugin to change the version name.
* [Auto rename Android versionName](https://medium.com/@jagonzalez.develop/auto-rename-android-versionname-by-creating-custom-gradle-plugin-2922bbaaaed6#.m69ng66ps)

## JitPack
* [JitPack](https://jitpack.io/)
* [Guide for Gradle Projects](https://jitpack.io/docs/BUILDING/#gradle-projects)
* [GradleModular](https://github.com/jitpack/gradle-modular)
