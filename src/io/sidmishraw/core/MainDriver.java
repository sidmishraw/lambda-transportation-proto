/**
 * Project: TestingLambdaSerialization
 * Package: io.sidmishraw.core
 * File: MainDriver.java
 * 
 * @author sidmishraw
 *         Last modified: Sep 10, 2017 7:09:52 PM
 */
package io.sidmishraw.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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
		
		// java8 lambda
		// sObject.setLamdaImplementation(() -> {
		//
		// System.out.println("FROM Inside Lambda:: Kangaroo!#001's lambda is
		// executing");
		// });
		
		StringBuffer scriptContentBuffer = new StringBuffer();
		
		try (BufferedReader bReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(Paths.get("lamda.js").toFile())))) {
			
			String line = null;
			
			while (null != (line = bReader.readLine())) {
				
				scriptContentBuffer.append(line);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		// set the js script as the new lambda as a transport(?)
		sObject.setLambdaJSScript(scriptContentBuffer.toString());
		
		// StringBuffer sBuffer = new StringBuffer();
		//
		// sBuffer.append("function displayTo(msg){");
		// sBuffer.append("print('From inside the JS Script:: ' + msg)");
		// sBuffer.append("}");
		// sBuffer.append("");
		// sBuffer.append("displayTo('" + "objectId :: " + sObject.getObjectId()
		// + "')");
		//
		// // using the JS script to define behavior
		// sObject.setLambdaJSScript(sBuffer.toString());
		
		System.out.println("Writing the object to file");
		
		try (ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream(Paths.get(filePath).toFile()))) {
			
			oStream.writeObject(sObject);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Wrote the object to file! Now sleeping for 30s");
		
		// try {
		//
		// Thread.sleep(30000);
		// } catch (InterruptedException e) {
		//
		// e.printStackTrace();
		// }
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
			// Thread.sleep(60000);
			
			System.out.println("Starting!...");
			
			try (ObjectInputStream oStream = new ObjectInputStream(new FileInputStream(Paths.get(filePath).toFile()))) {
				
				StandardLambdaObject sObject = (StandardLambdaObject) oStream.readObject();
				
				System.out.println("Read in the object with the object Id:" + sObject.getObjectId());
				
				// System.out.println("Executing the object's lambda");
				//
				// try {
				//
				// sObject.getLamdaImplementation().run();
				// } catch (Exception e) {
				//
				// e.printStackTrace();
				// }
				
				scriptIt(sObject.getLambdaJSScript());
				
				System.out.println("Lambda execution complete");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Evaluates the script
	 * 
	 * @param script
	 *            the script to be executed
	 * @throws ScriptException
	 * 
	 * @return the result of the evaluation
	 */
	private static Object scriptIt(String script) throws ScriptException {
		
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		
		// list of all registered scripting engines supported by default
		// Oracle Nashhorn for javascript
		// scriptEngineManager.getEngineFactories().forEach(fac -> {
		//
		// System.out.println(fac.getEngineName());
		// System.out.println(fac.getLanguageName());
		// });
		
		ScriptEngine jsEngine = scriptEngineManager.getEngineByName("js");
		
		Object result = jsEngine.eval(script);
		
		return result;
	}
	
	/**
	 * @param args
	 *            the command line args
	 */
	public static void main(String[] args) {
		
		System.out.println("Args length = " + args.length);
		
		System.out.println("Testing out ScriptEngineClass");
		
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
