package de.huberlin.wbi.cuneiform.core.libfun;

public interface Term {
	public boolean unify( Term other );
	public String print();
	public void unspecialize();
}
