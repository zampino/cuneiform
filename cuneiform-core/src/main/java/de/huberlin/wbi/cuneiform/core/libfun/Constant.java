package de.huberlin.wbi.cuneiform.core.libfun;

public class Constant<T> implements Term {

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
	public boolean unify( Term other ) {
		return equals( other );
	}

	@Override
	public String print() {
		
		if( content instanceof String )
			return "\""+content+"\"";
		
		return content.toString();
	}
	
	@Override
	public boolean equals( Object obj ) {
				
		if( !( obj instanceof Constant ) )
			return false;
		
		return content.equals( ( ( Constant<?> )obj ).content );
	}


	@Override
	public int hashCode() {
		return content.hashCode();
	}


	@Override
	public void unspecialize() {}
	
	public static Constant<String> constantFrom( String value ) {
		return new Constant<>( value );
	}
	
	public static Constant<Integer> constantFrom( int value ) {
		return new Constant<>( value );
	}
}
