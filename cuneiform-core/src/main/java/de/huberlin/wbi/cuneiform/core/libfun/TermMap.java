package de.huberlin.wbi.cuneiform.core.libfun;

import java.util.HashMap;

public class TermMap {

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
	
	public Term get( Term key ) {
		
		Term value;
		
		value = content.get( key );
		
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
		newContent.putAll( content );
		newContent.putAll( tm2.content );
		
		return new TermMap( newContent );		
	}
	
	public int size() {
		return content.size();
	}
	
	public boolean isEmpty() {
		return content.isEmpty();
	}
}
