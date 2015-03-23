package de.huberlin.wbi.cuneiform.core.libfun;

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

		if( concreteTerm instanceof Var )
			throw new UnexpectedVarException( "Concrete term must not contain variable: "+concreteTerm );
		
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
}
