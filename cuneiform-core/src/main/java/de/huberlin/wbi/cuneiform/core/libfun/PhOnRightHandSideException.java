package de.huberlin.wbi.cuneiform.core.libfun;

public class PhOnRightHandSideException extends UnexpectedPlaceholderException {

	private static final long serialVersionUID = -3319127875026655460L;

	public PhOnRightHandSideException() {
		super( "Placeholder must not appear on right-hand side of unification." );
	}
}
