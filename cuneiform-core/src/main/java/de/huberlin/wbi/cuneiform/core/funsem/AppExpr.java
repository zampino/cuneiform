package de.huberlin.wbi.cuneiform.core.funsem;

public class AppExpr implements Expr {

	private final int channel;
	private final Alist<Expr> lam;
	private final Amap<String, Alist<Expr>> bindingMap;
	private final Location location;

	public AppExpr( Location location, int channel, Alist<Expr> lam,
			Amap<String, Alist<Expr>> bindingMap ) {

		if( lam == null )
			throw new IllegalArgumentException(
					"Lambda compound expression must not be null." );

		if( bindingMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );
		
		if( channel < 1 )
			throw new IllegalArgumentException( "Channel must be a positive number." );
		
		if( location == null )
			throw new IllegalArgumentException( "Location must not be null." );

		this.lam = lam;
		this.bindingMap = bindingMap;
		this.channel = channel;
		this.location = location;
	}

	public Amap<String, Alist<Expr>> getBindingMap() {
		return bindingMap;
	}

	public Alist<Expr> getLam() {
		return lam;
	}

	@Override
	public boolean isFinal() {
		return false;
	}

	@Override
	public Alist<Expr> visit( Sem sem, Amap<String, Alist<Expr>> rho ) {
		return sem.accept( this, rho );
	}

	public int getChannel() {
		return channel;
	}

	public Location getLocation() {
		return location;
	}

	@Override
	public boolean isLamExpr() {
		return false;
	}

}
