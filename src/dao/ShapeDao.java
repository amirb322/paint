package dao;

import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.MyCircle;
import model.MyLine;
import model.MyRectangle;
import model.MyShape;

public class ShapeDao {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/mydb";

	static final String USER = "root";
	static final String PASS = "";
	Statement stmt = null;

	public void create(MyShape shap, int ownerId) {
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			stmt.executeUpdate(
					"insert into sapes ( type , start_point_X, start_point_Y, end_point_X, end_point_Y, owner_id) values('"
							+ shap.getType() + "', " + shap.getStartPoint().getX() + ", " + shap.getStartPoint().getY()
							+ ", " + +shap.getEndPoint().getX() + ", " + shap.getEndPoint().getY()+ ", "+ownerId + ")");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private ArrayList<MyShape> convertToShapeList(ResultSet rs) throws SQLException {
		ArrayList<MyShape> shapes = new ArrayList<>();
		while (rs.next()) {
//			MyShape shape = new MyShape();
//			shape.setType(rs.getString("type"));
			Point p1=new Point(Integer.parseInt(rs.getString("start_point_X")),
					Integer.parseInt(rs.getString("start_point_Y")));
			Point p2=new Point(Integer.parseInt(rs.getString("end_point_X")),
					Integer.parseInt(rs.getString("end_point_Y")));

			switch (rs.getString("type")) {
			case "line":
				MyLine line = new MyLine(p1, p2);
				shapes.add(line);
				break;

			case "circle":
				MyCircle circle = new MyCircle(p1, p2);
				shapes.add(circle);
				break;
			case "rectangle":
				MyRectangle rectangle = new MyRectangle(p1, p2);
				shapes.add(rectangle);
				break;
			default:
				break;
			}
		}
		return shapes;
	}

	public void delete(MyShape shape, int ownerId) {
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM sapes WHERE owner_id=" + ownerId + " AND start_point_X="
					+ shape.getStartPoint().getX() + " AND start_point_Y=" + shape.getStartPoint().getY()
					+ " AND end_point_X=" + shape.getEndPoint().getX() + " AND end_point_Y="
					+ shape.getStartPoint().getY() + " AND type='" + shape.getType() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int ownerId) {
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM sapes WHERE owner_id=" + ownerId );
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MyShape> getAllShapes(int ownerId) {
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery("select * from sapes s where s.owner_id="+ownerId);
			return convertToShapeList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

}
