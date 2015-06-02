package de.huberlin.wbi.cuneiform.core.libfun;

public class Record implements Term {

	private final Term[] termVec;
	
	public Record( Term... termVec ) {		
		this.termVec = termVec;
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
			throw new PhOnRightHandSideException();
		
		if( !( other instanceof Record ) )
			return false;
		
		record = ( Record )other;
		
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
		boolean comma;
		
		buf = new StringBuffer();
		
		buf.append( '{' );
		
		comma = false;
		for( Term t : termVec ) {
			
			if( comma )
				buf.append( ',' );
			comma = true;
			
			buf.append( t.print() );
		}
		
		buf.append( '}' );
		
		return buf.toString();
	}

	@Override
	public int hashCode() {
		return termVec.length;
	}

	@Override
	public void unspecialize() {
		
		for( Term t : termVec )
			t.unspecialize();
	}
}
