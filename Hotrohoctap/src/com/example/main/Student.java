package com.example.main;

public class Student {
	int _id;
	String _class_id;
	String _class_name;
	
	public Student(){
		
	}
	public Student(int id, String class_name, String class_id){
		this._id = id;
		this._class_id = class_id;
		this._class_name = class_name;
		
	}
	public Student(String class_name, String class_id){
		this._class_id = class_id;
		this._class_name = class_name;
	}
	public String getClass_id(){
		return this._class_id;
	}
	public void setClass_id(String class_id){
		this._class_id = class_id;
	}
	public int getId(){
		return this._id;
	}
	public void setId(int id){
		this._id = id;
	}
	public String getClass_name(){
		return this._class_name;
	}
	public void setClass_name(String class_name){
		this._class_name = class_name;
	}
	
}
