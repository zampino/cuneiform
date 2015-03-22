package de.huberlin.wbi.cuneiform.core.libfun;

public class Constant<T> implements Term {

	private final T content;
	
	public Constant( T content ) {
		
		if( content == null )
			throw new IllegalArgumentException( "Constant value must not be null." );
		
		this.content = content;
	}
	
	@Override
	public String toString() {
		
		if( content instanceof String )
			return "\""+content+"\"";
		
		return content.toString();
	}

	public T getContent() {
		return content;
	}

	@Override
	public boolean unify(Term other) {
		// TODO Auto-generated method stub
		return false;
	}
}
