package android.lib.sqlite.models;

import android.lib.sqlite.annotations.*;
import android.lib.sqlite.Model;
import android.lib.gmf.Forge;

@Table("notes")
public class Note extends Model {

    @Key
    @AutoIncrement
    @Column("id") private long id;
    @Column("uuid") public String uuid;
    @Column("file") public String file;
    @Column("cover") public String cover;
    @Column("author") public String author;
    @Column("created") public String created;
    @Column("modified") public String modified;
    @Column("pages") public int pages;
    @Column("fileCount") public int fileCount;
    @Column("lastEditedIndex") public int lastEditedIndex;
    @Column("orientation") public int orientation;
    @Column("rotation") public int rotation;
    @Column("width") public int width;
    @Column("height") public int height;
    @Column("tags") public Boolean tags; 
    @Column("txtOnly") public Boolean txtOnly;
    @Column("favorite") public Boolean favorite;
    @Column("security") public Boolean security;
    @Column("template") public String template;
    

    public long getId() { 
    	return id; 
    }
    
    @Override
    protected void beforeCreate() {
        super.beforeCreate();
        created = Forge.getNow();
        modified = "never";
    }
    
    @Override
    protected void beforeSave() {
        super.beforeSave();
        modified = Forge.getNow();
    }
    
    @Override
    protected void afterDelete() {
    	super.afterDelete();
    	Forge.deleteFile(file);
    }
}