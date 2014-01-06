package org.apache.cordova.plugin;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.util.*;
import 	android.os.Environment;
import android.media.ExifInterface;

/**
 * This class echoes a string called from JavaScript.
 */
public class Echo extends CordovaPlugin {
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("echo")) {
			String message = args.getString(0); 
			this.echo(message, callbackContext);
			return true;
		}
		return false;
	}

	private void echo(String message, CallbackContext callbackContext) {
		if (message != null && message.length() > 0) {



			try{
				callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, getImgPaths()));
			}
			catch(JSONException e){
				callbackContext.error("ERROR!");
			}
		} else {
			callbackContext.error("Expected one non-empty string argument.");
		}
	}

	private JSONObject getImgPaths() throws JSONException {
		//final String path = android.os.Environment.DIRECTORY_DCIM;
		File file[] = Environment.getExternalStorageDirectory().listFiles();
		
		JSONObject toReturn = new JSONObject();
		
		recursiveFileFind(file, toReturn);
		return toReturn;
	}

	public void recursiveFileFind(File[] file1, JSONObject toReturn) throws JSONException{
		int i = 0;
		String filePath="";
		if(file1!=null){
			while(i!=file1.length){
				filePath = file1[i].getAbsolutePath();
				if(file1[i].isDirectory()){
					File file[] = file1[i].listFiles();
					recursiveFileFind(file, toReturn);
				}
				else{
					if(file1[i].getName().toLowerCase().endsWith(".jpg") ||file1[i].getName().toLowerCase().endsWith(".jpeg")){
						try {
							int rotate = getImgOrientation(file1[i]);
							toReturn.put(filePath, rotate);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				i++;
				//Log.d(i+"", filePath);
			}
		}
	}
	
	public int getImgOrientation(File imageFile) throws IOException{
		int rotate = 0;
        ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
        case ExifInterface.ORIENTATION_ROTATE_270:
            rotate = 270;
            break;
        case ExifInterface.ORIENTATION_ROTATE_180:
            rotate = 180;
            break;
        case ExifInterface.ORIENTATION_ROTATE_90:
            rotate = 90;
            break;
        }
        return rotate;
	}
}
