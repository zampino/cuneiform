package de.huberlin.wbi.cuneiform.core.libfun;

public class Var extends Term {
	
	private final String name;
	private Term specializedValue;
	
	public Var( String name ) {
		
		if( name == null )
			throw new IllegalArgumentException( "Variable name must not be null." );
		
		if( name.isEmpty() )
			throw new IllegalArgumentException( "Variable name must not be empty." );
		
		if( name.charAt( 0 ) < 65 || name.charAt( 0 ) > 90 )
			throw new IllegalArgumentException( "Variable name must start with capital letter: ["+name+"]" );

		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	protected boolean unify( Term other ) {
		
		specializedValue = other;
		
		return true;		
	}

	@Override
	protected String print() {
		return name;
	}

	public Object getSpecializedValue() {
		return specializedValue;
	}
}
