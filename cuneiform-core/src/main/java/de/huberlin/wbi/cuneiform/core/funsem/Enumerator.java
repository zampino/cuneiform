package de.huberlin.wbi.cuneiform.core.funsem;

public class Enumerator {

	public static Alist<Amap<String, Alist<Expr>>> enumerate( Sign sign,
			Amap<String, Alist<Expr>> bindingMap ) {

		Param[] paramVec;
		Alist<Amap<String, Alist<Expr>>> comb;
		Alist<Expr> value;
		String paramName;
		Amap<String,Alist<Expr>> m;
		Alist<Expr> l;

		if( sign.getCorrelParamVec().length > 0 )
			throw new IllegalArgumentException(
					"Cannot enumerate correlated signature." );

		paramVec = sign.getInParamVec();
		comb = new Alist<>();

		if( paramVec.length == 0 ) {

			comb = comb.add( new Amap<String, Alist<Expr>>() );
			return comb;
		}

		for( Param param : paramVec ) {
			
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
				// TODO
			}
		}
		
		return comb;
	}

}
