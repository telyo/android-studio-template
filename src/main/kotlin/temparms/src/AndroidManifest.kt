package temparms.src

import temparms.ArmsPluginTemplateProviderImpl

fun armsManifest(provider: ArmsPluginTemplateProviderImpl)= """
    
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
package="${provider.appPackageName.value}">
    <application>
        ${if (provider.isModule.value){
    """
        <activity android:name="${provider.activityPackageName.value}.${provider.pageName}Activity">
	        <intent-filter>
	            <action android:name="android.intent.action.MAIN" />
	            <category android:name="android.intent.category.LAUNCHER" />
	        </intent-filter>
	    </activity> 
    """
}else{
    """
        <activity
	        android:name="${provider.activityPackageName.value}.${provider.pageName}Activity"
            android:screenOrientation="portrait"
	        />
    """
} }
    </application>
</manifest>
"""