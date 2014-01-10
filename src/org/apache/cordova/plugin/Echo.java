package org.apache.cordova.plugin;

import android.graphics.*;
import android.media.*;
import android.os.*;
import java.io.*;
import org.apache.cordova.api.*;
import org.json.*;

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
	//	final String path = android.os.Environment.DIRECTORY_DCIM;
		File file[] = Environment.getExternalStorageDirectory().listFiles();
	//	File file[] = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).listFiles();
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
						
						BitmapFactory.Options options = new BitmapFactory.Options();
						options.inJustDecodeBounds = true;

//Returns null, sizes are in the options variable 
						BitmapFactory.decodeFile(filePath, options);
						int width = options.outWidth; 
						int height = options.outHeight; //If you want, the MIME type will also be decoded (if possible) String type = options.outMimeType;
						JSONObject jso = new JSONObject();
						int rotate = 0;
						try {
							
							rotate = getImgOrientation(file1[i]);
							jso.put("width", width);
							jso.put("height",height);
							jso.put("rotate",rotate);
							toReturn.put(filePath, jso);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//Bitmap bitMapObj= BitmapFactory.decodeFile(filePath);
						
						
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

class Img{
	private int width;
	private int height;
	private int rotate;
	
	public Img(int w, int h, int r){
		this.width=w;
		this.height=h;
		this.rotate=r;
	}
	public int getWidth(){
		return width;
	}
    public int getHeight(){
		return height;
	}
	public int getRotate(){
		return rotate;
	}
	
	
}
