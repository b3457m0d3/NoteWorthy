package com.graymatterforge;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.graymatterforge.ui.GlobalObject;
import com.samsung.pen.EditorActivity;
import com.samsung.pen.SDKUtils;

@SuppressWarnings("unused")
public class webAppInterface{
    public Context context;
    //initialize the $upr object
    public GlobalObject $upr;
    
    public String[] allRecords;

    /** Instantiate the interface and set the context */
    public webAppInterface(Context c) {
        context = c;
        $upr = (GlobalObject)context;
        allRecords = getBookList();
    }
   
    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
    
    /** get all book ids from the database */
    public String[] getBookList(){
    	return $upr.oT.$DB.returnAllRecords();
    }
    public String[] loadBooks(){
    	String[] books = getBookList();
    	//generate the html for the books as a string
    	int i = 0;
    	int count = books.length;
    	String[] HTML = {};
    	while(i <= count){
    		//HTML[i] = ;
    	}
    	
    		
    	//HTML.concat("<a class='book' id='"+id+"'><img src='"+src+"' /></a>");
    	
    	return HTML;
    }
    
}