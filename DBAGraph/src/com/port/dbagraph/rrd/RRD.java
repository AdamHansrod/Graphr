package com.port.dbagraph.rrd;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.rrd4j.ConsolFun;
import org.rrd4j.DsType;
import org.rrd4j.core.RrdDb;
import org.rrd4j.core.RrdDef;
import org.rrd4j.core.Sample;
import org.rrd4j.graph.RrdGraph;
import org.rrd4j.graph.RrdGraphDef;

import com.opensymphony.xwork2.ActionSupport;
import com.port.dbagraph.dao.MoodleDAO;
import com.port.dbagraph.dao.StudentRecordsDAO;
import com.port.dbagraph.service.MoodleService;
import com.port.dbagraph.service.StudentRecordsService;

public class RRD extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MoodleService moodleService;
	private MoodleDAO moodleDAO;
	private StudentRecordsService studentRecordsService;
	private StudentRecordsDAO studentRecordsDAO;
	private static final String moodlerrdDBLocation = "//opt//tomcat7//data//moodle.rrd";
	private static final String moodleGraphLocation = "//opt//tomcat7//data//moodle.gif";
	private static final String SRrrdDBLocation = "//opt//tomcat7//data//sr.rrd";
	private static final String SREventCountGraphLocation = "//opt//tomcat7//data//srEventCount.gif";

	// This method currently opens the db, adds the value from the query from
	// the db and then produces the graph
	public void moodle() {
		try {
			File file = new File(moodlerrdDBLocation);
			boolean exists = file.exists();
			if (!exists) {
				//create the database if it doesn't exist
				RrdDef rrdDef = new RrdDef(moodlerrdDBLocation);
				rrdDef.addDatasource("users", DsType.GAUGE, 600, Double.NaN,
						Double.NaN);
				rrdDef.addArchive(ConsolFun.TOTAL, 0.5, 1, 12);
				RrdDb rrdDb = new RrdDb(rrdDef);
				updateMoodleGraph(rrdDb);
			} else {
				RrdDb rrdDb = new RrdDb(moodlerrdDBLocation);
				updateMoodleGraph(rrdDb);
			}
			createMoodleGraph(moodleGraphLocation, moodlerrdDBLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void srEvent() {
		try {
			File file = new File(SRrrdDBLocation);
			boolean exists = file.exists();
			if (!exists) {
				//create the database if it doesn't exist
				RrdDef rrdDef = new RrdDef(SRrrdDBLocation);
				rrdDef.addDatasource("eventcount", DsType.GAUGE, 600, Double.NaN,
						Double.NaN);
				rrdDef.addArchive(ConsolFun.TOTAL, 0.5, 1, 12);
				RrdDb rrdDb = new RrdDb(rrdDef);
				updateSREventCountGraph(rrdDb);
			} else {
				RrdDb rrdDb = new RrdDb(SRrrdDBLocation);
				updateSREventCountGraph(rrdDb);
			}
			createSREventCountGraph(SREventCountGraphLocation, SRrrdDBLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private final void updateSREventCountGraph(RrdDb rrdDb){
		try {
			Sample sample = rrdDb.createSample();
			int j = studentRecordsService.eventCount().getEventCount();
			sample.setAndUpdate("NOW:" + j);
			rrdDb.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	private final void updateMoodleGraph(RrdDb rrdDb){
		try {
			Sample sample = rrdDb.createSample();
			int j = moodleService.moodleUsers().getMoodleUsersCount();
			sample.setAndUpdate("NOW:" + j);
			rrdDb.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	private static final void createMoodleGraph(String graphLocation,
			String rrdDBLocation) {
		try {
			RrdGraphDef graphDef = new RrdGraphDef();
			graphDef.setTimeSpan(-3600L, -0L);
			graphDef.datasource("lineb", rrdDBLocation, "users", ConsolFun.TOTAL);
			graphDef.line("lineb", Color.GREEN, "Number of Moodle Users", 3);
			graphDef.setFilename(graphLocation);
			RrdGraph graph = new RrdGraph(graphDef);//actually creates the file.
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	private static final void createSREventCountGraph(String graphLocation,
			String rrdDBLocation) {
		try {
			RrdGraphDef graphDef = new RrdGraphDef();
			graphDef.setTimeSpan(-3600L, -0L);
			graphDef.datasource("lineb", rrdDBLocation, "eventcount", ConsolFun.TOTAL);
			graphDef.line("lineb", Color.GREEN, "Event Count", 3);
			graphDef.setFilename(graphLocation);
			RrdGraph graph = new RrdGraph(graphDef);//actually creates the file.
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	private static final void createMoodleDiskWaitGraph(String graphLocation,
			String rrdDBLocation) {
		try {
			RrdGraphDef waitGraphDef = new RrdGraphDef();
			waitGraphDef.setTimeSpan(-3600L, -0L);
			waitGraphDef.datasource("linea", rrdDBLocation, "a", ConsolFun.TOTAL);
			waitGraphDef.datasource("lineb", rrdDBLocation, "b", ConsolFun.TOTAL);
			waitGraphDef.line("linea", Color.GREEN, "Average Wait", 3);
			waitGraphDef.line("lineb", Color.BLUE, "Service Wait", 3);
			waitGraphDef.setFilename(graphLocation);
			waitGraphDef.setMaxValue(10);
			RrdGraph waitGraph = new RrdGraph(waitGraphDef);//actually creates the graph for wait.
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	private static final void createMoodleDiskUtilisationGraph(String graphLocation,
			String rrdDBLocation) {
		try {
			RrdGraphDef utilisationGraphDef = new RrdGraphDef();
			utilisationGraphDef.setTimeSpan(-3600L, -0L);
			utilisationGraphDef.datasource("linea", rrdDBLocation, "a", ConsolFun.TOTAL);
			utilisationGraphDef.line("linea", Color.RED, "Utilisation", 3);
			utilisationGraphDef.setFilename(graphLocation);
			RrdGraph utilisationGraph = new RrdGraph(utilisationGraphDef);//actually creates the graph for utilisation.
		} catch (IOException e) {
			System.out.println(e);
		}
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
