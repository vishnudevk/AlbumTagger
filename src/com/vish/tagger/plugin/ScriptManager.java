package com.vish.tagger.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class ScriptManager extends CordovaPlugin {
	public static final String CALLER_ENTRY = "callerEntry";
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if(CALLER_ENTRY.equals(action)){
			Log.i(ScriptManager.class.getName(), "Inside callerEntry");
			callbackContext.success("success dude");
			return true;
		}
		callbackContext.error("Invalid action");
		return false;
	}
}
