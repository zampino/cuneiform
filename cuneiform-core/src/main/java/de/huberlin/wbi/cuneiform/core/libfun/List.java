package de.huberlin.wbi.cuneiform.core.libfun;

import static de.huberlin.wbi.cuneiform.core.libfun.Nil.NIL;

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
}
