package de.huberlin.wbi.cuneiform.core.libfun;


public class Placeholder implements Term {
	
	public static final Placeholder ANY = new Placeholder() {
		
		@Override
		public boolean unify( Term other ) {
			
			if( other == null )
				throw new IllegalArgumentException( "Other term must not be null." );
			
			if( other instanceof Placeholder )
				throw new PhOnRightHandSideException();
			
			return true;
		}
	};
	
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

	public Term getSpecializedValue() {
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
