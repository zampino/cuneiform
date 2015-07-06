package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.ArrayList;
import java.util.List;

public class Enumerator {
	
	public static final ImmutableMap<String,Expr[]> EMPTY_MAP = new ImmutableMap<>();

	public static List<ImmutableMap<String, Expr[]>> enumerate( Sign sign,
			ImmutableMap<String, Expr[]> bindingMap ) {

		Param[] param;
		
		if( sign.getCorrelParamVec().length > 0 )
			throw new IllegalArgumentException(
					"Cannot enumerate correlated signature." );

		param = sign.getInParamVec(); 
		
		if( param.length == 0 )
			return new ArrayList<ImmutableMap<String, Expr[]>>() {
				private static final long serialVersionUID = -3820777543169977749L;
				{
					add( EMPTY_MAP );
				}
			};

		return null;
	}
	



}
