package gr.aueb.cf.teachersstudentsapp.service;

import java.sql.SQLException;
import java.util.List;

import gr.aueb.cf.teachersstudentsapp.dao.ITeacherDAO;
import gr.aueb.cf.teachersstudentsapp.dto.TeacherDTO;
import gr.aueb.cf.teachersstudentsapp.model.Teacher;
import gr.aueb.cf.teachersstudentsapp.service.exceptions.TeacherIdAlreadyExistsException;
import gr.aueb.cf.teachersstudentsapp.service.exceptions.TeacherNotFoundException;

public class TeacherServiceImp implements ITeacherService {
	
	private final ITeacherDAO teacherDAO;
	
	public TeacherServiceImpl(ITeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	@Override
	public void insertTeacher(TeacherDTO teacherDTO) throws TeacherIdAlreadyExistsException, SQLException {
		Teacher teacher = new Teacher();
		teacher.setSname(teacherDTO.getSname());
		teacher.setFname(teacherDTO.getFname());
		
		try {
			teacherDAO.insert(teacher);
		}catch (SQLException e) {
			throw e;
		}	
	}

	@Override
	public void deleteTeacher(TeacherDTO teacherDTO) throws TeacherNotFoundException, SQLException {
		Teacher teacherToDelete = new Teacher();
		
		teacherToDelete.setId(teacherDTO.getId());
		
		try {
			if (teacherDAO.getTeacherById(teacherToDelete.getId()) != null) {
				teacherDAO.delete(teacherToDelete);
			}else {
				throw new TeacherNotFoundException(teacherToDelete);
			}
		}catch(TeacherNotFoundException | SQLException e) {
			throw e;
		}
	}

	@Override
	public void updateTeacher(TeacherDTO oldTeacherDTO, TeacherDTO newTeacherDTO)
			throws TeacherNotFoundException, SQLException {
		
		// extract DTO
		Teacher teacherToUpdate = new Teacher();
		teacherToUpdate.setId(oldTeacherDTO.getId());
		
		Teacher newTeacher = new Teacher();
		newTeacher.setSname(newTeacherDTO.getSname());
		newTeacher.setSname(newTeacherDTO.getFname());
		
		// Forward to DAO
		try {
			if (teacherDAO.getTeacherById(teacherToUpdate.getId()) != null) {
				teacherDAO.update(teacherToUpdate, newTeacher);
			}else {
				throw new TeacherNotFoundException(teacherToUpdate);
			}
		}catch(TeacherNotFoundException | SQLException e) {
			throw e;
		}
	}

	@Override
	public List<Teacher> getTeacherByLastname(String lastname) throws SQLException {
		try {
			return teacherDAO.getTeachersByLastname(lastname);
		} catch (SQLException e) {
			throw e;
		}
	}
}
