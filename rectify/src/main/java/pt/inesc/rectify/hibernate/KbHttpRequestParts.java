package pt.inesc.rectify.hibernate;
// Generated Dec 3, 2016 4:44:04 PM by Hibernate Tools 5.2.0.Beta1

/**
 * KbHttpRequestParts generated by hbm2java
 */
public class KbHttpRequestParts implements java.io.Serializable {

	private Integer id;
	private KbHttpRequest kbHttpRequest;
	private String type;
	private String partKey;
	private String value;

	public KbHttpRequestParts() {
	}

	public KbHttpRequestParts(KbHttpRequest kbHttpRequest) {
		this.kbHttpRequest = kbHttpRequest;
	}

	public KbHttpRequestParts(KbHttpRequest kbHttpRequest, String type, String partKey, String value) {
		this.kbHttpRequest = kbHttpRequest;
		this.type = type;
		this.partKey = partKey;
		this.value = value;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPartKey() {
		return this.partKey;
	}

	public void setPartKey(String partKey) {
		this.partKey = partKey;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
