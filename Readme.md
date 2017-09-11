### Expriment to check the serialization of lambdas in Java

Moving the functionality around with the objects.

To run the simulation:

`java -jar test_lambda.jar cr <object-name>`

Creator:
```
Sidharths-MBP:Desktop sidmishraw$ java -jar test_lambda.jar cr ob1.pp
Args length = 2
Writing the object to file
Wrote the object to file! Now sleeping for 30s
Done!
Sidharths-MBP:Desktop sidmishraw$
``` 

`java -jar test_lambda.jar co <object-name>`

Consumer:
```
Sidharths-MBP:Desktop sidmishraw$ java -jar test_lambda.jar co ob1.pp
Args length = 2
Waiting 1 minute before reading in the serialized object
Starting!...
Read in the object with the object Id:Sid#0001
Executing the object's lambda
FROM Inside Lambda:: Sid#001's lambda is executing
Lambda execution complete
Done!
Sidharths-MBP:Desktop sidmishraw$
```


## Update#1

Using the embedded Javascript engine to transport logic for the various agents/objects seems to be a better and more powerful option than serializing and deserializing lambdas.

To illustrate this, lets refer to the script engine invocation in `io.sidmishraw.core.MainDriver.scriptIt` method. It uses the Nashhorn JS embedded engine to execute the JS script.

The `StandardLambdaObject` takes reads in the contents of the script from a file named `lamda.js` and saves it inside as a strin which is then saved in the serialized object file.

When the other process reads from the object file, it can get the script from the object and send it to the embedded engine to execute the script and doing what was expected from the lambda.

Sample output:
```
sidmishraw@Sidharths-MacBook-Pro ~/Desktop> 
java -jar test_lambda2.jar cr myLambda3
Args length = 2
Testing out ScriptEngineClass
Writing the object to file
Wrote the object to file! Now sleeping for 30s
Done!
sidmishraw@Sidharths-MacBook-Pro ~/Desktop> 
java -jar test_lambda2.jar co myLambda3
Args length = 2
Testing out ScriptEngineClass
Waiting 1 minute before reading in the serialized object
Starting!...
Read in the object with the object Id:Sid#0001
This is being called from within the script with the message:: Hey there from within my JS script I say hello world!
Over and out!



Lambda execution complete
Done!
sidmishraw@Sidharths-MacBook-Pro ~/Desktop> 
```

   