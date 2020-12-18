# Apple Music
by Greg Gushard


A bridge for Flutter apps and Apple Music authentication.

## Getting Started

Add applemusic: to your `pubspec.yaml file`

flutter pub get


**Note**  This build targets AndroidX.  If you are targetting Android 11 (API 30+) you will need to update your `AndroidManifest.xml` file.


```
<manifest
    xmlns:android="http://schemas.android.com/apk/res/android"
  package="com.example.applemusic">
	<queries>
	        <package android:name="com.apple.android.music" />
	</queries>
</manifest>

```

Bump the app level `build.gradle` to 30, or whichever SDK you desire.
