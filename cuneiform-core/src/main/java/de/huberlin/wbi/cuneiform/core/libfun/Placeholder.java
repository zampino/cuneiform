package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.LibFun.*;

public class Placeholder extends Term {
	
	private final String name;
	private Term specializedValue;
	private boolean isSpecial;
	
	public Placeholder( String name ) {
		
		if( name == null )
			throw new IllegalArgumentException( "Variable name must not be null." );
		
		if( name.isEmpty() )
			throw new IllegalArgumentException( "Variable name must not be empty." );
		
		if( name.charAt( 0 ) < 65 || name.charAt( 0 ) > 90 )
			throw new IllegalArgumentException( "Variable name must start with capital letter: ["+name+"]" );

		this.name = name;
		isSpecial = false;
	}

	public String getName() {
		return name;
	}

	@Override
	protected boolean unify( Term other ) {
		
		if( isSpecial )
			if( !eq( specializedValue, other ) )
				return false;
		
		specializedValue = other;
		isSpecial = true;
		
		return true;		
	}

	@Override
	protected String print() {
		return name;
	}

	public Object getSpecializedValue() {
		return specializedValue;
	}

	public boolean isSpecialized() {
		return isSpecial;
	}

	@Override
	protected void unspecialize() {
		isSpecial = false;
	}
}
