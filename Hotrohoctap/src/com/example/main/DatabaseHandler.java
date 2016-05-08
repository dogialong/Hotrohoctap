package com.example.main;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{
	// All Static variables
    // Database Version
	private static final int DATABASE_VERSION = 4;
	
	// Database Name
    private static final String DATABASE_NAME = "PolyManager";
    
    // Contacts table name
    private static final String TABLE_lop = "classManager";
    
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CLASS_ID = "class_id";
    private static final String KEY_CLASS_NAME = "class_name";
    
    
    // Contacts table 2
    private static final String TABLE_USER = "userManager";

    //
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "user";
    private static final String KEY_USER_AGE = "age";
    
    
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private static final  String CREATE_CLASS_TABLE = "CREATE TABLE " + TABLE_lop + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CLASS_ID + " TEXT," + KEY_CLASS_NAME + " TEXT" + ")";
    private static final String CREATE_CLASS_USER = "CREATE TABLE " + TABLE_USER + "("
            + KEY_USER_ID + " INTEGER PRIMARY KEY," + KEY_USER_NAME + " TEXT," + KEY_USER_AGE + " TEXT" + ")";
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	db.execSQL(CREATE_CLASS_TABLE);
        db.execSQL(CREATE_CLASS_USER);
        
    }
    
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_lop);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
 
        // Create tables again
        onCreate(db);
    }
    
    // Adding new contact
    public void addClass(Student student) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(KEY_CLASS_ID, student.getClass_id()); // Contact Name
	    values.put(KEY_CLASS_NAME, student.getClass_name()); // Contact Phone Number
	 
	    // Inserting Row
	    db.insert(TABLE_lop, null, values);
	    db.close(); // Closing database connection
    }
    public void addUser(User use) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(KEY_USER_NAME, use.getName().toString()); // Contact Name
	    values.put(KEY_USER_AGE, use.getAge().toString()); // Contact Age
	 
	    // Inserting Row
	    db.insert(TABLE_USER, null, values);
	    db.close(); // Closing database connection
    }
    public Student getClass(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        Cursor cursor = db.query(TABLE_lop, new String[] { KEY_ID,
                KEY_CLASS_ID, KEY_CLASS_NAME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Student student = new Student(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return student;
    }
    public List<Student> getAllClass() {
        List<Student> classlist = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_lop;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setClass_id(cursor.getString(1));
                student.setClass_name(cursor.getString(2));
                // Adding contact to list
                classlist.add(student);
            } while (cursor.moveToNext());
        }
     
        // return contact list
        return classlist;
    }
    
    //Add new Student
    
    
    
    //Get all user
    public List<User> getAllUser(){
    	List<User> userlist = new ArrayList<User>();
    	//Select all query
    	String selectQuery = "SELECT * FROM " + TABLE_USER;
    	SQLiteDatabase db = this.getWritableDatabase();
    	Cursor cursor = db.rawQuery(selectQuery, null);
    	
    	//Loop through all row and add to list
    	if(cursor.moveToFirst()){
    		do {
				User user = new User();
				user.setId(Integer.parseInt(cursor.getString(0)));
				user.setName(cursor.getString(1));
				user.setAge(cursor.getString(2));
				//Add to list
				userlist.add(user);
			} while (cursor.moveToNext());
    	}
    	//Return contact list
		return userlist;
    	
    	
    }
    
 // Deleting single contact
    public void deleteClass(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_lop, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
        db.close();
    }
    
    public void deleteUser(User user){
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.delete(TABLE_USER, KEY_ID + " = ?", new String[] { String.valueOf(user.getId()) });
    	db.close();
    }
    
    // Updating single contact
    public int updateClass(Student student) {
    SQLiteDatabase db = this.getWritableDatabase();
 
    ContentValues values = new ContentValues();
    values.put(KEY_CLASS_ID, student.getClass_id());
    values.put(KEY_CLASS_NAME, student.getClass_name());
 
    // updating row
    return db.update(TABLE_lop, values, KEY_ID + " = ?",
    		new String[] { String.valueOf(student.getId()) });
}
}


