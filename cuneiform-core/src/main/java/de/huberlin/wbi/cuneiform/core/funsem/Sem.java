package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface Sem {

	public Stream<Expr> eval( Stream<Expr> compoundExpr,
			Map<String, Stream<Expr>> rho, Supplier<Ticket> createTicket,
			Map<String, Stream<Expr>> Override );
}
