package android.lib.gmf;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class PDialog {
	
	private static PDialog instance;
	
	public PDialog(){
		instance = this;
	}
	
	public static PDialog getInstance(){
		if(instance == null)
			instance = new PDialog();
		return instance;
	}

        public static void dialogBox(Context context, String title,
                        String message, 
                        String messageYes, DialogInterface.OnClickListener listenerYes, 
                        String messageNo, DialogInterface.OnClickListener listenerNo){
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);                 
                        alert.setTitle(title);  
                        alert.setMessage(message);
                        alert.setPositiveButton(messageYes, listenerYes);
                    alert.setNegativeButton(messageNo, listenerNo);
                alert.show();
                return ;
        }
        
        
        public static void dialogBox(Context context, String title,
                        String message, 
                        String messageYes, DialogInterface.OnClickListener listenerYes,
                        String messageNo, DialogInterface.OnClickListener listenerNo,
                        String messageCancel,DialogInterface.OnClickListener listenerNeutral){
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);                 
                        alert.setTitle(title);  
                        alert.setMessage(message);
                        alert.setPositiveButton(messageYes, listenerYes);
                    alert.setNegativeButton(messageNo, listenerNo);
                    alert.setNeutralButton(messageCancel, listenerNeutral);
                        alert.show();
                return ;
        }
        
        
        public static void dialogBox(Context context, String title, String message,
                        String messageNeutre, DialogInterface.OnClickListener listenerNeutral){
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);                 
                        alert.setTitle(title);  
                        alert.setMessage(message);
                    alert.setNeutralButton(messageNeutre, listenerNeutral);
                alert.show();
                return ;
        }
}