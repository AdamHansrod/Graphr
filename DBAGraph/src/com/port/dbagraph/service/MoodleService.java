package com.port.dbagraph.service;

import com.port.dbagraph.dao.MoodleDAO;
import com.port.dbagraph.model.*;

public interface MoodleService {
	public void setMoodleDAO(MoodleDAO moodleDAO);

	public MoodleDAO getMoodleDAO();
	
	public SQLResult moodleUsers();

}
