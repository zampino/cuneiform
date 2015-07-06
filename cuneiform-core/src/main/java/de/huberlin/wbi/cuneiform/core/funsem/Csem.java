package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.function.Supplier;

import static de.huberlin.wbi.cuneiform.core.funsem.Enumerator.*;

public class Csem extends DefaultSem {

	public Csem( Supplier<Ticket> createTicket ) {
		super( createTicket );
	}

	@Override
	public Alist<Expr> accept( AppExpr appExpr, Amap<String, Alist<Expr>> rho ) {

		Alist<Expr> lam;
		Sign sign;
		Alist<Amap<String, Alist<Expr>>> enumList;
		Amap<String, Alist<Expr>> bindingMap;

		lam = appExpr.getLam();
		bindingMap = appExpr.getBindingMap();

		if( lam.isEmpty() )
			return new Alist<>();

		sign = ( ( LamExpr )lam.hd() ).getSign();

		enumList = enumerate( sign, bindingMap );

		/*
		 * EnumList = enum( Sign, BindingMap ), flatten( [step_app( AppLoc,
		 * Channel, L, E, CreateTicket, Override ) || L <- LamList, E <-
		 * EnumList] );
		 */

		return null;
	}

	@Override
	public Alist<Expr> accept( VarExpr varExpr, Amap<String, Alist<Expr>> rho ) {

		String name;
		Amap<String, Alist<Expr>> global;

		name = varExpr.getName();
		global = getGlobal();

		if( global.isKey( name ) )
			return global.get( name );

		if( !rho.isKey( name ) )
			throw new CfRuntimeException( varExpr.getLoc(), "The variable "
					+ name + " is unbound." );

		return rho.get( name );
	}

	@Override
	public Alist<Expr> eval( Alist<Expr> compoundExpr,
			Amap<String, Alist<Expr>> rho ) {

		Alist<Expr> result;

		result = compoundExpr.flatMap( ( Expr e ) -> stepSingle( e, rho ) );

		if( compoundExpr.equals( result ) )
			return result;

		return eval( result, rho );
	}

	private Alist<Expr> stepSingle( Expr expr, Amap<String, Alist<Expr>> rho ) {
		return expr.visit( this, rho );
	}
}
