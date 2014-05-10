package android.lib.sqlite.models;

import android.lib.sqlite.annotations.*;
import android.lib.sqlite.Model;
import android.lib.gmf.Forge;

@SuppressWarnings("unused")
@Table("note_page_link")
public class NotePageLink extends Model {

	@Key
	@Column("note_id") public long noteId;

	@Key
	@Column("page_id") public long pageId;

	public NotePageLink() {
        // default constructor
	}
	
	public NotePageLink(long noteId, long pageId) {
		this();
		this.noteId = noteId;
		this.pageId = pageId;
	}
	
	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}

	public void setPageId(long pageId) {
		this.pageId = pageId;
	}
	
	public long getNoteId() {
		return noteId;
	}

	public long getPageId() {
		return pageId;
	}
}