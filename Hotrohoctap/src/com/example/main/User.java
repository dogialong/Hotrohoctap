package com.example.main;

public class User {

	private int _id;
	private String _name;
	private String _Age;
	public User(){
		
	}
	
	
	public User(int id, String name, String age){
		this._id = id;
		this._name = name;
		this._Age = age;
	}
	
	public User(String name, String age){
		this._name = name;
		this._Age = age;
	}
	
	public int getId(){
		return this._id;
	}
	public void setId(int id){
		this._id = id;
	}
	public String getName(){
		return this._name;
	}
	public void setName(String name){
		this._name = name;
	}
	
	public String getAge(){
		return this._Age;
	}
	
	public void setAge(String age){
		this._Age = age;
	}
	
	
}