## Javawockeez

# E-Commerce - Project 2

The e-commerce is themed around selling baked goods. The following instructions tell how to set up the project.

### Back-end - Spring Server
---

The project's final server is contained in the directory
``` project-2-spring\commerce2 ```.

Because it is Maven Java project, the set up is:

1. Build the Maven dependencies in the pom.xml file

2. Because the project uses Stripe as the checkout payment api. In the ***application.properties*** file, add a property variable called ```stripe.api.key``` and set it equal to your secret key from your Stripe account.

3. In the ***application.yml*** file, set the datasource: 
* url 
* username 
* password 
* driver 

and the hibernate: 
* dialect

to set the connection of your database.

Afterwords, you should be able to build and run the backend Spring server.

### Back-end - PostgreSQL database
---
We utilized a postgres database for the project. If you wish to do the same or in another SQL dialect, look at (and run) the ***CommerceProject_Builder.sql*** script for set up.

If you have your own Stripe Account, create and use your product's price key from Stripe similar to how the products in the products table is like.

Also be sure the database being used, is set in the Spring appication as mentioned previously.

### Front-end - React application
---
The frontend application is a typescript React application; however, setting it up is similar to javascript React project. This is location in the ***project-2-react-app*** directory.

React applications use ```npm``` to install additional libraries if there's something someone wants to add because the default template. Similarly to Maven, the library dependencies are added into the ***package.json*** file.

The set up is then:

1. Run in the terminal ```npm install``` so **npm** can install and stored the library modules in the ***node_modules*** folder

2. Afterward, install the react star dependency with ```npm i react-rating-stars-component```

3. Now you should be able to run the application using ```npm start``` in the terminal
