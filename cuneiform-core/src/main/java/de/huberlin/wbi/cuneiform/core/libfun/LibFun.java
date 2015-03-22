package de.huberlin.wbi.cuneiform.core.libfun;

public class LibFun {

	@SuppressWarnings("unused")
	public static boolean isList( Cons cons ) {
		return true;
	}
	
	@SuppressWarnings("unused")
	public static boolean isList( Term term ) {		
		return false;
	}
	
	public static Cons cons( Term head, Cons tail ) {
		return new Cons( head, tail );
	}
	
	public static String printTerm( Term term ) {
		
		if( term == null )
			return "[]";
		
		return term.toString();
	}
	
	public static boolean unify( Term abstractTerm, Term concreteTerm ) {

		if( concreteTerm instanceof Var )
			throw new RuntimeException( "Concrete term must not contain variable: "+concreteTerm );
		
		if( abstractTerm == null ) {

			if( concreteTerm == null )
				return true;
			
			return false;
		}
		
		return abstractTerm.unify( concreteTerm );
		
	}

}
