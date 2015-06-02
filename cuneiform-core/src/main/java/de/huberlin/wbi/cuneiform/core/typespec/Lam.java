package de.huberlin.wbi.cuneiform.core.typespec;

import static de.huberlin.wbi.cuneiform.core.libfun.Atom.atomFrom;

import de.huberlin.wbi.cuneiform.core.libfun.Record;

public class Lam extends Record implements Expr {
	
	public static final String SYMBOL = "lam";

	public Lam( Sign sign, Body body ) {
		super( atomFrom( SYMBOL ), sign, body );
	}
}
