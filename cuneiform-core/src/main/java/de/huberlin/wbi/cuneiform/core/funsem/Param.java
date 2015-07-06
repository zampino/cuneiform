package de.huberlin.wbi.cuneiform.core.funsem;

public class Param {

	private final String name;
	
	public Param( String name, boolean isFile, boolean isList ) {
		
		if( name == null )
			throw new IllegalArgumentException( "Parameter name must not be null." );
		
		if( name.isEmpty() )
			throw new IllegalArgumentException( "Parameter name must not be empty." );
		
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
