package de.huberlin.wbi.cuneiform.core.libfun;

public class Atom implements Term {
	
	private final String name;
	
	public Atom( String name ) {
		
		if( name == null )
			throw new IllegalArgumentException( "Atom name must not be null." );
		
		if( name.isEmpty() )
			throw new IllegalArgumentException( "Atom name must not be empty." );
		
		if( name.charAt( 0 ) < 97 || name.charAt( 0 ) > 122 )
			throw new IllegalArgumentException( "Symbol must start with a lower case letter: ["+name+"]" );

		
		this.name = name;
	}
	
	@Override
	public boolean unify( Term other ) {
		
		if( other == null )
			throw new IllegalArgumentException( "Other term must not be null." );
		
		if( other instanceof Placeholder )
			throw new PhOnRightHandSideException();
		
		if( !( other instanceof Atom ) )
			return false;
		
		return name.equals( ( ( Atom )other ).name );
	}

	@Override
	public String print() {
		return name;
	}

	@Override
	public void unspecialize() {
		// unspecialize leaves atom untouched
	}

	public static Atom atomFrom( String name ) {
		return new Atom( name );
	}
	
	@Override
	public boolean equals( Object other ) {
		
		if( other == null )
			return false;
		
		if( !( other instanceof Atom ) )
			return false;
		
		return name.equals( ( ( Atom )other ).name );		
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
