package pl.ksb.agape.tools.reflect;

public class EntityField {
	private String name;
	private String dbName;
	@SuppressWarnings("rawtypes")
	private Class type;

	public EntityField(String name, String dbName,
			@SuppressWarnings("rawtypes") Class type) {
		super();
		this.name = name;
		this.dbName = dbName;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@SuppressWarnings("rawtypes")
	public Class getType() {
		return type;
	}

	@SuppressWarnings("rawtypes")
	public void setType(Class type) {
		this.type = type;
	}

}
