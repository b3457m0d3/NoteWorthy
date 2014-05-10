package android.lib.sqlite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.lib.gmf.Forge;

@SuppressWarnings("unused")
@SuppressLint("DefaultLocale")
public class TableMaker {
		private static TableMaker instance;
		public static List<String> TableStructure = new ArrayList<String>(); 
		public static String TableName;
		private static Context context;
		
		public TableMaker(Context appContext,String name){
			context = appContext;
			TableName = (name == null)?"NoteWorthy":name;
			instance = this;
		}
		public static TableMaker getInstance(Context context){
			if(instance == null){
				instance = new TableMaker(context,null);
			} return instance;
		}
		public static void open(){
			TableStructure.add("CREATE TABLE IF NOT EXISTS " +TableName+ " ( ");
		}
		public static String buildDefaultTable(){
			open();
			addField("id/integer/primary key+autoincrement");
			addField("uuid/varchar/255");
			addField("file/varchar/255");
			addField("pages/integer/");
			addField("cover/varchar(255)/");
			addField("author/varchar(140)/");
			addField("created/timestamp/not null+default+current_timestamp");
			addField("modified/timestamp/not null+default+current_timestamp");
			addField("lastEditedIndex/integer/");
			addField("orientation/integer/");
			addField("rotation/integer/");
			addField("width/integer/");
			addField("height/integer/");
			addField("tags/boolean/");
			addField("txtOnly/boolean/");
			addField("favorite/boolean/");
			addField("security/boolean/");
			addField("template/varchar");
			addField("fileCount/boolean/");
			close();
			return toSQL();
		}
		public static void close(){
			TableStructure.add(")");
		}
		public static String toSQL(){
			TableStructure.removeAll(Arrays.asList("", null));

		    // If this list is empty, it only contained blank values
		    if( TableStructure.isEmpty()) {
		        return "";
		    }
		    close();//finish the SQL statement before export
		    // Format the ArrayList as a string, similar to implode
		    StringBuilder builder = new StringBuilder();
		    builder.append(TableStructure.remove(0));

		    for( String s : TableStructure) {
		        builder.append( ",");
		        builder.append( s);
		    }

		    return builder.toString();
		}
		public static void addField(String nameTypeExtra){
			String[] args = Forge.explode(nameTypeExtra,"/");
			String field = args[0];
			field.concat(" ");
			field.concat(args[1].toUpperCase());
			field.concat(" ");
			String extra = args[2];
			if(extra != null){
				if(extra.contains("+")){
					String[] extras = extra.split("+");
					int counter = 0;
					while (counter <= extras.length){
						//make sure the last item doesn't get a trailing space
						boolean notLast = (counter<extras.length)? true : false;
						String str  = (notLast)? " " : "";						
						field.concat(extras[counter].toUpperCase());
						field.concat(str);
						counter++;
					}
					
				} 
			}
			TableStructure.add(field);
			
	}
}