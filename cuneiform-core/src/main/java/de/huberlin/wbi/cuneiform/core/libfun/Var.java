package de.huberlin.wbi.cuneiform.core.libfun;

public class Var implements Term {
	
	private final String name;
	
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
	public boolean unify(Term other) {
		// TODO Auto-generated method stub
		return false;
	}
}
