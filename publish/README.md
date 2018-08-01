# Publish
These scripts will help to publish something on [bintray](https://bintray.com).

There are two way to apply this script, depends on how do you plan to release
your libraries.

* [Single](#single-Release) - For single module project
* [Multiple](#modules-release) - For multiple module project

**Warning** this script was built to run Java Projects, if you're planning to
use it for a **Android Project** it will need some more work.

## Common steps
You need to add bintray plugin dependency in your root project.
Take a look how to do it and check out the latest version
[here](https://github.com/bintray/gradle-bintray-plugin).

## Single Release
When your project have single module to be released

Just add the script bellow in your `build.gradle` and replace data below by yours.

```gradle
def gitPath = "github.com/your user name/your project name"
ext.publishing = [
        group          : "com.company",
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

apply from: uri('https://raw.githubusercontent.com/brunodles/GradleHelpers/master/publish/publish.gradle')
```

## Modules Release
When your project have multiple modules, it would be tedious to configure and
maintain all configuration repeated in each module.

Create root publishing in your root `build.gradle`:
```gradle
def gitPath = "github.com/your user name/your project name"
ext.publishingRoot = [
        group          : "com.company",
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

But instead of applying it as we did in [single release](#single-release)
we just created the common information for our library.

Inside our sub-module `build.gradle` we can change resulting `publishing` data.
Like this:

```gradle
ext.publishing = publishingRoot.clone() + [
        artifact: "${publishingRoot.artifact}-submodule-artifact",
        name    : "${publishingRoot.name} Sub-Module-Name"
]
apply from: uri('https://raw.githubusercontent.com/brunodles/GradleHelpers/master/publish/publish.gradle')
```

## Include publish script
You may replace the `apply from: uri(...)` with a local file.
With local file, you don't need internet connection to apply the script.

Download the script and add it into your project.
Choose a way to do it below:

```gradle
apply from: 'publish.gradle'
apply from: rootProject.file('publish.gradle')
```
