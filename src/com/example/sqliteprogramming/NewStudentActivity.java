package com.example.sqliteprogramming;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewStudentActivity extends Activity {
	EditText editName, editEmail;
	Spinner spinnerCourse;
	Button buttonSubmit;
	ArrayList<Course> listCourses;
	ArrayAdapter<Course> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student);
		editName = (EditText) findViewById(R.id.editText1);
		editEmail = (EditText) findViewById(R.id.editText2);
		buttonSubmit = (Button) findViewById(R.id.button1);
		spinnerCourse = (Spinner) findViewById(R.id.spinner1);

		// show Courses in Spinner
		DatabaseHelper helper = new DatabaseHelper(NewStudentActivity.this);
		listCourses = helper.getCourses();

		adapter = new ArrayAdapter<Course>(NewStudentActivity.this,
				android.R.layout.simple_spinner_item, listCourses);
		spinnerCourse.setAdapter(adapter);

		// submit button listener
		buttonSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = editName.getText().toString();
				String email = editEmail.getText().toString();

				Course c = (Course) spinnerCourse.getSelectedItem();

				Student s = new Student();
				s.setName(name);
				s.setEmail(email);
				s.setCourseID(c.getId());

				// save student info using DatabaseHelper
				DatabaseHelper helper = new DatabaseHelper(
						NewStudentActivity.this);
				try {
					helper.insertStudent(s);
					Toast.makeText(NewStudentActivity.this, "sucess",
							Toast.LENGTH_LONG).show();
				} catch (Exception ex) {
					Log.e("error", ex.toString());
				}
			}
		});

	}

}
