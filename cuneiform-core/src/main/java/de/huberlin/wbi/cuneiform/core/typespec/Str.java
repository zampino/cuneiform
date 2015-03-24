package de.huberlin.wbi.cuneiform.core.typespec;

import de.huberlin.wbi.cuneiform.core.libfun.Record;

import static de.huberlin.wbi.cuneiform.core.libfun.LibFun.*;

public class Str extends Record {

	private static final String SYMBOL_STR = "str";
	
	public Str( String value ) {
		super( SYMBOL_STR, constantFrom( value ) );
	}

}
