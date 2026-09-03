package com.PrepareStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentManagement {
	
	//*********** Dynamic Query ****************
	
	 static Scanner sc=new Scanner(System.in);
	 
	 public static Connection dbConnect() throws ClassNotFoundException, SQLException {
		 
		 Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","12345");
			
			return connection;
		 
	 }
	 
		// ****** ADD STUDENT ******
	 public static void addStudent() throws ClassNotFoundException, SQLException{
		 
		 PreparedStatement prepareStatement = dbConnect().
				 prepareStatement("insert into student values (?,?,?,?)"); // dynamic Query
		 System.out.println("Enter Roll no: ");
		 prepareStatement.setInt(1, sc.nextInt());
		 System.out.println("Enter Name: ");
		 prepareStatement.setString(2, sc.next());
		 System.out.println("Enter Marks: ");
		 prepareStatement.setDouble(3, sc.nextDouble());
		 System.out.println("Enter age: ");
		 prepareStatement.setInt(4, sc.nextInt());
		 
		 int result = prepareStatement.executeUpdate();
		 System.out.println("Qury Ok "+result+"rows affected");
		 
	 }
	 
	// ****** DELETE STUDENT ******
	 public static void removeStudent() throws ClassNotFoundException, SQLException {
			 
		 Connection con = dbConnect(); 
		 	System.out.println("Enter Roll no of Student to Remove: "); 
		 	int rollNo = sc.nextInt(); 
			PreparedStatement prepareStatement = con.prepareStatement( "DELETE FROM student WHERE id=?" ); 
			prepareStatement.setInt(1, rollNo); 
			int result = prepareStatement.executeUpdate(); 
			 
			if (result > 0) { 
				System.out.println("Student Deleted Successfully!"); 
			} 
			 else 
			{ 
				System.out.println("Student Not Found!"); 
			} 
			 prepareStatement.close(); 
			 con.close(); 
			 
		 }
	 

	// ************** UPDATE STUDENT **************
	public static void updateStudent() throws ClassNotFoundException, SQLException {

		Connection con = dbConnect();

		System.out.println("Enter Roll no of Student to Update: ");
		int rollNo = sc.nextInt();

		System.out.println("Enter New Name: ");
		String name = sc.next();

		System.out.println("Enter New Marks: ");
		double marks = sc.nextDouble();

		System.out.println("Enter New Age: ");
		int age = sc.nextInt();

		PreparedStatement prepareStatement =
				con.prepareStatement(
						"UPDATE student SET name=?, marks=?, age=? WHERE id=?"
				);

		prepareStatement.setString(1, name);
		prepareStatement.setDouble(2, marks);
		prepareStatement.setInt(3, age);
		prepareStatement.setInt(4, rollNo);

		int result = prepareStatement.executeUpdate();

		if (result > 0) {
			System.out.println("Student Updated Successfully!");
		} else {
			System.out.println("Student Not Found!");
		}

		prepareStatement.close();
		con.close();
	}


// **************** DISPLAY ALL STUDENTS ****************
public static void displayallStudent() throws ClassNotFoundException, SQLException {

	PreparedStatement statement =
			dbConnect().prepareStatement("select * from student");

	ResultSet set = statement.executeQuery();

	System.out.println("========== Student Details ============");
	System.out.println("---------------------------------------------");

	while(set.next()) {
		System.out.println("|"+set.getInt(1)+"|"+set.getString(2)+"|"+set.getDouble(3)+"|"+set.getInt(4));
		System.out.println("-------------------------------");
	}

	statement.close();
}

public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
	boolean flag=true;
	while(flag) {
		System.out.println("------------ Student Management System------------");
		System.out.println("1.Add Student");
		System.out.println("2.Update Student");
		System.out.println("3.remove student");
		System.out.println("4.Display students");
		System.out.println("5.Exit..");
		System.out.println("Enter your choice: ");
		int ch=sc.nextInt();
		switch(ch) {
		case 1-> addStudent();
		case 2-> updateStudent();
		case 3->removeStudent();
		case 4-> displayallStudent();
		case 5 -> { 
			System.out.println("Thank You! Exiting..."); 
			flag = false; 
			} 
		
		default -> System.out.println("Invalid Choice! Please try again.");
		}
	}
	
}

}  // ✅ StudentManagement class close