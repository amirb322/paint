package model;

import java.awt.Point;

public class MyCircle extends MyShape {

	public MyCircle( Point startPoint, Point endPoint) {
		this.setType("circle");
		this.endPoint = endPoint;
		this.startPoint = startPoint;
	}
	
	public static int getReduce(int x1, int y1, int x2, int y2) {
		//TODO calculate reduce here
		return 0;
	}
	public static Point getCenter(int x1, int y1, int x2, int y2) {
		//TODO calculate cordinate of center 
		return null;
	}

	@Override
	public boolean equals(Object shape) {
		
		if (((MyShape)shape).getType().equalsIgnoreCase("circle")) {
			MyCircle shap = (MyCircle) shape;
			if (this.startPoint.equals(shap.startPoint) && this.endPoint.equals(shap.endPoint))
				return true;
		}
		return false;
	}

}
