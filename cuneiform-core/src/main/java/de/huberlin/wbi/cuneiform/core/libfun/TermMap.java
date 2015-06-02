package de.huberlin.wbi.cuneiform.core.libfun;

import java.util.HashMap;

public class TermMap implements Term {

	private final HashMap<Term,Term> content;
	
	public TermMap( Term key, Term value ) {
		this();
		content.put( key, value );
	}
	
	public TermMap() {
		content = new HashMap<>();
	}
	
	private TermMap( HashMap<Term,Term> content ) {
		this.content = content;
	}
	
	public TermMap put( Term key, Term value ) {
		
		HashMap<Term,Term> newContent;
		
		newContent = new HashMap<>();
		newContent.putAll( content );
		newContent.put( key, value );
		
		return new TermMap( newContent );
	}
	
	public Term get( Term key ) throws UnboundKeyException {
		
		Term value;
		
		if( key == null )
			throw new IllegalArgumentException( "Key must not be null." );
		
		value = content.get( key );
		if( value == null )
			throw new UnboundKeyException( key );
		
		return value;
	}
	
	public Term get( Term key, Term def ) {
		
		if( content.containsKey( key ) )
			return content.get( key );
		
		return def;
		
	}
	
	public TermMap merge( TermMap tm2 ) {
		
		HashMap<Term,Term> newContent;
		
		newContent = new HashMap<>();
		newContent.putAll( tm2.content );
		newContent.putAll( content );
		
		return new TermMap( newContent );		
	}
	
	public int size() {
		return content.size();
	}
	
	public boolean isEmpty() {
		return content.isEmpty();
	}

	@Override
	public boolean unify( Term other ) {
		
		if( other == null )
			throw new IllegalArgumentException( "Other term must not be null." );
		
		if( other instanceof Placeholder )
			throw new UnexpectedPlaceholderException( ( Placeholder )other );
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unspecialize() {
		// TODO Auto-generated method stub
		
	}
}
