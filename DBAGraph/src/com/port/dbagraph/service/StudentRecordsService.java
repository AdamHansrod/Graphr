package com.port.dbagraph.service;

import com.port.dbagraph.dao.StudentRecordsDAO;
import com.port.dbagraph.model.*;

public interface StudentRecordsService {
	public StudentRecordsDAO getStudentRecordsDAO();

	public void setStudentRecordsDAO(StudentRecordsDAO studentRecordsDAO);
	
	public SQLResult eventCount();

}
