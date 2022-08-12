package gr.aueb.cf.teachersstudentsapp.service;

import java.sql.SQLException;
import java.util.List;

import gr.aueb.cf.teachersstudentsapp.dto.TeacherDTO;
import gr.aueb.cf.teachersstudentsapp.service.exceptions.TeacherIdAlreadyExistsException;
import gr.aueb.cf.teachersstudentsapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.teachersstudentsapp.model.Teacher;

public interface ITeacherService {
	
	/**
	 * Insert a {@link Teacher} based on the data carried by the {@link TeacherDTO}.
	 * @param teacherDTO 
	 * 			DTO object that contains the data
	 * @throws TeacherIdAlreadyExistsException
	 * 			if any teacher identified by their id, needed to be inserted has already been inserted.
	 * @throws SQLException
	 * 			if any error happens related to SQL queries.
	 */
	void insertTeacher(TeacherDTO teacherDTO) 
			throws TeacherIdAlreadyExistsException, SQLException;
	
	/**
	 * Removes a {@link Teacher} based on the data carried by the {@link TeacherDTO}.
	 * @param teacherDTO
	 * 			DTO object that contains the data (id)
	 * @throws TeacherNotFoundException
	 * 			if any teacher identified by their id, needed to be deleted can't be found.
	 * @throws SQLException
	 */
	void deleteTeacher(TeacherDTO teacherDTO)
			throws TeacherNotFoundException, SQLException;
	
	/**
	 * Updates a {@link Teacher} based on the data carried by the {@link TeacherDTO}.
	 * @param oldTeacherDTO
	 * 				DTO object that contains the data (id) of the teacher that will be updated.
	 * @param newTeacherDTO
	 * 				DTO object that contains the data of the new teacher.
	 * @throws TeacherNotFoundException
	 * 				if any teacher identified by their id, needed to be updated not found.
	 * @throws SQLException
	 * 				if any error happens during SQL update.
	 */
	void updateTeacher(TeacherDTO oldTeacherDTO, TeacherDTO newTeacherDTO)
			throws TeacherNotFoundException, SQLException;
	
	/**
	 * Returns to the caller a list of the {@link Teacher} objects identified by
	 * their last name or the initial characters of that last name 
	 * 
	 * @param lastname
	 * 		a string object that contains the last name or the initial letters of the last name.
	 * @return
	 * 		a list that contains the search results or null if no results are found
	 * @throws SQLException
	 * 		if any error occurs during SQL search
	 */
	List<Teacher> getTeacherByLastname(String lastname) throws SQLException;
}
