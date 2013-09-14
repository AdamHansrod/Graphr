package com.port.dbagraph.action;

import java.net.URL;

import com.opensymphony.xwork2.ActionSupport;
import com.port.dbagraph.dao.MoodleDAO;
import com.port.dbagraph.dao.StudentRecordsDAO;
import com.port.dbagraph.rrd.RRD;
import com.port.dbagraph.service.MoodleService;
import com.port.dbagraph.service.StudentRecordsService;

public class Update extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MoodleService moodleService;
	private MoodleDAO moodleDAO;
	private StudentRecordsService studentRecordsService;
	private StudentRecordsDAO studentRecordsDAO;

	public String execute() {
		//Create or update the rrd graphs
		RRD rrdGraphMaker = new RRD();
		rrdGraphMaker.setMoodleDAO(moodleDAO);
		rrdGraphMaker.setMoodleService(moodleService);
		rrdGraphMaker.setStudentRecordsDAO(studentRecordsDAO);
		rrdGraphMaker.setStudentRecordsService(studentRecordsService);
		rrdGraphMaker.moodle();
		rrdGraphMaker.srEvent();
		return SUCCESS;
	}
	public MoodleService getMoodleService() {
		return moodleService;
	}
	public void setMoodleService(MoodleService moodleService) {
		this.moodleService = moodleService;
	}
	public MoodleDAO getMoodleDAO() {
		return moodleDAO;
	}
	public void setMoodleDAO(MoodleDAO moodleDAO) {
		this.moodleDAO = moodleDAO;
	}
	public StudentRecordsService getStudentRecordsService() {
		return studentRecordsService;
	}
	public void setStudentRecordsService(StudentRecordsService studentRecordsService) {
		this.studentRecordsService = studentRecordsService;
	}
	public StudentRecordsDAO getStudentRecordsDAO() {
		return studentRecordsDAO;
	}
	public void setStudentRecordsDAO(StudentRecordsDAO studentRecordsDAO) {
		this.studentRecordsDAO = studentRecordsDAO;
	}	
}
