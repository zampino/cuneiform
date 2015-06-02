package de.huberlin.wbi.cuneiform.core.typespec;

import de.huberlin.wbi.cuneiform.core.libfun.Record;
import de.huberlin.wbi.cuneiform.core.libfun.TermMap;
import static de.huberlin.wbi.cuneiform.core.libfun.Atom.*;

public class Nat extends Record implements Body {

	public static final String SYMBOL = "nat";
	
	public Nat( TermMap bodyMap ) {
		super( atomFrom( SYMBOL ), bodyMap );
	}

}
