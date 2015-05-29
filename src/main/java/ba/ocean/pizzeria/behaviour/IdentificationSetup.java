package ba.ocean.pizzeria.behaviour;

public class IdentificationSetup {
	private String prefix;
	private String pattern;
	private String suffix;
	private String lastId;
	
	public IdentificationSetup() {
		
	}
	
	public IdentificationSetup(String prefix, String pattern, String suffix) {
		this.prefix = prefix;
		this.pattern = pattern;
		this.suffix = suffix;
	}
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getLastId() {
		return lastId;
	}

	public void setLastId(String lastId) {
		this.lastId = lastId;
	}
	
	
	
}
