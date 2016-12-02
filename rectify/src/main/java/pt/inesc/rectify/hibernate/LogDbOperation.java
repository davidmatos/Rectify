package pt.inesc.rectify.hibernate;
// Generated Dec 1, 2016 10:02:07 PM by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LogDbOperation generated by hbm2java
 */
public class LogDbOperation implements java.io.Serializable {

	private Integer id;
	private Date ts;
	private String request;
	private Set<LogDbSubOperation> logDbSubOperations = new HashSet<LogDbSubOperation>(0);

	public LogDbOperation() {
	}

	public LogDbOperation(Date ts) {
		this.ts = ts;
	}

	public LogDbOperation(Date ts, String request, Set<LogDbSubOperation> logDbSubOperations) {
		this.ts = ts;
		this.request = request;
		this.logDbSubOperations = logDbSubOperations;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTs() {
		return this.ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public String getRequest() {
		return this.request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Set<LogDbSubOperation> getLogDbSubOperations() {
		return this.logDbSubOperations;
	}

	public void setLogDbSubOperations(Set<LogDbSubOperation> logDbSubOperations) {
		this.logDbSubOperations = logDbSubOperations;
	}

}