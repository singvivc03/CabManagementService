### CabManagementService
An inter-city cab management portal to be used as an admin and booking tool

### Design strategy
Each package define can independently be taken as one microservice where that microservices will handle all work related
to that domain properly.

- [Booking](src/main/java/com/cab/management/booking) : Handles the search and match of the cab with the Rider
- [OnBoarding](src/main/java/com/cab/management/onboarding) - handles the onboarding/launching of the services.
- [Pricing](src/main/java/com/cab/management/pricing) : Handles the price calculation of the end trip
- [Registration](src/main/java/com/cab/management/registration) : Registration of new cab
- [Trip](src/main/java/com/cab/management/trip) : Handles the start and end of the trip
- [User](src/main/java/com/cab/management/user) : Handles the finding and registering of new user

### Tools and Java version
Used gradle version [6.8](https://docs.gradle.org/6.8.1/userguide/installation.html) for building the project
Used java version [11](https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html) for development

### How the changes are tested
All changes are compiled and tested by adding the Junit.