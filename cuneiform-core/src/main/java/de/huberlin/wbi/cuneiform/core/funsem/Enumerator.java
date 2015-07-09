package de.huberlin.wbi.cuneiform.core.funsem;

public class Enumerator {

	public static Alist<Amap<String, Alist<Expr>>> enumerate( Sign sign,
			Amap<String, Alist<Expr>> bindingMap ) {

		Alist<Param> paramList;
		Alist<Amap<String, Alist<Expr>>> comb;
		Alist<Expr> value;
		String paramName;
		Amap<String,Alist<Expr>> m;
		Alist<Expr> l;

		if( !sign.getCorrelParamVec().isEmpty() )
			throw new IllegalArgumentException(
					"Cannot enumerate correlated signature." );

		paramList = sign.getInParamVec();
		comb = new Alist<>();

		if( paramList.isEmpty() ) {

			comb = comb.add( new Amap<String, Alist<Expr>>() );
			return comb;
		}

		for( Param param : paramList ) {
			
			paramName = param.getName();
			value = bindingMap.get( paramName );
			
			if( comb.isEmpty() ) {
				
				for( Expr v : value.reverse() ) {
					
					l = new Alist<>();
					l = l.add( v );
					
					m = new Amap<>();
					m = m.put( paramName, l );
					
					comb = comb.add( m );
				}
					
			}
			else {
				throw new UnsupportedOperationException( "NYI" );
			}
		}
		
		return comb;
	}

}
