package com.htala.RNPreSettings;

import android.content.Context;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Map;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

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
      catch (Exception e) { }
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
    } catch (Exception e) { }

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
