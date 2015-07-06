package de.huberlin.wbi.cuneiform.core.funsem;

public class Param {

	private final String name;
	private final boolean isFile;
	private final boolean isList;
	
	public Param( String name, boolean isFile, boolean isList ) {
		
		if( name == null )
			throw new IllegalArgumentException( "Parameter name must not be null." );
		
		if( name.isEmpty() )
			throw new IllegalArgumentException( "Parameter name must not be empty." );
		
		this.name = name;
		this.isFile = isFile;
		this.isList = isList;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "{param,\""+name+"\","+isFile+","+isList+"}";
	}

}
