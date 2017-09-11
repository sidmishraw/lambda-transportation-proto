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