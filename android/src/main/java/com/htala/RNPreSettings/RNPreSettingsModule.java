package com.htala.RNPreSettings;

import android.Manifest;
import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.BatteryManager;
import android.provider.Settings.Secure;
import android.webkit.WebSettings;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.app.ActivityManager;
import android.util.DisplayMetrics;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;

import java.util.HashMap;

import javax.annotation.Nullable;

public class RNPreSettingsModule extends ReactContextBaseJavaModule {

  ReactApplicationContext reactContext;

  public RNPreSettingsModule(ReactApplicationContext reactContext) {
    super(reactContext);

    this.reactContext = reactContext;
  }

  private static final String DATA_FILE_NAME = "HT_RNPreSettings.txt";

  private void writeToFile(String data, Context context) {
      try {
          OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(DATA_FILE_NAME, Context.MODE_PRIVATE));
          outputStreamWriter.write(data);
          outputStreamWriter.close();
      }
      catch (IOException e) {
          Log.e("Exception", "File write failed: " + e.toString());
      } 
  }

  private String readFromFile(Context context) {

    String ret = "";

    try {
        InputStream inputStream = context.openFileInput(DATA_FILE_NAME);

        if ( inputStream != null ) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ( (receiveString = bufferedReader.readLine()) != null ) {
                stringBuilder.append(receiveString);
            }

            inputStream.close();
            ret = stringBuilder.toString();
        }
    }
    catch (FileNotFoundException e) {
        Log.e("login activity", "File not found: " + e.toString());
    } catch (IOException e) {
        Log.e("login activity", "Can not read file: " + e.toString());
    }

    return ret;
}

  private String getPreSettings(){
    return readFromFile(reactContext);
  }

  private void setPreSettings(String settings){
    writeToFile(settings, reactContext);
  }

  @Override
  public String getName() {
    return "RNPreSettings";
  }


  @ReactMethod
  public void setSettings(String settings) {
    setPreSettings(settings);
  }

  @Override
  public @Nullable
  Map<String, Object> getConstants() {
    HashMap<String, Object> constants = new HashMap<String, Object>();

    constants.put("settings", getPreSettings());
    
    return constants;
  }
}
