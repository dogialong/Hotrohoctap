package com.example.main;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class QLSVActivity extends Activity {
	
	
	MainActivity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qlsv);
		Button addc = (Button)findViewById(R.id.btnadd);
		Button view = (Button)findViewById(R.id.btnprint);
		Button manage = (Button)findViewById(R.id.btnmanager);		
		manage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QLSVActivity.this, spinner_st.class);
				startActivity(intent);
			}
		});
		
		
		//button 1
		addc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Create custom dialog object
				Dialog dialog = new Dialog(QLSVActivity.this);
				// Include dialog.xml file
                dialog.setContentView(R.layout.dialog_add);
                // Set dialog title
                dialog.setTitle("Them lop");              
                final EditText id_class = (EditText)dialog.findViewById(R.id.id_class);
                final EditText name_class = (EditText)dialog.findViewById(R.id.name_class);
                Button add_clear = (Button)dialog.findViewById(R.id.add_clear);
                Button add_save = (Button)dialog.findViewById(R.id.add_save);
                final DatabaseHandler db = new DatabaseHandler(QLSVActivity.this);
                add_clear.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						id_class.setText(null);
						name_class.setText(null);
					}
				});
                add_save.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						String id = id_class.getText().toString();
						String name = name_class.getText().toString();
						if(id.equals("") || name.equals("")){
							Toast.makeText(QLSVActivity.this, "fail", Toast.LENGTH_SHORT).show();
						} else {
							Log.d("Insert: ", "Inserting .."); 
					        db.addClass(new Student(id, name));
					        Toast.makeText(QLSVActivity.this, "success", Toast.LENGTH_LONG).show();
					     // Reading all contacts
					        Log.d("Reading: ", "Reading all contacts.."); 
					        List<Student> student = db.getAllClass();       
					         
					        for (Student cn : student) {
					            String log = "Id: "+cn.getId()+" ,ID_CLASS: " + cn.getClass_id() + " ,NAME_CLASS: " + cn.getClass_name();
					                // Writing Contacts to log
					        Log.d("Name: ", log);
					        }
						}
					
					}
					
				});
                dialog.show();
                
			}
		});
		
		//button 2
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(QLSVActivity.this, ViewClass.class);
				startActivity(i);
				
			}
		});
	}

}
