package de.huberlin.wbi.cuneiform.core.typespec;

import static de.huberlin.wbi.cuneiform.core.libfun.Constant.*;
import static de.huberlin.wbi.cuneiform.core.libfun.Atom.*;

import de.huberlin.wbi.cuneiform.core.libfun.Record;

public class Str extends Record implements Expr {

	public static final String SYMBOL = "str";
	
	public Str( String value ) {
		super( atomFrom( SYMBOL ), constantFrom( value ) );
	}

}
