package de.huberlin.wbi.cuneiform.core.libfun;

public class UnboundVarException extends Exception {

	private static final long serialVersionUID = -6307928111959625078L;

	public UnboundVarException( Term varName ) {
		super( "Variable "+varName+" is unbound." );
	}
}
