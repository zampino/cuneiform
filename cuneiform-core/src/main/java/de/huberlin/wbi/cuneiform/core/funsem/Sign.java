package de.huberlin.wbi.cuneiform.core.funsem;

public class Sign {

	private final Alist<Param> correlParamVec;
	private final Alist<Param> inParamVec;
	private final Alist<Param> outParamVec;
	
	public Sign( Alist<Param> outParamVec, Alist<Param> correlParamVec, Alist<Param> inParamVec ) {
		
		if( outParamVec == null )
			throw new IllegalArgumentException( "Output parameter vector must not be null." );
		
		if( outParamVec.isEmpty() )
			throw new IllegalArgumentException( "Output parameter vector must not be empty." );
		
		if( correlParamVec == null )
			throw new IllegalArgumentException( "Correlated parameter vector must not be null." );

		if( inParamVec == null )
			throw new IllegalArgumentException( "Input parameter vector must not be null." );
		
		this.correlParamVec = correlParamVec;
		this.inParamVec = inParamVec;
		this.outParamVec = outParamVec;
	}

	public Alist<Param> getCorrelParamVec() {
		return correlParamVec;
	}

	public Alist<Param> getInParamVec() {
		return inParamVec;
	}

	public Alist<Param> getOutParamVec() {
		return outParamVec;
	}

}
