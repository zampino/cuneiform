package de.huberlin.wbi.cuneiform.core.libfun;

public class Nil extends List {

	public static final Nil NIL = new Nil(); 
	
	private Nil() {}

	@Override
	public boolean unify( Term other ) {
		
		if( other == null )
			throw new IllegalArgumentException( "Other term must not be null." );
		
		if( other instanceof Placeholder )
			throw new PhOnRightHandSideException();
		
		return this == other;
	}

	@Override
	public String print() {
		return "[]";
	}

	@Override
	public void unspecialize() {
		// unspecialize leaves nil untouched
	}

	@Override
	public Term hd() {
		throw new InvalidOpOnNilException( "Cannot retrieve head from nil." );
	}

	@Override
	public List tl() {
		throw new InvalidOpOnNilException( "Cannot retrieve tail from nil." );
	}
}
