/**
 * Project: TestingLambdaSerialization
 * Package: io.sidmishraw.core
 * File: StandardLambdaObject.java
 * 
 * @author sidmishraw
 *         Last modified: Sep 10, 2017 7:06:10 PM
 */
package io.sidmishraw.core;

import java.io.Serializable;

/**
 * @author sidmishraw
 *
 *         Qualified Name: io.sidmishraw.core.StandardLambdaObject
 *
 *         This is a standard class that will make an object which will be
 *         needing a lambda(function) for testing some functionality.
 */
public class StandardLambdaObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;
	
	/**
	 * Object ID
	 */
	private String					objectId;
	
	/**
	 * Default implementation
	 */
	private StandardConsumingLamda	lamdaImplementation;
	
	private String					lambdaJSScript;
	
	/**
	 * @return the lambdaJSScript
	 */
	public String getLambdaJSScript() {
		return this.lambdaJSScript;
	}
	
	/**
	 * @param lambdaJSScript
	 *            the lambdaJSScript to set
	 */
	public void setLambdaJSScript(String lambdaJSScript) {
		this.lambdaJSScript = lambdaJSScript;
	}
	
	/**
	 * 
	 */
	public StandardLambdaObject() {
		
		this.objectId = "";
		this.lamdaImplementation = () -> {};
	}
	
	/**
	 * @return the objectId
	 */
	public String getObjectId() {
		return this.objectId;
	}
	
	/**
	 * @param objectId
	 *            the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * @return the lamdaImplementation
	 */
	public StandardConsumingLamda getLamdaImplementation() {
		return this.lamdaImplementation;
	}
	
	/**
	 * @param lamdaImplementation
	 *            the lamdaImplementation to set
	 */
	public void setLamdaImplementation(StandardConsumingLamda lamdaImplementation) {
		this.lamdaImplementation = lamdaImplementation;
	}
	
	/**
	 * Just a method that is meant to be called from a JS script, to simulate
	 * the transport of lambdas(:D)
	 * 
	 * @param message
	 *            the message
	 */
	public void toBeCalledFromWithMyScript(String message) {
		
		System.out.println("This is being called from within the script with the message:: " + message);
		System.out.println("Over and out!\n\n\n");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StandardLambdaObject [objectId=" + this.objectId + ", lamdaImplementation=" + this.lamdaImplementation
				+ "]";
	}
}
