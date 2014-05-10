package android.lib.gmf;

import java.util.Hashtable;

import android.graphics.Typeface;
import android.util.Log;
import android.content.Context;


public class FontFace {
	 
	@SuppressWarnings("unused")
	private Context context;
	private static FontFace instance;
	
	private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();
	
	public FontFace(Context _context) {
		context = _context;
	}
 
	public static FontFace getInstance(Context _context) {
		synchronized (cache) {
			if (instance == null)
				instance = new FontFace(_context);
			return instance;
		}
	}
 
	public Typeface get(Context c, String assetPath) {
		synchronized (cache) {
			if (!cache.containsKey(assetPath)) {
				try {
					Typeface t = Typeface.createFromAsset(c.getAssets(), assetPath);
					cache.put(assetPath, t);
				} catch (Exception e) {
					Log.e("Typeface get()", "Failed to load font '" + assetPath + "' : " + e.getMessage());
					return null;
				}
			}
			return cache.get(assetPath);
		}
	}
}