Every HTTP Request will send a few things:
Request
The specific URL we're sending the request to.
 - The host (youtube.com)
 - path parameters (eg id of the video you're currently on)
 - query parameters (eg the start time in the url)
Verb & the protocol used
 - GET 
 - HTTP 1.1
Headers
 - Metadata related to the request
   - Such as Security concerns
     - Any sort of private encrypted data
     - Information regarding what types of responses will be permitted
   - Information regarding any payloads/bodies in the request
     - The size of the body
     - The content type of the body
Potentially, a request may contain a body

Response
Status code
 - eg, 200 "OK", 404 "not found", 500 "server error"
Headers
 - Metadata such as
   - Security concerns
   - Information regarding any bodies in the response
 Potentially, a response may contain a body

User interact with -> Front-end Server (react) sends JSON to ->       Backend server      
     <- html from                              <- receives JSON from

REST

GET carapi.com/car/12345

{
    "make":"toyota",
    "model":"camry",
    "year":2007,
    "mpg":20
}

Verbs in a RESTful api
Verbs are not magical - they are just a way of organizing,
or differentiating, requests in a way that makes sense to
humans
GET (retrieve data)
POST (send data - creating a new item)
PUT (send data - overwrite an existing item)
PATCH (send data - partial update of an existing item)
DELETE (delete a specific item)

The URL in a RESTful API is used to identify the resource
that we're working with. (by resource, I could mean
'this specific car/pokemon/video' or also 'all cars/pokemon/video')

The Verb in a RESTful API is used to specify what we would like
to do with the resource (CRUD - create/read/update/delete)

The body in a RESTful API is usually JSON. (or XML)
JavaScript Object Notation - "a lightweight data 
interchange format" through comma-separated key/value pairs

The status code specifies how the Server has chosen to
respond to the request.
100 (informational)
200 (success)
    - 200 (generic OK)
        - a good status for when the response is sent as
            expected
    - 201 (resource created)
        - a good status for when something was 
            successfully created
300 (redirects)
400 (client errors)
    - 400 (generic client error)
        - a good status for when the sender of the request
            has made a mistake
    - 401 (unauthorized)
        - a good status for bad logins / the lack of a
            logged in or authenticated user
    - 403 (forbidden)
        - a good status for users without access 
            (eg admins only)
    - 404 (resource not found)
        - the item we're looking for can not be found
            this could either be
            - a url that the server does not know how
                to respond to
            - the specific resource we requested does
                not exist
500 (server errors)
    - 500 (generic server error)
        - The server has thrown an exception!

Cars API planning
I want to be able to insert a car and retrieve all cars

POST /car
-> should take in a request body
    the fields of the body (make, model, year, mpg)
    should not be null.
    if they are null, I use a 400 status code
    If they are not null, the car resource should be
    created, with status code 201

GET /car
-> should respond with a response body containing all cars
    should respond with a 200 status code

3rd endpoint - after adding a new field to 'car' named
VIN, I want to be able to retrieve a car by its VIN

GET /car/12345
GET /car/{vin}
-> should respond with a response body containing a single
    car with a matching VIN IF and only IF the VIN in the
    path parameter is valid
    - If no car matches the VIN, I use a 404 status code
    - If a car matches the VIN, I use a 200 status code
        with the car's JSON in the body

GET /car?make=toyota