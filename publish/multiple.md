# Multiple
When your project have multiple modules, you can use [multiple.gradle](multiple.gradle) file.

This file is ready to share some of maven properties.

## sample
The setup is done in two levels, to avoid duplicated settings and possible mistakes.

### Root
In your root `build.gradle` add this:
```gradle
def gitPath = "github.com/brunodles/OleasterSuiteRunner"
ext.publishingRoot = [
        group          : "com.brunodles",
        version        : '0.1.1',
        repo           : "TempRepo",
        name           : "Oleaster-SuiteRunner",
        artifact       : "oleaster-suiterunner",
        gitPath        : gitPath,
        site           : "https://$gitPath",
        issueTrackerUrl: "https://$gitPath/issues",
        tag            : 'git rev-parse --abbrev-ref HEAD'.execute().text.trim(),
        override       : true,
        desc           : "A jUnit test runner to format Oleaster tests as a suite.",
        license        : [
                short: "mit",
                name : "MIT License",
                url  : "http://www.opensource.org/licenses/mit-license.php"
        ],
        developer      : [
                name : "Bruno de Lima e Silva",
                email: "dlimaun@gmail.com"
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
