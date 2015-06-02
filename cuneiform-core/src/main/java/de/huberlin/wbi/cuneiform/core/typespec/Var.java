package de.huberlin.wbi.cuneiform.core.typespec;

import static de.huberlin.wbi.cuneiform.core.libfun.Constant.*;
import static de.huberlin.wbi.cuneiform.core.libfun.Atom.*;

import de.huberlin.wbi.cuneiform.core.libfun.Record;

public class Var extends Record implements Expr {

	public static final String SYMBOL = "var";
	
	public Var( String varname ) {
		super( atomFrom( SYMBOL ), constantFrom( varname ) );
	}

}
