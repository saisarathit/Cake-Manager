# Cake-Manager
Requirements:
* By accessing the root of the server (/) it should be possible to list the cakes currently in the system. This must be presented in an acceptable format for a human to read.

* It must be possible for a human to add a new cake to the server.

* By accessing an alternative endpoint (/cakes) with an appropriate client it must be possible to download a list of
the cakes currently in the system as JSON data.

* The /cakes endpoint must also allow new cakes to be created.

Implementation:
* This application is implemented using Spring Boot with Restful Web Services.
* Available Rest end points are /waracle is context path, /(root url), /cakes, /oauth.
* OAuth is implemented for a url to demonstrate OAuth functionality.
* Junit test cases are implemented.
* Docker containerisation is implemented.

Rest End points Usages:
Context path is configures as /waracle
Note: Please prefix /waracle for all end points. Ex: localhost:<port>/waracle/<endpoints>

* / (root url, Get request) : This request list out all cakes information available in current system.
   
   -> Ex: http://localhost:8083/waracle/
* /cakes(Get Request): This request downloads a file with list of cakes currently in the system as JSON data. This has optional query parameter to specify file name.
    * If no query parameter specified then file will be downloaded with default filename as cakes.json
   
      --> Ex: http://localhost:8083/waracle/cakes
    * If file name is provided then file will be downloaded with given name
   
      Ex: http://localhost:8083/waracle/cakes?fileName=cakesList.json
* /cakes(Post Request): This request is used to store new cakes. Cake details should be provided in Request body.
   
    Ex: http://localhost:8083/waracle/cakes 
   
      Request Body
      -----------
      {
        "title": "BlueBerry Cake",
        "description": "BlueBerry Cake is made of Blueberries",
        "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
      }
      
For all the above urls OAuth is disabled.
* /oauth : GitHub OAuth functionality is added for this url. Page will be redirected to GitHub login page by requesting this url.
   
     * Ex:http://localhost:8083/waracle/oauth

   
 Docker:
   * Dockerfile is available in project.
   * Prerequisite: Docker should be installed to use this feature
   * Docker image can be created using below command
   
        * docker build -t <image-name> <dockerfile-location> 
   * Once Docker image is created successfully, it can be run using below command
   
        * docker run --name cakecontainer cake-manager : This command starts the application
