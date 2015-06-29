package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Csem extends DefaultSem {

	public Csem(Map<String, Expr[]> global, Supplier<Ticket> createTicket,
			Map<ChannelRef, Expr[]> fin) {
		super( global, createTicket, fin );
	}

	@Override
	public Expr[] eval( Expr[] compoundExpr, Map<String, Expr[]> rho ) {

		Stream<Expr> stream;
		Expr[] result;

		stream = Stream.of( compoundExpr );

		result = toExprVec( stream.flatMap( ( Expr e ) -> Stream
				.of( stepSingle( e, rho ) ) ) );

		if( Arrays.deepEquals( compoundExpr, result ) )
			return result;

		return eval( result, rho );
	}

	private Expr[] stepSingle( Expr expr, Map<String, Expr[]> rho ) {
		return expr.visit( this, rho );
	}

	@Override
	public Expr[] accept( VarExpr varExpr, Map<String, Expr[]> rho ) {

		String name;
		Map<String, Expr[]> global;

		name = varExpr.getName();
		global = getGlobal();

		if( global.containsKey( name ) )
			return global.get( name );

		if( !rho.containsKey( name ) )
			throw new CfRuntimeException( varExpr.getLoc(), "The variable "
					+ name + " is unbound." );

		return rho.get( name );
	}
}
