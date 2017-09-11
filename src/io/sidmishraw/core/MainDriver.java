/**
 * Project: TestingLambdaSerialization
 * Package: io.sidmishraw.core
 * File: MainDriver.java
 * 
 * @author sidmishraw
 *         Last modified: Sep 10, 2017 7:09:52 PM
 */
package io.sidmishraw.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

/**
 * @author sidmishraw
 *
 *         Qualified Name: io.sidmishraw.core.MainDriver
 *
 *         The main driver of the program
 */
public class MainDriver {
	
	/**
	 * Creates the serialized object
	 * 
	 * @param filePath
	 *            the file path to the generated serialized object file
	 */
	private static void createSerializedObject(String filePath) {
		
		StandardLambdaObject sObject = new StandardLambdaObject();
		
		sObject.setObjectId("Sid#0001");
		sObject.setLamdaImplementation(() -> {
			
			System.out.println("FROM Inside Lambda:: Sid#001's lambda is executing");
		});
		
		System.out.println("Writing the object to file");
		
		try (ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream(Paths.get(filePath).toFile()))) {
			
			oStream.writeObject(sObject);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Wrote the object to file! Now sleeping for 30s");
		
		try {
			
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Consumes the serialized object
	 * 
	 * @param filePath
	 *            the file path to the serialized object file
	 */
	private static void consumeSerialzedObject(String filePath) {
		
		try {
			
			System.out.println("Waiting 1 minute before reading in the serialized object");
			
			// first sleep for 60s, giving enough time for the creator to create
			// the serialized object.
			Thread.sleep(60000);
			
			System.out.println("Starting!...");
			
			try (ObjectInputStream oStream = new ObjectInputStream(new FileInputStream(Paths.get(filePath).toFile()))) {
				
				StandardLambdaObject sObject = (StandardLambdaObject) oStream.readObject();
				
				System.out.println("Read in the object with the object Id:" + sObject.getObjectId());
				
				System.out.println("Executing the object's lambda");
				
				sObject.getLamdaImplementation().execute();
				
				System.out.println("Lambda execution complete");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 *            the command line args
	 */
	public static void main(String[] args) {
		
		System.out.println("Args length = " + args.length);
		
		if (args.length < 2) {
			
			System.err.println("Need a switch");
			System.exit(1);
		}
		
		// object serialization creator
		if (args[0].equalsIgnoreCase("cr")) {
			
			createSerializedObject(args[1]);
		} else if (args[0].equalsIgnoreCase("co")) {
			
			consumeSerialzedObject(args[1]);
		} else {
			
			System.err.println("Only `cr` and `co` switches are supported");
			System.exit(1);
		}
		
		System.out.println("Done!");
	}
	
}
