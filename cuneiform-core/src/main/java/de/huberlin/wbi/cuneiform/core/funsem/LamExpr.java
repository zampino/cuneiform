package de.huberlin.wbi.cuneiform.core.funsem;

public class LamExpr implements Expr {

	private final Sign sign;
	private final Body body;
	private final Location loc;

	public LamExpr( Location loc, Sign sign, Body body ) {

		if( sign == null )
			throw new IllegalArgumentException( "Signature must not be null." );
		
		if( body == null )
			throw new IllegalArgumentException( "Body must not be null." );
		
		if( loc == null )
			throw new IllegalArgumentException( "Location must not be null." );

		this.sign = sign;
		this.body = body;
		this.loc = loc;
	}

	public Sign getSign() {
		return sign;
	}

	@Override
	public boolean isFinal() {
		return true;
	}

	@Override
	public Alist<Expr> visit( Sem sem, Amap<String, Alist<Expr>> rho ) {
		return sem.accept( this, rho );
	}

	@Override
	public boolean isLamExpr() {
		return true;
	}

	public Body getBody() {
		return body;
	}
	
	public boolean isNative() {
		return body.isNative();
	}

	public Location getLocation() {
		return loc;
	}
}
