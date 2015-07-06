package de.huberlin.wbi.cuneiform.core.funsem;

public class AppExpr implements Expr {

	private final Alist<Expr> lam;
	private final Amap<String, Alist<Expr>> bindingMap;

	public AppExpr( Location loc, int channel, Alist<Expr> lam,
			Amap<String, Alist<Expr>> bindingMap ) {

		if( lam == null )
			throw new IllegalArgumentException(
					"Lambda compound expression must not be null." );

		if( bindingMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );

		this.lam = lam;
		this.bindingMap = bindingMap;
	}

	@Override
	public Alist<Expr> visit( Sem sem, Amap<String, Alist<Expr>> rho ) {
		return sem.accept( this, rho );
	}

	public Alist<Expr> getLam() {
		return lam;
	}

	public Amap<String, Alist<Expr>> getBindingMap() {
		return bindingMap;
	}

}
