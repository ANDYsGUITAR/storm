package com.log.utils;



import org.apache.sqoop.client.SqoopClient;
import org.apache.sqoop.model.MDriverConfig;
import org.apache.sqoop.model.MFromConfig;
import org.apache.sqoop.model.MJob;
import org.apache.sqoop.model.MLink;
import org.apache.sqoop.model.MLinkConfig;
import org.apache.sqoop.model.MSubmission;
import org.apache.sqoop.model.MToConfig;
import org.apache.sqoop.submission.counter.Counter;
import org.apache.sqoop.submission.counter.CounterGroup;
import org.apache.sqoop.submission.counter.Counters;
import org.apache.sqoop.validation.Status;


public class Transfer 
{
	public static String url = "http://localhost:12000/sqoop/";
	public static SqoopClient client = new SqoopClient(url);
    public static void main( String[] args )
    {
    	HDFStoMySQL();
    	Clear();

    }
    private static void HDFStoMySQL() {
    	CreateLink();
    	CreateJob();
    	JobStart();
		
	}
	private static void Clear() {
    	if(client.getJob("Transfer")!=null) {
    		client.deleteJob("Transfer");
    		System.out.println("Job Transfer is deleted.");
    	}
    	if(client.getLink("mysql_link")!=null) {
    		client.deleteLink("mysql_link");
    		System.out.println("link mysql_link is deleted.");
    	}
    	if(client.getLink("hdfs_link")!=null) {
    		client.deleteLink("hdfs_link");
    		System.out.println("link hdfs_link is deleted.");
    	}
    	
	}
	private static void JobStart() 
    {
    	MSubmission submission = client.startJob("Transfer");
    	System.out.println("Job Submission Status : " + submission.getStatus());
    	if(submission.getStatus().isRunning() && submission.getProgress() != -1) {
    	  System.out.println("Progress : " + String.format("%.2f %%", submission.getProgress() * 100));
    	}
    	System.out.println("Hadoop job id :" + submission.getExternalJobId());
    	System.out.println("Job link : " + submission.getExternalLink());
    	Counters counters = submission.getCounters();
    	if(counters != null) {
    	  System.out.println("Counters:");
    	  for(CounterGroup group : counters) {
    	    System.out.print("\t");
    	    System.out.println(group.getName());
    	    for(Counter counter : group) {
    	      System.out.print("\t\t");
    	      System.out.print(counter.getName());
    	      System.out.print(": ");
    	      System.out.println(counter.getValue());
    	    }
    	  }
    	}
		
	}
	public static void CreateLink() 
    {

    	// create a placeholder for link
    	// 
    	MLink MySQL_link = client.createLink("generic-jdbc-connector");
    	MySQL_link.setName("mysql_link");
    	MySQL_link.setCreationUser("Andy");
    	MLinkConfig MySQL_linkConfig = MySQL_link.getConnectorLinkConfig();
    	// fill in the link config values
    	MySQL_linkConfig.getStringInput("linkConfig.connectionString").setValue("jdbc:mysql://localhost:3306/Log");
    	MySQL_linkConfig.getStringInput("linkConfig.jdbcDriver").setValue("com.mysql.jdbc.Driver");
    	MySQL_linkConfig.getStringInput("linkConfig.username").setValue("root");
    	MySQL_linkConfig.getStringInput("linkConfig.password").setValue("wasd");
    	MySQL_linkConfig.getStringInput("dialect.identifierEnclose").setValue(" ");
    	// save the link object that was filled
    	Status MySQL_status = client.saveLink(MySQL_link);
    	if(MySQL_status.canProceed()) {
    	 System.out.println("Created Link with Link Name : " + MySQL_link.getName());
    	} else {
    	 System.out.println("Something went wrong creating the link");
    	}
    	//**************************************************
    	MLink HDFS_link = client.createLink("hdfs-connector");
    	HDFS_link.setName("hdfs_link");
    	HDFS_link.setCreationUser("Andy");
    	MLinkConfig HDFS_linkConfig = HDFS_link.getConnectorLinkConfig();
    	HDFS_linkConfig.getStringInput("linkConfig.uri").setValue("hdfs://localhost:9000/");
    	Status HDFS_status = client.saveLink(HDFS_link);
    	if(HDFS_status.canProceed()) {
       	 System.out.println("Created Link with Link Name : " + HDFS_link.getName());
       	} else {
       	 System.out.println("Something went wrong creating the link");
       	}
    }
    public static void CreateJob() 
    {

    	MJob job = client.createJob("hdfs_link", "mysql_link");
    	job.setName("Transfer");
    	job.setCreationUser("Andy");
    	// set the "FROM" link job config values
    	MFromConfig fromJobConfig = job.getFromJobConfig();
    	fromJobConfig.getStringInput("fromJobConfig.inputDirectory").setValue("/user/hive/warehouse/toplesson2017/");
    	//fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("id");
    	// set the "TO" link job config values
    	MToConfig toJobConfig = job.getToJobConfig();
    	toJobConfig.getStringInput("toJobConfig.schemaName").setValue("Log");
    	toJobConfig.getStringInput("toJobConfig.tableName").setValue("topLesson");
    	//toJobConfig.getStringInput("toJobConfig.outputDirectory").setValue("/usr/tmp");
    	// set the driver config values
    	//MDriverConfig driverConfig = job.getDriverConfig();
    	//driverConfig.getStringInput("throttlingConfig.numExtractors").setValue("3");

    	Status status = client.saveJob(job);
    	if(status.canProceed()) {
    	 System.out.println("Created Job with Job Name: "+ job.getName());
    	} else {
    	 System.out.println("Something went wrong creating the job");
    	}
    }
}

