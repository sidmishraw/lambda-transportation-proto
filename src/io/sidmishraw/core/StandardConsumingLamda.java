/**
 * Project: TestingLambdaSerialization
 * Package: io.sidmishraw.core
 * File: StandardConsumingLamda.java
 * 
 * @author sidmishraw
 *         Last modified: Sep 10, 2017 7:07:48 PM
 */
package io.sidmishraw.core;

import java.io.Serializable;

/**
 * @author sidmishraw
 *
 *         Qualified Name: io.sidmishraw.core.StandardConsumingLamda
 *
 */
@FunctionalInterface
public interface StandardConsumingLamda extends Serializable {
	
	public void execute();
}
