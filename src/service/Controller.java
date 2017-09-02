package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import dao.PersonDao;
import dao.ShapeDao;
import model.MyShape;
import model.PainterPerson;

public class Controller {

	static PersonDao dao = new PersonDao();
	static ShapeDao shDao = new ShapeDao();

	private static ArrayList<PainterPerson> fetchPainters() {
		return dao.getAllPerson();
	}

	public static boolean checkPass(String string, String string2) {
		if ((new String(string)).equals(new String(string2))) {
			System.out.println("in check: ");
			return true;
		}
		return false;
	}

	public static PainterPerson checkUser(String userName) {

		for (PainterPerson painter : fetchPainters()) {
			if (userName.equals(painter.getUserName()))
				return painter;
		}
		return null;
	}

	public static void create(PainterPerson person) {
		dao.create(person);
	}

	public static void create(MyShape sh, int ownerid) {
		if (!isInShapes(sh, ownerid))
			shDao.create(sh, ownerid);
	}

	public static void clear(int ownerId) {
		shDao.delete(ownerId);

	}

	public static ArrayList<MyShape> load(int ownerid) {
		return shDao.getAllShapes(ownerid);
	}

	private static boolean isInShapes(MyShape sh, int ownerid) {
		for (MyShape shape : load(ownerid)) {
			if (shape.equals(sh))
				return true;
		}
		return false;
	}

}
