package de.huberlin.wbi.cuneiform.core.typespec;

import static de.huberlin.wbi.cuneiform.core.libfun.LibFun.constantFrom;

import de.huberlin.wbi.cuneiform.core.libfun.Record;

public class Var extends Record {

	private static final String SYMBOL_VAR = "var";
	
	public Var( String varname ) {
		super( SYMBOL_VAR, constantFrom( varname ) );
	}

}
