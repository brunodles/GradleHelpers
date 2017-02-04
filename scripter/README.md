# Scripter

This is just a helper to download files.

My main idea around this script was to update my other scripts, so I can keep every project updated. 

## How to use

In your root `build.gradle` create an extension like that.

```gradle
ext.scripter = [
    'destination/file.extension' : 'http://url_to_my.file',
    'destination/file.extension' : 'http://url_to_my.file',
    'destination/file.extension' : 'file://path_to_my_file.file'
]
```