# Gradle plugin

This repo is intended to have all my gradle plugins.

Here I'll add custom tasks and build examples of how to use then.

The idea is simple, provide a way to reuse those tasks without messing up my `build.gradle` file with these
custom tasks.

This could also be used as a helper to show how to use some of gradle capabilities.

# How to

## Custom Execute (cexec)

This is a simple executor, sometimes the default `exec` task does not work as expected.

I wrote this simple task just to trigger some terminal commands from a gradle task.
It may looks strange if you only know the basic gradle commands.

Try to imagine the following situations:
  * Run something before install the app on android. Like the `development backEnd App`, `mocked Api` or anything like thad.
  * Grab information from the PC to use on the app. When I'm working with both backEnd and Android App,
  I want to use *my pc IP address* as `API_URL` but I don't want to keep looking on my IP address manually.
  So we can use a task to do that for us.

I use the `cexec` task to do those tasks. Keep reading, the `IP Grabber` will have it's own task.

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

## JitPack
* [JitPack](https://jitpack.io/)
* [Guide for Gradle Projects](https://jitpack.io/docs/BUILDING/#gradle-projects)
* [GradleModular](https://github.com/jitpack/gradle-modular)