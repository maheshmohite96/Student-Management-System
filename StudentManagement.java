package com.PrepareStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentManagement {

	//*********** Dynamic Query ****************

	static Scanner sc = new Scanner(System.in);

	public static Connection dbConnect() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "12345");

		return connection;

	}

	// ****** ADD STUDENT ******
	public static void addStudent() throws ClassNotFoundException, SQLException {

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
		System.out.println("Qury Ok " + result + "rows affected");

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

}
