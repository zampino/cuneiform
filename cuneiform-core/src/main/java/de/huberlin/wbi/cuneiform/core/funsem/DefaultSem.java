package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class DefaultSem implements Sem {

	private static final int MAX_CHANNEL = 16;



	public static Expr[] toExprVec( Stream<Expr> stream ) {

		Object[] resultObj;
		Expr[] resultExpr;
		int n;

		resultObj = stream.toArray();

		n = resultObj.length;
		resultExpr = new Expr[ n ];

		System.arraycopy( resultObj, 0, resultExpr, 0, n );

		return resultExpr;
	}

	private ImmutableMap<String, Expr[]> global;
	private final Supplier<Ticket> createTicket;
	private final List<ImmutableMap<UUID, Expr[]>> fin;

	public DefaultSem( Supplier<Ticket> createTicket ) {

		int i;

		this.createTicket = createTicket;
		global = new ImmutableMap<>();

		fin = new ArrayList<>();
		for( i = 0; i < MAX_CHANNEL; i++ )
			fin.add( new ImmutableMap<UUID, Expr[]>() );
	}

	@Override
	public Expr[] accept( LamExpr lamExpr, ImmutableMap<String, Expr[]> rho ) {
		return new Expr[] { lamExpr };
	}

	@Override
	public Expr[] accept( SelectExpr selectExpr,
			ImmutableMap<String, Expr[]> rho ) {

		UUID ref;
		int channel;
		ImmutableMap<UUID, Expr[]> fmap;

		channel = selectExpr.getChannel();
		ref = selectExpr.getRef();

		fmap = fin.get( channel );

		if( fmap.isKey( ref ) )
			return fmap.get( ref );

		return new Expr[] { selectExpr };
	}

	@Override
	public Expr[] accept( StrExpr strExpr, ImmutableMap<String, Expr[]> rho ) {
		return new Expr[] { strExpr };
	}

	public Supplier<Ticket> getCreateTicket() {
		return createTicket;
	}

	public ImmutableMap<String, Expr[]> getGlobal() {
		return global;
	}

	public void putFin( int i, UUID ref, Expr[] value ) {

		ImmutableMap<UUID, Expr[]> m;

		m = fin.get( i );
		fin.set( i, m.put( ref, value ) );
	}

	public void putGlobal( String key, Expr[] value ) {
		global = global.put( key, value );
	}

}
