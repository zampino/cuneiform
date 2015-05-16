package de.huberlin.wbi.cuneiform.core.libfun;

public class Nil extends List {

	public static final Nil NIL = new Nil(); 
	
	private Nil() {}

	@Override
	public boolean unify( Term other ) {
		return this == other;
	}

	@Override
	public String print() {
		return "[]";
	}

	@Override
	public void unspecialize() {}

	@Override
	public Term getHead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getTail() {
		// TODO Auto-generated method stub
		return null;
	}
}
