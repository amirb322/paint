package model;

import java.awt.Point;

public class MyLine extends MyShape {

	public MyLine(Point startPoint, Point endPoint) {
		this.setType("line");
		this.endPoint = endPoint;
		this.startPoint = startPoint;
	}

	@Override
	public boolean equals(Object shape) {
		
		if (((MyShape)shape).getType().equalsIgnoreCase("line")) {
			MyLine shap = (MyLine) shape;
			if (this.startPoint.equals(shap.startPoint) && this.endPoint.equals(shap.endPoint))
				return true;
		}
		return false;
	}

	// @Override
	// public String toString() {
	// return this.getType().toString();
	// }

}
