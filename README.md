# Just an example of an Http Server

## What is an Http Server, anyway?
Well, when you're surfing the web you kinda send packages across the internet and some server receives it, reads it and say "Ok, you're good here" and then send to your destination.

## But what does HTTP has to do with it? 

HTTP is just a protocol, and like any other protocol, it stands to keep things in order and facilitate communication across clients and servers, using action verbs, such as: get, post, delete, put, patch, and providing a code information with it. 

Along with it, HTTP uses headers for requests and responses, providing every information a request accepts for that specific response. It's like you're ordering a pizza and saying your request to the waiter, with the tops and layers you want in your pizza. If the restaurant has it, the waiter will bring it to you, but if it doesn't the waiter will inform that your request couldn't be processed. Either way you get your response. 

The image below sums all of it:


![image](https://user-images.githubusercontent.com/66542266/199606145-3bf3b874-9f6c-4a21-90be-0dfd32e4f4c7.png)


## Nice, but how to do it with Java?
Java has nice objects for http, it also has built-ins autheticators, HTTPS, SSL, communication via websockets and so on. There's a <strong>VAST</vast> library provided by Sun, introduced to JDK since Java 9 and 11 for better working http clients. 

It's not so much productive to work with, and Oracle itself says it's only for educational purposes, but since you grasp on it, you'll become to understand how things works behind the curtains. 

For server's implementation you can follow the documentation: https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpServer.html

And for client's implementation you can also follow the documentation: https://docs.oracle.com/en/java/javase/12/docs/api/java.net.http/java/net/http/HttpClient.html

## How to test it?
Well, since it's just an educational project, you can test it with JUnit, just pressing the test button on your IDE. 

The project's not finished, and it was for educational purposes, but i'll come back to it soon. :)
