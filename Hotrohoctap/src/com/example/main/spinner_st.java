package com.example.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class spinner_st extends Activity {
	// Khai bao bien
	Spinner spinner1;
	EditText u;
	EditText a;
	Button reg;
	ListView list;
	DatabaseHandler db;
	List<User> user;
	List<Student> student;
	List<String> strings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner_st);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		u = (EditText) findViewById(R.id.username);
		a = (EditText) findViewById(R.id.Age);
		reg = (Button) findViewById(R.id.btnReg);
		list = (ListView) findViewById(R.id.listView1);

		//TextView selection = (TextView) findViewById(R.id.selection);

		final DatabaseHandler dbb = new DatabaseHandler(spinner_st.this);
		// goi phuong thuc ok!
		dataSpin();
		listData();

		// Tao su kien onClick
		reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				final String name = u.getText().toString();
				final String age = a.getText().toString();

				if (name.equals("") || age.equals("")) {
					Toast.makeText(spinner_st.this, "Field not null", Toast.LENGTH_SHORT).show();
				} else {
					dbb.addUser(new User(name, age));
					listData();
					Log.d("Reading: ", "Reading all contacts..");
					user = db.getAllUser();
					for (User cn : user) {
						String log = "Id: " + cn.getId() + " ,ID_CLASS: " + cn.getName() + " ,NAME_CLASS: "
								+ cn.getAge();
						// Writing Contacts to log
						Log.d("Name: ", log);
					}
				}

			}
		});
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				// TODO Auto-generated method stub
				// Dialog Create
				final Dialog dialog = new Dialog(spinner_st.this);
				// Include dialog file
				dialog.setContentView(R.layout.dialog_view_del);
				// set dialog title
				dialog.setTitle("You are sure delete this?");

				final Button yes = (Button) dialog.findViewById(R.id.yes);
				final Button no = (Button) dialog.findViewById(R.id.no);

				yes.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dbb.deleteUser(user.get(position));
					dialog.dismiss();
					listData();
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

				return false;
			}
		});

	}

	// Truyen du lieu vao spinner
	private void dataSpin() {
		// khoi tao doi tuong db
		DatabaseHandler db = new DatabaseHandler(spinner_st.this);
		// tao list chua doi tuong lay tu database
		List<Student> stu = db.getAllClass();
		// tao list rong
		List<String> list = new ArrayList<String>();
		// tao vong lap lay gia tri tu list chua du lieu tu database
		for (int i = 0; i < stu.size(); i++) {
			// add du lieu vao list rong
			list.add(stu.get(i).getClass_id());

		}
		// khoi tao adpater truyen du lieu tu list rong -> spinner
		ArrayAdapter<String> data = new ArrayAdapter<String>(spinner_st.this, android.R.layout.simple_spinner_item,
				list);
		// style dropdownmenu khong quan trong
		data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// khoi tao adapter, done ok
		spinner1.setAdapter(data);
	}

	private void listData() {
		// Database hangler
		db = new DatabaseHandler(spinner_st.this);
		user = db.getAllUser();
		strings = new ArrayList<String>();
		for (int i = 0; i < user.size(); i++) {
			strings.add(user.get(i).getId() + " " + user.get(i).getName() + " " + user.get(i).getAge());
		}
		ArrayAdapter<String> datA = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
		list.setAdapter(datA);
		datA.notifyDataSetChanged();
	}
	//

}
