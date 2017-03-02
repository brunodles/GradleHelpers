# Single
When your project have single module to be released, you can use [single.gradle](single.gradle) file.

## Sample
Just add the script bellow in your `build.gradle`

```gradle
ext.publishing = [
        group          : "com.brunodles",
        version        : '0.1.1',
        repo           : "TempRepo",
        name           : "Oleaster-SuiteRunner",
        artifact       : "oleaster-suiterunner",
        site           : "https://github.com/brunodles/OleasterSuiteRunner",
        issueTrackerUrl: "https://github.com/brunodles/OleasterSuiteRunner/issues",
        git            : "git@github.com:brunodles/OleasterSuiteRunner.git",
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