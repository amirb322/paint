package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.PainterPerson;

public class PersonDao {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/mydb";

	static final String USER = "root";
	static final String PASS = "";

	Statement stmt = null;

	public void create(PainterPerson person) {
		int rows;
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			rows = stmt.executeUpdate("insert into personpainter ( user_name , passwd) values('"
					+ person.getUserName()+ "','" + person.getPasswd().toString()+"')");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	public void update(int id, PainterPerson person) {
//		int rows;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			stmt = conn.createStatement();
//			rows = stmt.executeUpdate("UPDATE person SET FIRST_NAME='" + person.getFirstName() + "', LAST_NAME='"
//					+ person.getLastName() + "', EMAIL='" + person.getEmail() + "', PHON_NUM='" + person.getPhonNum()
//					+ "' WHERE ID=" + id);
//			System.out.println(rows + " person(s) succesfully updated");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

//	public ArrayList<Person> get(int id) {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			stmt = conn.createStatement();
//			ResultSet rs = (ResultSet) stmt.executeQuery("select * from person s WHERE s.ID = " + id);
//
//			return convertToStdList(rs);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public ArrayList<Integer> get(Person pers) {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			stmt = conn.createStatement();
//			ResultSet rs = (ResultSet) stmt.executeQuery("select s.ID from person s WHERE s.FIRST_NAME ='"
//					+ pers.getFirstName() + "' AND s.LAST_NAME='" + pers.getLastName() + "' AND s.EMAIL='"
//					+ pers.getEmail() + "' AND s.phon_num='" + pers.getPhonNum() + "'");
//
//			return convertToIntList(rs);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//	private ArrayList<Integer> convertToIntList(ResultSet rs) {
//		ArrayList<Integer> ids = new ArrayList<>();
//		try {
//			while (rs.next()) {
//				
//				ids.add(Integer.parseInt(rs.getString("ID")));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ids;
//	}

	private ArrayList<PainterPerson> convertToStdList(ResultSet rs) throws SQLException {
		ArrayList<PainterPerson> persons = new ArrayList<>();
		while (rs.next()) {
			PainterPerson std = new PainterPerson(null, null);
			std.setUserName(rs.getString("user_name"));
			std.setPasswd(rs.getString("passWD"));
			std.setId(Integer.parseInt(rs.getString("id")));
			
			persons.add(std);
		}
		return persons;
	}

//	public void delete(int id) {
//		int rows;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			stmt = conn.createStatement();
//			rows = stmt.executeUpdate("DELETE FROM person WHERE ID=" + id);
//			System.out.println(rows + " row(s) deleted from person ");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	public ArrayList<PainterPerson> getAllPerson() {
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery("select * from personpainter");
			return convertToStdList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}



}
