Graphr
======

Originally designed for the University of Portsmouth Application Development and Maintenance team to support the DBA's by producing graphs every 5 minutes of various queryable stastics from a range of databases that were then displayed either in browser or on digital signage.

The project is composed of Java, Struts2, Spring and Mybatis as well as the RRD4j library which provides the graphing capabilities.

It creates rrd files at a preset location on a tomcat web server and then serves the dynamically updated images upon request through a servlet, example code is provided for the current two different types of databases to connect to, as well as then create rrd databases and graphs.




Dependancies
======

The project needs:

  Struts2

  Mybatis

  Spring

  ojdbc6.jar or ojbdc14.jar

  Java EE6 SDK
