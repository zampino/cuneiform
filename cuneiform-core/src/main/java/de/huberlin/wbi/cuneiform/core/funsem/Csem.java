package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.function.Supplier;

import static de.huberlin.wbi.cuneiform.core.funsem.Enumerator.*;
import static de.huberlin.wbi.cuneiform.core.funsem.Util.*;

public class Csem extends DefaultSem {

	public Csem( Supplier<Ticket> createTicket ) {
		super( createTicket );
	}

	@Override
	public Alist<Expr> accept( AppExpr appExpr, Amap<String, Alist<Expr>> rho ) {

		Expr e;
		Alist<Expr> lam, result;
		Sign sign;
		Alist<Amap<String, Alist<Expr>>> enumList;
		Amap<String, Alist<Expr>> bindingMap;
		int channel;
		Location appLoc;

		lam = appExpr.getLam();
		bindingMap = appExpr.getBindingMap();

		if( lam.isEmpty() )
			return NIL;

		if( isFinal( lam ) ) {

			// lambda compound expression is final
			e = lam.hd();
			if( !e.isLamExpr() )
				throw new RuntimeException( "Expected lambda expression: " + e );

			sign = ( (LamExpr) e ).getSign();
			channel = appExpr.getChannel();
			appLoc = appExpr.getLocation();

			if( isFinal( bindingMap ) ) {

				// binding map is final

				if( sign.getCorrelParamVec().length == 0 ) {

					// no task correlation

					enumList = enumerate( sign, bindingMap );

					result = NIL;
					for( Amap<String, Alist<Expr>> en : enumList )
						for( Expr l : lam ) {

							if( !l.isLamExpr() )
								throw new RuntimeException(
										"Expected lambda expression: " + e );

							result = result.append( stepApp( appLoc, channel,
									(LamExpr) l, en ) );
						}

					return result;
				}

				// task correlation must be resolved

				throw new UnsupportedOperationException( "NYI" );
			}

			// return current state
			return NIL.add( appExpr );
		}

		// return current state
		return NIL.add( appExpr );
	}

	public Alist<Expr> stepApp( Location appLoc, int channel, LamExpr lamExpr,
			Amap<String, Alist<Expr>> binding ) {

		Param[] outParamList;
		Param outParam;
		Amap<String, Alist<Expr>> ctx, bodyMap, bodyMap1;
		NatBody natBody;
		Alist<Expr> expr, x;
		String outParamName;
		Alist<Expr> lam1;
		Location lamLoc;
		Sign sign;

		if( lamExpr.isNative() ) {

			sign = lamExpr.getSign();
			outParamList = sign.getOutParamVec();
			outParam = outParamList[ channel - 1 ];
			outParamName = outParam.getName();
			natBody = (NatBody) lamExpr.getBody();
			bodyMap = natBody.getBodyMap();
			lamLoc = lamExpr.getLocation();

			ctx = bodyMap.merge( binding );
			expr = ctx.get( outParamName );

			x = step( expr, ctx );

			if( isFinal( x ) )
				return x;

			bodyMap1 = bodyMap.put( outParamName, x );
			lam1 = NIL
					.add( new LamExpr( lamLoc, sign, new NatBody( bodyMap1 ) ) );

			return NIL.add( new AppExpr( appLoc, channel, lam1, binding ) );

		}

		throw new UnsupportedOperationException( "NYI" );

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

		result = step( compoundExpr, rho );

		if( compoundExpr.equals( result ) )
			return result;

		return eval( result, rho );
	}

	public Alist<Expr> step( Alist<Expr> compoundExpr,
			Amap<String, Alist<Expr>> rho ) {
		return compoundExpr.flatMap( ( Expr e ) -> stepSingle( e, rho ) );
	}

	private Alist<Expr> stepSingle( Expr expr, Amap<String, Alist<Expr>> rho ) {
		return expr.visit( this, rho );
	}
}
