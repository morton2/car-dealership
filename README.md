# car-dealership

Entities:

• Car • Configuration • Customer

Car:

 Long id  String modelType;  Long serialNumber;

Configuration:

 Long id  Long trim;  Long engine;  Long interior;  Long exterior;  Long equipment;

Customer:

 Long id;  String name;  String placeOfBirth;  String nationality;  Date dateOfBirth;

## API endpoints

### car

• Retrieve all the cars stored in the system through (GET) /car endpoint.
• Update a car via JSON by its id, through (PUT) /car endpoint.
• Create a new car object via JSON, through (POST) /car endpoint.
• Delete a Car by its id, through (DELETE) /car/id endpoint.

### configuration

• Retrieve a configuration by its id, through (GET) /configuration/id endpoint.
• Update a configuration by its id via JSON, through (PUT) /configuration endpoint.
• Create a configuration via JSON, through (POST) /configuration endpoint.
• Delete a specific configuration by its id, through (DELETE) /configuration endpoint.


### customer
• Retrieve all the customers stored in the system, through (GET) /customer endpoint.
• Update a customer via JSON by its id, through (PUT) /customer endpoint.
• Create a new customer via JSON, through (POST) /customer endpoint.
• Delete a customer by its id, through (DELETE) /customer/id endpoint.