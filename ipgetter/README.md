# ip.gradle

There are two gradle files here, so we have two ways to use this.

## First way
On this way we need to import the gradle file and call `getApiUrl()` where we want.

Take a look bellow.

`appRoot/module/build.gradle`
```gradle
apply from: uri('https://raw.githubusercontent.com/brunodles/GradleHelpers/master/ipgetter/ip.gradle')
...
android {
  ...
  defaultConfig {
    ...
    buildConfigField "String", "API_URL", '"http://my.real.api.com"'
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

You may need to change the _interface_ or the _port_.
To do so, just create `ip.properties` in the root dir of your project.
`appRoot/ip.properties`
```properties
api.local_ip_interface=wlan0
api.local_port=80
```

## Second Way
This way provides a similar api, but you will write your PC properties into `gradle.properties`.

The ideal around here is to not put the properties into your project,
since it will be probably the same for all projects.

To set it up, you just need to edit/create a `gradle.properties` in your gradle home.
The path is `~/.gradle/gradle.properties`.

Here we added a new property, `api.use_mock`, this is to enable us to change the api without create a new `BuildType`.
So we can change it outside the project.
This also added the possibility to change the api url on command line.
`gradle -Papi.use_mock=false install`

I also added a task to check the api properties.
`gradle printIpProps`

`~/.gradle/gradle.properties`
```properties
api.use_mock=true
api.local_ip_interface=wlp9s0
api.local_port=3000
```

`appRoot/module/build.gradle`
```gradle
apply from: uri('https://raw.githubusercontent.com/brunodles/GradleHelpers/master/ipgetter/ip2.gradle')
...
android {
  ...
  defaultConfig {
    ...
    buildConfigField "String", "API_URL", "\"${getApiUrl("http://my.real.api.com")}\""
  }
}
```