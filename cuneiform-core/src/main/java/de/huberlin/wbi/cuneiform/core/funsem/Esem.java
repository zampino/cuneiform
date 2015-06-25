package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Esem implements Sem {

	@Override
	public Stream<Expr> eval(Stream<Expr> compoundExpr,
			Map<String, Stream<Expr>> rho, Supplier<Ticket> createTicket,
			Map<String, Stream<Expr>> override) {

		return compoundExpr.flatMap((Expr e) -> evalSingle(e, rho,
				createTicket, override));
	}

	private static Stream<Expr> evalSingle(StringExpr expr, Map<String, Stream<Expr>> rho,
			Supplier<Ticket> createTicket, Map<String, Stream<Expr>> override) {
		
		return Stream.of( expr );
	}

	private static Stream<Expr> evalSingle(Expr expr, Map<String, Stream<Expr>> rho,
			Supplier<Ticket> createTicket, Map<String, Stream<Expr>> override) {
		return null;
	}
}
