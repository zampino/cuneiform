package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.LibFun.printTerm;

public class Record extends Term {

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
	protected boolean unify(Term other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String print() {
		
		StringBuffer buf;
		
		buf = new StringBuffer();
		
		buf.append( '{' ).append( symbol );
		
		for( Term t : termVec )
			buf.append( ',' ).append( printTerm( t ) );
		
		buf.append( '}' );
		
		return buf.toString();
	}
	
	
}
