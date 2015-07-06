package de.huberlin.wbi.cuneiform.core.funsem;


public class LamExpr implements Expr {

	private final Sign sign;
	
	public LamExpr( Location loc, Sign sign, NatBody body ) {
		
		if( sign == null )
			throw new IllegalArgumentException( "Signature must not be null." );
		
		this.sign = sign;
	}

	@Override
	public Alist<Expr> visit( Sem sem, Amap<String, Alist<Expr>> rho ) {
		return sem.accept( this, rho );
	}

	public Sign getSign() {
		return sign;
	}

}
