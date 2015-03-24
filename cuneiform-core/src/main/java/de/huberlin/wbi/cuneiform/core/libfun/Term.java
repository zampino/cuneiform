package de.huberlin.wbi.cuneiform.core.libfun;

public abstract class Term {
	protected abstract boolean unify( Term other );
	protected abstract String print();
	protected abstract void unspecialize();
}
