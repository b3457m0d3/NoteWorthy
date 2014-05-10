package android.lib.gmf;

import java.io.IOException;

import com.samsung.android.sdk.pen.document.SpenInvalidPasswordException;
import com.samsung.android.sdk.pen.document.SpenNoteDoc;
import com.samsung.android.sdk.pen.document.SpenNoteFile;
import com.samsung.android.sdk.pen.document.SpenUnsupportedTypeException;
import com.samsung.android.sdk.pen.document.SpenUnsupportedVersionException;

import android.os.FileObserver;
import android.content.Context;
import android.lib.gmf.Forge;
import android.lib.sqlite.models.Note;
import android.lib.sqlite.Query;


public class NoteObserver extends FileObserver {
	public NoteObserver	instance;
	public Context context;
	public String path;
	public String MovedFrom;
	public NoteObserver(String strPath) {
		super(strPath, ALL_EVENTS);
		instance = this;
		startWatching();
		path = Forge.addSlash(strPath);
	}
	
	public NoteObserver getInstance(){
		if(instance == null){
			return new NoteObserver(path);
		} else return instance;
	}
	
	@Override
    public void onEvent(int event, String file) {

		event &= FileObserver.ALL_EVENTS;
		
		path.concat(file);
		/* TODO 
		 * - testing
		 * 
		 * 
		 */
		//** New File or SubDirectory Created 
        if(event == FileObserver.CREATE && !file.equals(".probe")){ 
        	setNoteInfo(context,path);
        }
        if(event == FileObserver.DELETE && !file.equals(".probe")){ 
        	String sql = "SELECT * FROM notes WHERE file = ?";       	
        	Note note = Query.one(Note.class, sql,file).get();
        	if(note.exists()){
        		Forge.deleteFile(note.file);
        		note.delete();
        	}
        }
        if(event == FileObserver.MODIFY && !file.equals(".probe")){ 
        	String sql = "SELECT * FROM notes WHERE file = ?";       	
        	Note note = Query.one(Note.class, sql,file).get(); 
        	if(note.exists()){
        		note.modified = Forge.getNow();
        		note.save();
        	}
        }
        if(event == FileObserver.MOVED_FROM && !file.equals(".probe")){ 
        	//save the file to a member variable
        	MovedFrom = path; 
        }
        if(event == FileObserver.MOVED_TO && !file.equals(".probe")){ 
        	//update the path to the file in the database
        	String sql = "SELECT * FROM notes WHERE file = ?";       	
        	Note note = Query.one(Note.class, sql,MovedFrom).get(); 
        	if(note.exists()){
        		note.file = file;
        		note.save();
        	}
        }
    }
	public void close() {
        super.finalize();
    }
	public void setNoteInfo(Context context, String path){
		SpenNoteDoc noteDoc;
		Note note = new Note();
		try {
			noteDoc = new SpenNoteDoc(context, path, 0, SpenNoteDoc.MODE_WRITABLE);
			note.uuid            = noteDoc.getId();
			note.file            = path;
			note.cover           = noteDoc.getCoverImagePath(); 
			note.template        = noteDoc.getTemplateUri();
			note.pages           = noteDoc.getPageCount();
			note.lastEditedIndex = noteDoc.getLastEditedPageIndex();
			note.height          = noteDoc.getHeight();
			note.width           = noteDoc.getWidth();
			note.rotation        = noteDoc.getRotation();
			note.orientation     = noteDoc.getOrientation();
			note.fileCount       = noteDoc.getAttachedFileCount();
			note.tags            = noteDoc.hasTaggedPage();
			note.txtOnly         = noteDoc.isAllPageTextOnly();
			note.security        = SpenNoteFile.isLocked(path);
			note.favorite        = SpenNoteFile.isFavorite(path);
			note.save();
			noteDoc.close();

		} catch (SpenInvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SpenUnsupportedTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SpenUnsupportedVersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}