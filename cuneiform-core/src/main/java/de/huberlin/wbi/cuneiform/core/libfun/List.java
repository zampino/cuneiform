package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.Nil.NIL;

import java.util.HashSet;
import java.util.Set;

public abstract class List implements Term {

	public abstract Term hd();
	public abstract List tl();
	
	public static List list( Term... termVec ) {
		
		List result;
		int i;
		
		result = NIL;
		for( i = termVec.length-1; i >= 0; i-- )
			result = new Cons( termVec[ i ], result );
		
		return result;
	}
	
	public abstract int size();

	public List usort() {
		
		List result;
		List trav;
		Set<Term> elementSet;
		
		elementSet = new HashSet<>();
		trav = this;
		
		while( trav != NIL ) {	
			elementSet.add( trav.hd() );
			trav = trav.tl();
		}
		
		result = NIL;
		
		for( Term element : elementSet )
			result = cons( element, result );
		
		return result;
	}
	
	public List reverse() {
		
		List result;
		List trav;
		
		trav = this;
		result = NIL;
		
		while( trav != NIL ) {
			result = cons( trav.hd(), result );
			trav = trav.tl();
		}
		
		return result;
	}
	
	public List map( Fun fun ) {
		
		List trav, result;
		
		trav = this;
		result = NIL;
		
		while( trav != NIL ) {
			result = cons( fun.apply( trav.hd() ), result );
			trav = trav.tl();
		}
		
		return result.reverse();
	}
	
	public static Cons cons( Term t, List l ) {
		return new Cons( t, l );
	}
	
}
