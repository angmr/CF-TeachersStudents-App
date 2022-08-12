package gr.aueb.cf.teachersstudentsapp.service.exceptions;

import gr.aueb.cf.teachersstudentsapp.model.Teacher;

public class TeacherIdAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public TeacherIdAlreadyExistsException(Teacher teacher) {
		super("Teacher with id = " + teacher.getId() + " already exists");
	}
}
