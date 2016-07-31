package com.example.sqliteprogramming;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static final String DBNAME = "cdac.sqlite";
	public static final int VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// write queries to create tables
		String table1Query = "create table course "
				+ " (course_id integer,course_title text, "
				+ " duration integer)";
		db.execSQL(table1Query);
		// insert queries
		db.execSQL("insert into course values(1,'DMC',6 )");
		db.execSQL("insert into course values(2,'DAC',6 )");

		// table 2 Student
		String table2Query = "create table student "
				+ " (student_id integer primary key "
				+ " autoincrement,name text,"
				+ " course_id integer,email text)";

		db.execSQL(table2Query);
		db.execSQL("insert into student " + " (name,course_id,email)"
				+ "values('Ankith',1,'ankith@gmail.com')");
		db.execSQL("insert into student" + "(name,course_id,email)"
				+ "values('Gaurav',2,'Gaurav@gmail.com')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public ArrayList<Student> getStudents() {
		ArrayList<Student> listStudents = new ArrayList<Student>();
		
		SQLiteDatabase db = getWritableDatabase();

		String query = "select * from student";
		Cursor cur = db.rawQuery(query, null);

		// get one row at a time from cursor
		while (cur.moveToNext() == true) {
			int id = cur.getInt(0);
			String name = cur.getString(1);
			int courseID = cur.getInt(2);
			String email = cur.getString(3);

			// String data = id + "," +name + "," + courseID + "," + email;
			Student s = new Student(id, name, courseID, email);

			listStudents.add(s);
		}
		db.close();
		return listStudents;
	}
	
	public ArrayList<Course> getCourses()
	{
		ArrayList<Course> listCourses = new ArrayList<Course>();
		SQLiteDatabase db = getWritableDatabase();
		String query = "select * from course";
		
		Cursor cur = db.rawQuery(query, null);
		while(cur.moveToNext() == true){
			int id = cur.getInt(0);
			String title = cur.getString(1);
			int duration = cur.getInt(2);
			Course c = new Course(id, title, duration);
			listCourses.add(c);
		}
		db.close();
		return listCourses;
		
	}

	public void insertStudent(Student s) {
		
		String query = "insert into Student(name,email,course_id)" + "values('"+s.getName() + "','"+ s.getEmail() +"',"+s.getCourseID()+")";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(query);
		db.close();
		
		
		
		
	}

	/*public void deleteStudent(Student s) {
		// TODO Auto-generated method stub
		String query = "delete Student where id ='"+s.getCourseID()+"' ";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(query);
		db.close();
		
	}
*/
}//eof databasehelper
