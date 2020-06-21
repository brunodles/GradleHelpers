# amp.gradle

Using this you can preprocess android manifest.

## Usage

`AndroidManifest`
```xml
<manifest>
  {{key}}
</manifest>
```

`appRoot/module/build.gradle`
```gradle
apply from: uri('https://raw.githubusercontent.com/brunodles/GradleHelpers/master/androidManifestPreprocessor/amp.gradle')
...
amp {
    placeholder "key", "value"
}
```

The point here is to use load value from anywhere (gradle variable, file, environment variable, api).

This gradle plugin allows us to inject whole elements (`<sample field="value"/>`). 
The default android placeholder only allow to change parameters value inside elements.   


## Example

`AndroidManifest`
```
<manifest>

    {{permissions}}

    <application>

        ...

        {{services}}

    </application>
</manifest>
```

`build.gradle`
```
apply plugin: 'com.android.application'

// Applly the plugin
apply from: uri('https://raw.githubusercontent.com/brunodles/GradleHelpers/master/androidManifestPreprocessor/amp.gradle')

android {
    ...
}

amp {
    placeholder "permissions", contentOrEmpty("${project.buildDir}/generated/syncHelperManifest/permissions")
    placeholder "services", contentOrEmpty("${project.buildDir}/generated/syncHelperManifest/services")
}

static String contentOrEmpty(String filePath) {
    def file = new File(filePath)
    return (file.exists()) ? file.text : "<!-- empty -->"
}
```