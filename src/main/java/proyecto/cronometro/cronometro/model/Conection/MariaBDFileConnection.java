package proyecto.cronometro.cronometro.model.Conection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "connection")
@XmlAccessorType(XmlAccessType.FIELD)
public class MariaBDFileConnection {
	
	private static final long serialVersionUID = 1L;
	
	private String uri;
	private String db;
	private String user;
	private String pass;
	
	public MariaBDFileConnection() {
		this.uri = "";
		this.db = "";
		this.user = "";
		this.pass = "";
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
