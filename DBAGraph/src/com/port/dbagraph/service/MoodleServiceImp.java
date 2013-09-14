package com.port.dbagraph.service;

import com.port.dbagraph.dao.MoodleDAO;
import com.port.dbagraph.model.*;


public class MoodleServiceImp implements MoodleService{
	public MoodleDAO moodleDAO;
	
	//sql methods to run, has to be here, in the service, in the DAO, in the mapper


	public MoodleDAO getMoodleDAO() {
		return moodleDAO;
	}

	public void setMoodleDAO(MoodleDAO moodleDAO) {		
		this.moodleDAO = moodleDAO;
	}

	public SQLResult moodleUsers() {
		return this.moodleDAO.moodleUsers();
	}

	
}
