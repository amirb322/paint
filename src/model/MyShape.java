package model;

import java.awt.Point;

public abstract class MyShape  {

	String type;
	Point startPoint;
	Point endPoint;
	
	

	public String getType() {
		return type;
	}

//	MyShape() {
//		
//		setType(T);
//	}

	public void setType(String type) {
		this.type = type;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	

//	@Override
//	public String toString() {
//		return super.toString();
//	}
//	@Override
//	public boolean equals(Object arg0) {
//		// TODO Auto-generated method stub
//		return super.equals(arg0);
//	}
//	
	

}
