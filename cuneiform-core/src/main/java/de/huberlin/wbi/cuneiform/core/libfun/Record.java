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
	public boolean equals( Object obj ) {
		
		Record other;
		int i;
		
		if( !( obj instanceof Record ) )
			return false;
		
		other = ( Record )obj;
		
		if( !symbol.equals( other.symbol ) )
			return false;
		
		if( termVec.length != other.termVec.length )
			return false;
		
		for( i = 0; i < termVec.length; i++ )
			if( !termVec[ i ].equals( other.termVec[ i ] ) )
				return false;
		
		return true;
	}

	@Override
	public boolean unify( Term other ) {
		
		Record record;
		int i;
		
		if( other == null )
			throw new IllegalArgumentException( "Other term must not be null." );
		
		if( other instanceof Placeholder )
			throw new UnexpectedPlaceholderException( ( Placeholder )other );
		
		if( !( other instanceof Record ) )
			return false;
		
		record = ( Record )other;
		
		if( !symbol.equals( record.symbol ) )
			return false;
			
		if( termVec.length != record.termVec.length )
			return false;
		
		for( i = 0; i < termVec.length; i++ )
			if( !termVec[ i ].unify( record.termVec[ i ] ) )
				return false;
		
		return true;
	}

	@Override
	public String print() {
		
		StringBuffer buf;
		
		buf = new StringBuffer();
		
		buf.append( '{' ).append( symbol );
		
		for( Term t : termVec )
			buf.append( ',' ).append( t.print() );
		
		buf.append( '}' );
		
		return buf.toString();
	}

	@Override
	public int hashCode() {
		return symbol.hashCode();
	}

	@Override
	public void unspecialize() {
		
		for( Term t : termVec )
			t.unspecialize();
	}
}
