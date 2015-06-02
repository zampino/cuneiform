package de.huberlin.wbi.cuneiform.core.libfun;


public class Placeholder implements Term {
	
	private Term specializedValue;
	private boolean isSpecial;
	
	public Placeholder() {
		isSpecial = false;
		specializedValue = null;
	}

	@Override
	public boolean unify( Term other ) {
		
		if( other == null )
			throw new IllegalArgumentException( "Other term must not be null." );
		
		if( other instanceof Placeholder )
			throw new PhOnRightHandSideException();
		
		if( isSpecial )
			if( !specializedValue.equals( other ) )
				return false;
		
		specializedValue = other;
		isSpecial = true;
		
		return true;		
	}

	@Override
	public String print() {
		return toString();
	}

	public Object getSpecializedValue() {
		return specializedValue;
	}

	public boolean isSpecialized() {
		return isSpecial;
	}

	@Override
	public void unspecialize() {
		isSpecial = false;
	}
}
