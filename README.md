# PhoneExercise
 
Steps to Run:
1) make sure port 8082 is available
2) download the project into your machine
3) unzip and run -> **mvn clean install**
4) run -> **mvn spring-boot:run**
5) Access -> http://localhost:8082/h2-console
6) populate JDBC URL with **jdbc:h2:mem:phoneapp**
7) Hit POST request http://localhost:8082/api/users
   body: **{
   "userName":"john_user",
   "password":"john_user",
   "emailAddress":"test123@gmail.com",
    "preferredPhoneNumber":"+353881234567"
}**
8) Hit POST http://localhost:8082/api/users/authenticate
  body: {
    "username":"john_user",
    "password":"john_user"
}
9) you will get jwt token
10) pass the jwt token in header to access below rest api
  1) Delete USER : DELETE http://localhost:8082/api/users/{userid}
  2) GET all Users: GET http://localhost:8082/api/users
  3) Add phone to user: POST http://localhost:8082/api/phones/users/{userId}
     Example body: {
         "phoneName":"John's Pixel",
         "phoneModel":"IPHONE",
         "phoneNumber":"+353881234567"
      }
 4) Delete user phone: DELETE http://localhost:8082/api/phones/{phoneId}/users/{userId}
 5) Get all phones by userid: GET http://localhost:8082/api/phones/users/{userId}
 6) Update preferredPhoneNumber: Patch http://localhost:8082/api/users/{userId}
   
