package de.huberlin.wbi.cuneiform.core.libfun;

public class UnexpectedPlaceholderException extends RuntimeException {

	private static final long serialVersionUID = -3319127875026655460L;

	public UnexpectedPlaceholderException( Placeholder ph ) {
		super( "Placeholder "+ph+" must not appear on right-hand side of unification." );
	}
}
