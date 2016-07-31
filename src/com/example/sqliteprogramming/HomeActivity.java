package com.example.sqliteprogramming;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends ListActivity {

	ListView listViewStudent;
	ArrayList<Student> listStudent = new ArrayList<Student>();
	ArrayAdapter<Student> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		listViewStudent = getListView();

		listViewStudent.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			
				
				AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
				final Student s = listStudent.get(arg2);
				builder.setTitle("Student" + s.getName());
				builder.setMessage("What Action you want to perform");
				
				builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						DatabaseHelper helper = new DatabaseHelper(HomeActivity.this);
						SQLiteDatabase db= helper.getWritableDatabase();
						String query="delete from student where student_id="+s.getId();
						db.execSQL(query);
						db.close();
						
						
					}
				});
				builder.setPositiveButton("Update", null);
				builder.setNeutralButton("Cancel", null);
				builder.setIcon(R.drawable.ic_launcher);
				
				AlertDialog dialog = builder.create();
				dialog.show();
				
			}
		});
		
		
		
		
		/* open Db,read Student record and display in the List View

		DatabaseHelper helper = new DatabaseHelper(HomeActivity.this);

		listStudent = helper.getStudents();*/
		/*
		 * SQLiteDatabase db = helper.getWritableDatabase();
		 * 
		 * String query ="select * from student"; Cursor cur =
		 * db.rawQuery(query, null);
		 * 
		 * //get one row at a time from cursor while(cur.moveToNext() == true) {
		 * int id = cur.getInt(0); String name = cur.getString(1); int
		 * courseID=cur.getInt(2); String email = cur.getString(3);
		 * 
		 * // String data = id + "," +name + "," + courseID + "," + email;
		 * Student s = new Student(id, name, courseID, ema il);
		 * 
		 * listStudent.add(s); } db.close();
		 */

		// create and set adapter
		/*adapter = new ArrayAdapter<Student>(HomeActivity.this,
				android.R.layout.simple_list_item_1, listStudent);
		listViewStudent.setAdapter(adapter);*/
	}// eof oncreate

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		DatabaseHelper helper = new DatabaseHelper(HomeActivity.this);

		listStudent = helper.getStudents();
		adapter = new ArrayAdapter<Student>(HomeActivity.this,
				android.R.layout.simple_list_item_1, listStudent);
		listViewStudent.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		SharedPreferences sp = getSharedPreferences("userdata", MODE_PRIVATE);
		switch (item.getItemId()) {
		case R.id.studentNew: {
			Intent in = new Intent(HomeActivity.this, NewStudentActivity.class);
			startActivity(in);
		}
			break;
		case R.id.exitStudent:
			finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
