package de.huberlin.wbi.cuneiform.core.funsem;

public class Util extends Alist<Expr> {

	public static final Alist<Expr> NIL = new Alist<>();
	public static final Alist<Param> PARAMLIST = new Alist<>();
	public static final Amap<String, Alist<Expr>> EMPTY_MAP = new Amap<>();

	public static boolean isFinal( Alist<Expr> ce ) {

		if( ce.isEmpty() )
			return true;

		if( !ce.hd().isFinal() )
			return false;

		return isFinal( ce.tl() );
	}
	
	public static boolean isFinal( Amap<String,Alist<Expr>> map ) {
		
		for( Alist<Expr> v : map.values() )
			if( !isFinal( v ) )
				return false;
		
		return true;
	}
}
