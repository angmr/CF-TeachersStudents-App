package gr.aueb.cf.teachersstudentsapp.dao;

import java.sql.SQLException;
import java.util.List;

import gr.aueb.cf.teachersstudentsapp.model.Teacher;

public interface ITeacherDAO {
	
	void insert(Teacher teacher) throws SQLException;
	void delete(Teacher teacher) throws SQLException;
	void update(Teacher oldTeacher, Teacher newTeacher) throws SQLException;
	List<Teacher> getTeachersByLastname(String lastname) throws SQLException;
	Teacher getTeacherById(int id) throws SQLException;
}
