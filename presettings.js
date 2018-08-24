/**
 * @providesModule react-native-presettings
 */
import { NativeModules } from 'react-native';

var RNPreSettings = NativeModules.RNPreSettings;

var lastSettings = "";
var isSet = false;

export default {

  getSettings: function() {
    if(isSet) return lastSettings;
    return RNPreSettings.settings;
  },
  setSettings: function(value) {
    RNPreSettings.setSettings(value);
    isSet = true;
    lastSettings = value;
  }
};
