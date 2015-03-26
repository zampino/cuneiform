package de.huberlin.wbi.cuneiform.core.libfun;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LibFun {
	
	public static Cons list( Term... termVec ) {
		
		Cons result;
		int i;
		
		result = null;
		for( i = termVec.length-1; i >= 0; i-- )
			result = cons( termVec[ i ], result );
		
		return result;
	}
	
	public static Cons cons( Term head, Cons tail ) {
		return new Cons( head, tail );
	}
	
	public static String printTerm( Term term ) {
		
		if( term == null )
			return "[]";
		
		return term.print();
	}
	
	public static Constant<String> constantFrom( String s ) {
		return new Constant<>( s );
	}
	
	public static Constant<Integer> constantFrom( int i ) {
		return new Constant<>( i );
	}
	
	public static boolean unify( Term abstractTerm, Term concreteTerm ) {

		if( concreteTerm instanceof Placeholder )
			throw new UnexpectedPlaceholderException( "Concrete term must not contain placeholderS: "+concreteTerm );
		
		if( abstractTerm == null ) {

			if( concreteTerm == null )
				return true;
			
			return false;
		}
		
		return abstractTerm.unify( concreteTerm );
		
	}

	public static boolean eq( Term a, Term b ) {
		
		if( a == null ) {

			if( b == null )
				return true;
			
			return false;
		}
		
		return a.equals( b );
	}
	
	public static Term unspecialize( Term term ) {
		
		if( term == null )
			return null;
		
		term.unspecialize();
		return term;
	}
	
	public static Map<Term,Term> map() {
		return Collections.unmodifiableMap( new HashMap<Term,Term>() );
	}
	
	public static Map<Term,Term> map( Term key, Term value ) {
		
		Map<Term,Term> map;
		
		map = new HashMap<>();
		map.put( key, value );
		
		return Collections.unmodifiableMap( map );
	}
	
	public static Map<Term,Term> put( Term key, Term value, Map<Term,Term> map ) {
		
		Map<Term,Term> map1;
		
		map1 = new HashMap<>();
		map1.putAll( map );
		map1.put( key, value );
		
		return Collections.unmodifiableMap( map1 );
	}
	
	public static Map<Term,Term> merge( Map<Term,Term> m1, Map<Term,Term> m2 ) {
		
		Map<Term,Term> m3;
		
		m3 = new HashMap<>();
		
		m3.putAll( m2 );
		m3.putAll( m1 );
		return Collections.unmodifiableMap( m3 );
	}
	
	public static Term get( Term key, Map<Term,Term> map ) throws UnboundVarException {
		
		if( !map.containsKey( key ) )
			throw new UnboundVarException( key );
		
		return map.get( key );
	}
	
	public static Term get( Term key, Map<Term,Term> map, Term def ) {
		
		if( !map.containsKey( key ) )
			return def;
		
		return map.get( key );
	}
}
