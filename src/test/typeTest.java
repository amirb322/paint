package test;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Test;
import model.MyCircle;
import model.MyLine;
import model.MyShape;

public class typeTest {

	

	@Test
	public void isEqual2Lines() {
		MyShape line1 = new MyLine(new Point(0, 5), new Point(2, 6));
		MyShape line2 = new MyLine(new Point(0, 5), new Point(2, 6));
		assertTrue(line1.equals(line2));
	}

	@Test
	public void isEqual2LineswhitDefrentPoint() {
		MyShape line1 = new MyLine(new Point(0, 5), new Point(3, 6));
		MyShape line2 = new MyLine(new Point(0, 5), new Point(2, 6));
		assertTrue(!line1.equals(line2));
	}

	@Test
	public void isEqual2LinesNullPoint() {
		try {

			MyShape line1 = new MyLine(null, null);
			MyShape line2 = new MyLine(null, null);
			assertTrue(line1.equals(line2));
		} catch (Exception e) {
			if (e instanceof NullPointerException)
				assertTrue(true);
		}
	}

	@Test
	public void isEqual2LineAndCircle() {
		MyShape line1 = new MyLine(new Point(0, 5), new Point(2, 6));
		MyShape line2 = new MyCircle(new Point(0, 5), new Point(2, 6));
		assertTrue(!line1.equals(line2));
	}

}
