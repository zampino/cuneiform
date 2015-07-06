package de.huberlin.wbi.cuneiform.core.funsem;

public class AppExpr implements Expr {
	
	private final Expr[] lam;
	private final ImmutableMap<String,Expr[]> bindingMap;

	public AppExpr( Location loc, int channel, Expr[] lam,
			ImmutableMap<String, Expr[]> bindingMap ) {
		
		if( lam == null )
			throw new IllegalArgumentException( "Lambda compound expression must not be null." );
		
		if( bindingMap == null )
			throw new IllegalArgumentException( "Binding map must not be null." );
		
		this.lam = lam;
		this.bindingMap = bindingMap;
	}

	@Override
	public Expr[] visit( Sem sem, ImmutableMap<String, Expr[]> rho ) {
		return sem.accept( this, rho );
	}

	public Expr[] getLam() {
		return lam;
	}

	public ImmutableMap<String, Expr[]> getBindingMap() {
		return bindingMap;
	}

}
