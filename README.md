# agent-example 
agent-example provides an example on how to use javaagent with Spring Boot.
This relates to [Spring Boot issue 4868](https://github.com/spring-projects/spring-boot/issues/4868)
, which has been fixed in Spring Boot v1.4

To run this example execute 
```
script/start_from_exec_jar.sh
```

and you should see the following:

```
... projects build building ...
HttpTrace{url='GET http://www.ifeng.com/', statusCode=200, cost=921, e=null}
HTTP/1.1 200 OK
HttpTrace{url='GET http://www.ifeng.com/', statusCode=200, cost=1983, e=null}
HTTP/1.1 200 OK
```
 
