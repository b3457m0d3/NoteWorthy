package android.lib.gmf;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import com.samsung.android.sdk.pen.document.SpenNoteDoc;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * @** USEFUL code snippets
 * @author b3457m4c
 *  [---convert array to ArrayList:-------------------------------]
 *  List<String> list = new ArrayList<String>( Arrays.asList( s));
 *  [---loop through ArrayList:-----------------------------------]
 *  for( String s : ArrayList) {
 *      //s is the current item
 *  }
 *
 */

@SuppressWarnings("unused")
public class Forge{
	
	public Context context;
	public Forge instance;
	public final String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
	public Rect mScreenRect;
	
	public Forge getInstance(){
		if (instance == null) 
            instance = new Forge(context);
		return instance;
	}
	
    public Forge(Context cntxt) {
        context = cntxt;
        instance = this;
	}
	//** Get the dimension of the device screen ==================================
	public Rect getScreenDimensions(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        mScreenRect = new Rect();
        display.getRectSize(mScreenRect);
        return mScreenRect;
	}
	
	//** Array Functions =========================================================
	//// Check an ArrayList<String> for a value
	public boolean listContains(ArrayList<String> list, String val){
		int count = Collections.frequency(list, val);
		return (count>0)? true : false;
	}
	// ===========================================================================
	//// Convert a String[] array into an ArrayList<String>
	public ArrayList<String> arrayToList(String[] array){
		List<String> list = new ArrayList<String>( Arrays.asList(array));
		return (ArrayList<String>) list;
	}
	// ===========================================================================
	//// Convert an arraylist into a String[] array 
	public String[] listToArray(List<String> array){
		String[] arr = {};
		if(!array.isEmpty()){
			int i = 0;
			int count = array.size();
			while(i <= count){
				arr[i] = array.get(i);
				i++;
			}
			return arr;
		} else return arr;		
	}
	// ===========================================================================
	//// Split a string into an array by a custom delimiter or a comma
	public static String[] explode(String str, String delimiter){
		if(delimiter == null) delimiter = ",";
		return (str.contains(delimiter))? str.split(delimiter) : new String[]{str};
	}
	//** FileSystem Functions =====================================================
    //// create the folders in the filePath if they dont exist, returns a boolean
	public boolean mkDir(String filePath){
		String _path = addSlash(filePath);
		String path = sdPath + _path;
		File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Toast.makeText(context, "Save Path Creation Error", Toast.LENGTH_SHORT).show();
                return false;
            } else {
            	return true;
            }
        } else return true;
	}
	// =============================================================================
    //// Read the contents of an html file in the assets folder into a string
	public void HTMLFileContents(String HTML) throws IOException{
		InputStream is = context.getAssets().open(HTML+".html");
		int size = 0;
		try {
			size = is.available();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		byte[] buffer = new byte[size];
		is.read(buffer);
		is.close();
	
		String str = new String(buffer);
		str = str.replace("old string", "new string");
	}
	//==============================================================================
	//// load string data in a webview as html 
    public void loadStrAsHTML(WebView view, String str){
    	view.loadDataWithBaseURL("fake://not/needed", str,"text/html","utf-8","");
    }
	// =============================================================================
    //// Adds a slash to the end of a filepath if it needs it	
	public static String addSlash(String strPath){
		String slash = (strPath.endsWith(File.separator))?"":File.separator;
		return strPath.concat(slash);
	}
	// =============================================================================
    ////
	public void renameFile(String strFilePath, String newPath) {
		File file = new File(strFilePath);
	    file.renameTo(new File(newPath));
    }
	// =============================================================================
    ////
	public void moveFile(String strFilePath, String newPath){
		renameFile(strFilePath,newPath);
	}
	// =============================================================================
    ////
	public static boolean deleteFile(String strFilePath){
		File file = new File(strFilePath);
		return (file.delete()) ? true : false;
	}
	// =============================================================================
    //// A 
	public FilenameFilter filterFactory(String fileExt){
		return new txtFileFilter(fileExt);
	}
	// =============================================================================
    //// A class that filters a list of files by their extension.
	class txtFileFilter implements FilenameFilter {
		public String fileExt;
		public txtFileFilter(String ext){
			if(ext != null){
				if(!ext.contains(",")){
					fileExt = "."+ext;
				} else {
					String[] split = ext.split(",");
					String output = ".";
					for(int i = 0; split.length >= i; i++){
						if(i != split.length){
							output = output + split[i] + " || .";
						}
					}
					fileExt = output;
				}
			} else {
				fileExt = ".spd";
			}
		}
        @Override
        public boolean accept(File dir, String name) {
            return (name.endsWith("."+fileExt));
        }
    }
	
	//** Time & Date ===============================================================
    //// Gets a SQLite formatted time stamp for right now
	@SuppressLint("SimpleDateFormat")
	public static String getNow(){
        // set the format to sql date time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
	
	//** Type Conversion ===========================================================
    //// Convert a string to an integer
	public int stringToInt(String str){
		try {
            int num = Integer.parseInt(str);
            return num;
        } catch(NumberFormatException nfe) {
			Toast.makeText(context, "Could not parse " + nfe, Toast.LENGTH_SHORT).show();
			return -1;
        } 
	}
	//** HTTP Functions ===========================================================
    //// Use name value pairs to format an Http query string
	public void formatHttpQueryString(){
		HttpPost request = new HttpPost();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", "value"));
		try {
			request.setEntity(new UrlEncodedFormEntity(params));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//httpClient.execute(request);
	}
}