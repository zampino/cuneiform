package de.huberlin.wbi.cuneiform.core.libfun;

public class PhIsKeyInTermMapException extends UnexpectedPlaceholderException {

	private static final long serialVersionUID = 4593786580572555649L;

	public PhIsKeyInTermMapException() {
		super( "Placeholder must not appear as key in term map." );
	}

}
