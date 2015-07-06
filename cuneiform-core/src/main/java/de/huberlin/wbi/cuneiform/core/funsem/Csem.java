package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static de.huberlin.wbi.cuneiform.core.funsem.Enumerator.*;

public class Csem extends DefaultSem {

	public Csem( Supplier<Ticket> createTicket ) {
		super( createTicket );
	}

	@Override
	public Expr[] accept( AppExpr appExpr, ImmutableMap<String, Expr[]> rho ) {
		
		Expr[] lam;
		Sign sign;
		List<ImmutableMap<String,Expr[]>> enumList;
		ImmutableMap<String,Expr[]> bindingMap;
		
		lam = appExpr.getLam();
		bindingMap = appExpr.getBindingMap();
		
		if( lam.length == 0 )
			return new Expr[] {};
		
		sign = ( ( LamExpr )lam[ 0 ] ).getSign();
		
		enumList = enumerate( sign, bindingMap );
		
        /* EnumList = enum( Sign, BindingMap ),
        flatten( [step_app( AppLoc, Channel, L, E, CreateTicket, Override ) || L <- LamList, E <- EnumList] ); */
		
		
		return null;
	}

	@Override
	public Expr[] accept( VarExpr varExpr, ImmutableMap<String, Expr[]> rho ) {

		String name;
		ImmutableMap<String, Expr[]> global;

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
	public Expr[] eval( Expr[] compoundExpr, ImmutableMap<String, Expr[]> rho ) {

		Stream<Expr> stream;
		Expr[] result;

		stream = Stream.of( compoundExpr );

		result = toExprVec( stream.flatMap( ( Expr e ) -> Stream
				.of( stepSingle( e, rho ) ) ) );

		if( Arrays.deepEquals( compoundExpr, result ) )
			return result;

		return eval( result, rho );
	}

	private Expr[] stepSingle( Expr expr, ImmutableMap<String, Expr[]> rho ) {
		return expr.visit( this, rho );
	}
}
