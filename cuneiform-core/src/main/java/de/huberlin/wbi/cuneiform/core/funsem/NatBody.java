package de.huberlin.wbi.cuneiform.core.funsem;

public class NatBody implements Body {

	private final Amap<String, Alist<Expr>> bodyMap;

	public NatBody( Amap<String, Alist<Expr>> bodyMap ) {
		
		if( bodyMap == null )
			throw new IllegalArgumentException( "Body map must not be null." );
		this.bodyMap = bodyMap;
	}

	@Override
	public boolean isNative() {
		return true;
	}

	public Amap<String, Alist<Expr>> getBodyMap() {
		return bodyMap;
	}

	
}
