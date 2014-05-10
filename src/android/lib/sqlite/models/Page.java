package android.lib.sqlite.models;

import android.lib.sqlite.annotations.*;
import android.lib.sqlite.Model;
import android.lib.gmf.Forge;

@SuppressWarnings("unused")
@Table("page")
public class Page extends Model {

	@AutoIncrement
    @Key @Column("id") private long id;
	@Column("uuid") public String uuid;
	@Column("index") public int index;
	@Column("thumnbnail") public String thumb;
		
	public long getId() {
		return id;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getThumb(){
		return thumb;
	}
	
	public void setThumb(String thumb){
		this.thumb = thumb;
	}
	
	@Override
	public boolean isValid() {
		return uuid != null && !uuid.isEmpty() && index >-1;
	}
}