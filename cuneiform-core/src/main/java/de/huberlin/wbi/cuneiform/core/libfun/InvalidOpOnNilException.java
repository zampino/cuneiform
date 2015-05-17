package de.huberlin.wbi.cuneiform.core.libfun;

public class InvalidOpOnNilException extends RuntimeException {

	private static final long serialVersionUID = -2674153155428160980L;

	public InvalidOpOnNilException( String msg ) {
		super( msg );
	}

}
