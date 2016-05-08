package com.example.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ViewClass extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_view_add);
		final DatabaseHandler db = new DatabaseHandler(ViewClass.this);
		final ListView list = (ListView)findViewById(R.id.list);
		// danh sach Lop
		final List<Student> student = db.getAllClass();
		
		// khoi tao adapter , truyen vao arraylist la String
		
	    List<String> strings = new ArrayList<String>();
	    for (int i = 0; i < student.size(); i++) {
	    	strings.add("" + student.get(i).getId() + "             " + student.get(i).getClass_id() + "                  " + student.get(i).getClass_name());
	    	
		}
		
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, strings);
		list.setAdapter(arrayAdapter);
		list.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    final int position, long arg3) {
            	// Make sth on longclick
            	
            	//dialog
            	// Create custom dialog object
				final Dialog dialog = new Dialog(ViewClass.this);
				// Include dialog.xml file
                dialog.setContentView(R.layout.dialog_view_del);
                // Set dialog title
                dialog.setTitle("Xoa");   
                
                final Button yes = (Button)dialog.findViewById(R.id.yes);
                final Button no = (Button)dialog.findViewById(R.id.no);
                 
                yes.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						db.deleteClass(student.get(position));
						dialog.dismiss();
						finish();
					}
				});
            	no.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
            	dialog.show();
            	
            	return true;
            }
        }); 
		
		
		
		
	}
}
