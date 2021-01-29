package models;

public class FactureClient {
	
	protected int id ;
	protected String code;
	protected ModePayement modePayement;
	public FactureClient(String code, ModePayement modePayement) {
		super();
		this.code = code;
		this.modePayement = modePayement;
	}
	public FactureClient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ModePayement getModePayement() {
		return modePayement;
	}
	public void setModePayement(ModePayement modePayement) {
		this.modePayement = modePayement;
	}
	
	

}
