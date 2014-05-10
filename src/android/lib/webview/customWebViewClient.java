package com.graymatterforge;

import java.io.File;

import com.samsung.pen.EditorActivity;

//import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.app.ProgressDialog;
//import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressWarnings("unused")
public class customWebViewClient extends WebViewClient {
    
    public Context oContext;
    public ProgressDialog pd = null;
    
    public customWebViewClient (Context context){
        oContext = context;
        pd = new ProgressDialog(oContext);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        
    }
    
    @Override
    public void onPageFinished(WebView view, String url){
        if (pd != null && pd.isShowing()){
            pd.dismiss();
        }
    }

    
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
		if( !url.contains("activity://") ) {
		//check for the activity schema
			pd.show();
			view.loadUrl(url);
		    return false;
		} else {
			//Pass it to the system to be handled
			pd.show();
			
			
			
			//set up cases 
			if( url.contains("") ){
				Intent intent = new Intent(oContext, EditorActivity.class);
				intent.putExtra("", "Swinging on a star. ");
				oContext.startActivity(intent);
			} else if( url.contains("") ){
				
			}
		//Tell the WebView you took care of it.
			return true;
		}
    }
}