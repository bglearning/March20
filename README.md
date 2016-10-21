# March20 - The Beginning of Spring

A simple example [Spring-boot](https://projects.spring.io/spring-boot/) 
application which provides a very basic RESTful web service. 

## Requirements

* JDK 1.8 or later
* Gradle 2.3+
* mongodb 3.0+

## Building the application

Run `./gradlew build` to build the application.

## Running the application

Note: Make sure `mongod` service is running.

```
java -jar build/libs/march20-0.1.0.jar
```

The server is now up and running.

Check it out:

```
$ curl http://localhost:8080
{
    "_links" : {
        "thoughts" : {
            "href" : "http://localhost:8080/thoughts{?page,size,sort}",
                "templated" : true
        },
            "profile" : {
                "href" : "http://localhost:8080/profile"
            }
    }
}
```

The application provides a RESTful API for `thoughts`:

```
$ curl http://localhost:8080/thoughts
{
    "_embedded" : {
        "thoughts" : [ ]
    },
        "_links" : {
            "self" : {
                "href" : "http://localhost:8080/thoughts"
            },
            "profile" : {
                "href" : "http://localhost:8080/profile/thoughts"
            },
            "search" : {
                "href" : "http://localhost:8080/thoughts/search"
            }
        },
        "page" : {
            "size" : 20,
            "totalElements" : 0,
            "totalPages" : 0,
            "number" : 0
        }
}
```
