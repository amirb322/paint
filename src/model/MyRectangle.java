package model;

import java.awt.Point;

public class MyRectangle extends MyShape {

	public MyRectangle( Point stP, Point endP) {
		this.setType("rectangle");
		this.endPoint = endP;
		this.startPoint = stP;
	}
	
	public static int[] getRealStartAndEndPoint(int x1, int y1, int x2, int y2) {
		//TODO calculate start and end point of rectangle's diagonal 
		return null;
	}

	@Override
	public boolean equals(Object shape) {
		
		if (((MyShape)shape).getType().equalsIgnoreCase("rectangle")) {
			MyRectangle shap = (MyRectangle) shape;
			if (this.startPoint.equals(shap.startPoint) && this.endPoint.equals(shap.endPoint))
				return true;
		}
		return false;
	}
}
