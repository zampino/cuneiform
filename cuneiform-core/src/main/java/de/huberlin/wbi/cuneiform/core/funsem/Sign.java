package de.huberlin.wbi.cuneiform.core.funsem;

public class Sign {

	private final Param[] correlParamVec;
	private final Param[] inParamVec;
	private final Param[] outParamVec;
	
	public Sign( Param[] outParamVec, Param[] correlParamVec, Param[] inParamVec ) {
		
		if( outParamVec == null )
			throw new IllegalArgumentException( "Output parameter vector must not be null." );
		
		if( outParamVec.length < 1 )
			throw new IllegalArgumentException( "Output parameter vector must not be empty." );
		
		if( correlParamVec == null )
			throw new IllegalArgumentException( "Correlated parameter vector must not be null." );

		if( inParamVec == null )
			throw new IllegalArgumentException( "Input parameter vector must not be null." );
		
		this.correlParamVec = correlParamVec;
		this.inParamVec = inParamVec;
		this.outParamVec = outParamVec;
	}

	public Param[] getCorrelParamVec() {
		return correlParamVec;
	}

	public Param[] getInParamVec() {
		return inParamVec;
	}

	public Param[] getOutParamVec() {
		return outParamVec;
	}

}
