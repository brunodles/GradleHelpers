# Single
When your project have single module to be released, you can use [single.gradle](single.gradle) file.

## Setup
Just add the script bellow in your `build.gradle`

```gradle
ext.maven = [
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
version   | The version of this project, [read this](http://server.org/) | 1.0.0
repo      | The name of the repository on [bintray](https://bintray.com/) | TestRepo
name      | Name of the project | PicPicker
artifact  | Used to compound the usage url | picpicker
site      | Site of the library, maybe shown for the users | http://brunodles.github.io/picpicker
git       | Git url for the Source code | git@github.com:brunodles/picpicker.git
tag       | Something to identify the release | rc
