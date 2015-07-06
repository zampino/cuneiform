package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class DefaultSem implements Sem {

	private static final int MAX_CHANNEL = 16;

	private Amap<String, Alist<Expr>> global;
	private final Supplier<Ticket> createTicket;
	private final List<Amap<UUID, Alist<Expr>>> fin;

	public DefaultSem( Supplier<Ticket> createTicket ) {

		int i;

		this.createTicket = createTicket;
		global = new Amap<>();

		fin = new ArrayList<>();
		for( i = 0; i < MAX_CHANNEL; i++ )
			fin.add( new Amap<UUID, Alist<Expr>>() );
	}

	@Override
	public Alist<Expr> accept( LamExpr lamExpr, Amap<String, Alist<Expr>> rho ) {
		
		Alist<Expr> result;
		
		result = new Alist<>();
		result = result.add( lamExpr );
		return result;
	}

	@Override
	public Alist<Expr> accept( SelectExpr selectExpr,
			Amap<String, Alist<Expr>> rho ) {

		UUID ref;
		int channel;
		Amap<UUID, Alist<Expr>> fmap;
		Alist<Expr> result;

		channel = selectExpr.getChannel();
		ref = selectExpr.getRef();

		fmap = fin.get( channel );

		if( fmap.isKey( ref ) )
			return fmap.get( ref );
		
		result = new Alist<>();
		result = result.add( selectExpr );
		
		return result;
	}

	@Override
	public Alist<Expr> accept( StrExpr strExpr, Amap<String, Alist<Expr>> rho ) {
		
		Alist<Expr> result;
		
		result = new Alist<>();
		result = result.add( strExpr );
		
		return result;
	}

	public Supplier<Ticket> getCreateTicket() {
		return createTicket;
	}

	public Amap<String, Alist<Expr>> getGlobal() {
		return global;
	}

	public void putFin( int i, UUID ref, Alist<Expr> value ) {

		Amap<UUID, Alist<Expr>> m;

		m = fin.get( i );
		fin.set( i, m.put( ref, value ) );
	}

	public void putGlobal( String key, Alist<Expr> value ) {
		global = global.put( key, value );
	}

}
