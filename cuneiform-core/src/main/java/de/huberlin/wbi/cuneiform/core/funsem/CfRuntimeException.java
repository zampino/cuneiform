package de.huberlin.wbi.cuneiform.core.funsem;

public class CfRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -851801101878863155L;

	public CfRuntimeException( Location loc, String msg ) {
		super( loc+": "+msg );
	}
}
