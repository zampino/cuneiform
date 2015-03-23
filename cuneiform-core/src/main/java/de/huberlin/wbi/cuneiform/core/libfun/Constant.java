package de.huberlin.wbi.cuneiform.core.libfun;

public class Constant<T> extends Term {

	private final T content;
	
	public Constant( T content ) {
		
		if( content == null )
			throw new IllegalArgumentException( "Constant value must not be null." );
		
		this.content = content;
	}
	

	public T getContent() {
		return content;
	}

	@Override
	protected boolean unify( Term other ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String print() {
		
		if( content instanceof String )
			return "\""+content+"\"";
		
		return content.toString();
	}
}
