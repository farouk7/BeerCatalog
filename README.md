# BeerCatalog
##**1. IDE:**
    _INTELIJJ IDEA_

##**2. Database:**
    _Postgresql_ 

##**3. WebService:**
    _rest_

##**5. Framework:**
    spring-Boot
 
##**6. Depoloy:**

**6.1 Dockerfile**
 
     docker build -t springio/BeerCatalog .
     COPY command the project JAR file into the container 
    
##**7. Security type:**

**7.1 Jwt token authentication**

        _it take 2 minutes to expired._

##**8. Endpoint:**

**8.1 Endpoint authentication:**

    http://localhost:8081/user/authenticate
    use autorization->bearer to Input token.

**8.2 endpoint Beers:**

    `_http://localhost:8081/Beers ->[GET{id},PUT{object},POST{object},DELETE{id}]_`

**8.3 endpoint Manufacturer:**
    
    _http://localhost:8081/manufactuer ->[GET{id},PUT{object},POST{object},DELETE{id}]_


##**9. Objects**
**9.1 user Json:**

    
`[  {
    "email":"admin@user.com",
    "password":"testadmin"
] }`


**9.2 beer Json:**

`[{
        "id": 3,
        "name": "alhambra",
        "graduation": "6",
        "type": "rubia",
        "description": "riquisima",
        "manufacturerByManufacturerId": {
            "id": 1,
            "name": "Mahou",
            "nacionality": "Espana"
        }
}]`

**9.3 manufacturer Json:**

`[{
            "id": 1,
            "name": "Mahou",
            "nacionality": "EspaÃ±a"
}]`