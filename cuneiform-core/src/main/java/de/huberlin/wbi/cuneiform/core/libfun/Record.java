package de.huberlin.wbi.cuneiform.core.libfun;

public class Record implements Term {

	private final String symbol;
	private final Term[] termVec;
	
	public Record( String symbol, Term... termVec ) {
		
		if( symbol == null )
			throw new IllegalArgumentException( "Symbol must not be null." );
		
		if( symbol.isEmpty() )
			throw new IllegalArgumentException( "Symbol must not be empty." );
		
		if( symbol.charAt( 0 ) < 97 || symbol.charAt( 0 ) > 122 )
			throw new IllegalArgumentException( "Symbol must start with a lower case letter: ["+symbol+"]" );
		
		this.symbol = symbol;
		this.termVec = termVec;
	}

	public String getSymbol() {
		return symbol;
	}

	public int length() {
		return termVec.length;
	}

	@Override
	public boolean unify(Term other) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
