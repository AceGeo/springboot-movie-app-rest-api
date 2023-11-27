n this project, Spring Boot is used entirely, along with Spring Security.

Initially, this project includes a Movie_App, where the CRUD methodology has been implemented to allow users, with appropriate authorization as an Admin, to perform actions such as get, getAll, post, update, and delete. Otherwise, as a regular user, only the get operation is allowed.

This authorization scheme applies not only to movies but also to categories, which are connected to movies. Subsequently, all users have the ability to review any desired movie.

The database used is MySQL.

Additionally, a Login and Register functionality has been created, providing the ability to validate users through token creation. This token is then used for authentication and authorization.

Finally, appropriate documentation has been generated using Spring Open API (commonly known as Swagger).
