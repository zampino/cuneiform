package de.huberlin.wbi.cuneiform.core.libfun;

public class PhAsKeyInTermMapException extends UnexpectedPlaceholderException {

	private static final long serialVersionUID = 4593786580572555649L;

	public PhAsKeyInTermMapException() {
		super( "Placeholder must not be used as key in term map." );
	}

}
