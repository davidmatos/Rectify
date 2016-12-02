package pt.inesc.rectify.hibernate;
// Generated Dec 1, 2016 10:02:07 PM by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * KbDbOp generated by hbm2java
 */
public class KbDbOp implements java.io.Serializable {

	private Integer id;
	private KbHttpRequest kbHttpRequest;
	private Date ts;
	private String query;
	private Set<KbDbOpParts> kbDbOpPartses = new HashSet<KbDbOpParts>(0);

	public KbDbOp() {
	}

	public KbDbOp(Date ts) {
		this.ts = ts;
	}

	public KbDbOp(KbHttpRequest kbHttpRequest, Date ts, String query, Set<KbDbOpParts> kbDbOpPartses) {
		this.kbHttpRequest = kbHttpRequest;
		this.ts = ts;
		this.query = query;
		this.kbDbOpPartses = kbDbOpPartses;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KbHttpRequest getKbHttpRequest() {
		return this.kbHttpRequest;
	}

	public void setKbHttpRequest(KbHttpRequest kbHttpRequest) {
		this.kbHttpRequest = kbHttpRequest;
	}

	public Date getTs() {
		return this.ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Set<KbDbOpParts> getKbDbOpPartses() {
		return this.kbDbOpPartses;
	}

	public void setKbDbOpPartses(Set<KbDbOpParts> kbDbOpPartses) {
		this.kbDbOpPartses = kbDbOpPartses;
	}

}