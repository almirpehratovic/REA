package ba.ocean.pizzeria.behaviour;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * Part of Identification Pattern (Hruby, 159). If pattern is configured as auto-numbered then
 * most important property is lastId. Prefix, pattern and suffix can be configured by application
 * users and are used only when Identification Pattern is not configured as auto-numbered.
 */
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
