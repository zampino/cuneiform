package de.huberlin.wbi.cuneiform.core.funsem;

import java.util.List;
import java.util.function.BiFunction;

/*
 * A is the type of the elements of the target list
 * B is the type of the accumulator
 */
public class FunOp<A,B> {

	public B foldl( BiFunction<A,B,B> fun, B acc0, List<A> list ) {
		// TODO
		return null;
	}

}
