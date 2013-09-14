package com.port.dbagraph.service;

import com.port.dbagraph.dao.StudentRecordsDAO;
import com.port.dbagraph.model.*;


public class StudentRecordsServiceImp implements StudentRecordsService{
	public StudentRecordsDAO studentRecordsDAO;
	
	//sql methods to run, has to be here, in the service, in the DAO, in the mapper
	public StudentRecordsDAO getStudentRecordsDAO() {
		return studentRecordsDAO;
	}

	public void setStudentRecordsDAO(StudentRecordsDAO studentRecordsDAO) {
		this.studentRecordsDAO = studentRecordsDAO;
	}
	
	public SQLResult eventCount(){
		return this.studentRecordsDAO.eventCount();
	}
	
}
