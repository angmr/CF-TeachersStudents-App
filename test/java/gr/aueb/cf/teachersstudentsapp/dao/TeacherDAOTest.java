package gr.aueb.cf.teachersstudentsapp.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.aueb.cf.teachersstudentsapp.dao.dbutil.DBHelper;
import gr.aueb.cf.teachersstudentsapp.model.Teacher;

class TeacherDAOTest {
	
	private static ITeacherDAO teacherDAO;
	
	@BeforeAll
	public static void setupClass() {
		teacherDAO = new TeacherDAOImpl();
	}
	
	@BeforeEach
	public void setup() throws SQLException {
		createDummyTeachers();
	}
	
	@AfterAll
	public static void tearDown() throws SQLException {
		DBHelper.eraseData();
	}
	
	@Test
	public void persistAndGetTeacher() throws SQLException {
		Teacher t = new Teacher();
		t.setFname("Alice");
		t.setSname("Smith");
		teacherDAO.insert(t);
		
		List<Teacher> teachers = teacherDAO.getTeachersByLastname("Smi");
		assertTrue(teachers.size() == 1);
	}
	
	@Test 
	public void deleteTeacher() throws SQLException {
		Teacher t = new Teacher();
		t.setId(1);
		teacherDAO.delete(t);
		
		List<Teacher> teachers = teacherDAO.getTeachersByLastname("Titu");
		assertNull(teachers);
	}
	
	@Test
	public void getTeacherByLastName() throws SQLException {
		List<Teacher> teachers = teacherDAO.getTeachersByLastname("Streigh");
		assertNotNull(teachers);
	}
	
	@Test
	public void updateTeacher() throws SQLException {
		Teacher t = new Teacher();
		t.setId(2);
		
		Teacher t2 = new Teacher();
		t2.setFname("Bod2");
		t2.setSname("Streigh2");
		
		teacherDAO.update(t,  t2);
		
		List<Teacher> teachers = teacherDAO.getTeachersByLastname("Streigh2");
		assertEquals(teachers.get(0).getSname(), "Streigh2");
	}
	
	
	public static void createDummyTeachers() throws SQLException {
		Teacher t = new Teacher();
		t.setFname("Anna");
		t.setSname("Titu");
		teacherDAO.insert(t);
		
		Teacher t1 = new Teacher();
		t1.setFname("Bod");
		t1.setSname("Streigh");
		teacherDAO.insert(t1);
	}
}
