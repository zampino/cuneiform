package de.huberlin.wbi.cuneiform.core.typespec;

import static de.huberlin.wbi.cuneiform.core.libfun.Constant.*;
import static de.huberlin.wbi.cuneiform.core.libfun.Atom.*;
import de.huberlin.wbi.cuneiform.core.libfun.Record;


public class Outvar extends Record {
	
	public static String SYMBOL = "outvar";

	public Outvar( String outvarName, boolean isFile, boolean isList ) {
		super(
			atomFrom( SYMBOL ),
			constantFrom( outvarName ),
			constantFrom( isFile ),
			constantFrom( isList ) );
	}
}
