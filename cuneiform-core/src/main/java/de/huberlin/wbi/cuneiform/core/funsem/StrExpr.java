package de.huberlin.wbi.cuneiform.core.funsem;

public class StrExpr implements Expr {

	private final String content;
	
	public StrExpr( String content ) {
		this.content = content;
	}

	@Override
	public boolean equals( Object obj ) {
		
		StrExpr other;
		
		if( obj == null )
			return false;
		
		if( !( obj instanceof StrExpr ) )
			return false;
		
		other = ( StrExpr )obj;
		
		return content.equals( other.content );
	}

	@Override
	public int hashCode() {
		return content.hashCode();
	}
	
	@Override
	public String toString() {
		return "{str,\""+content+"\"}";
	}

	@Override
	public Alist<Expr> visit( Sem sem, Amap<String, Alist<Expr>> rho ) {
		return sem.accept( this, rho );
	}
	

}
