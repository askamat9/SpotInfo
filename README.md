# SpotInfo
SpotInfo cohesively informs users about places of interest near their current location.

## Running the app
Obtain a Google Maps API key (https://developers.google.com/maps/documentation/javascript/get-api-key) and replace all instances of BuildConfig.API_KEY with your unique key.

## Inspiration
This Android application was built as a summer project that was designed to allow me to learn more about Android development. I had previously worked on a rudimentary, two-player Tic-Tac-Toe app as well as XML layouts for a hackathon project, but I wanted to dig a little deeper with by commiting to a project that would require authentication, user permission, more complex XML layouts and fragments, API calls, and greater tinkering with gradle files.

## Building process
I first built the Sign up and login pages, using Firebase's authentication services to create and sign in new users. I then built the Map View by creating a fragment and using the Google Maps API. The List View was then created using the Places API. The last element I implemented was the Password recovery menu, again using the Firebase API to handle requests.

## What I learned
I learned how to connect my app to services such as Firebase and Google Cloud Platform, and use their APIs (authentication and maps/places respectively) to execute the major functions of the application. Additionally, this project showed me how valuable Stack Overflow is as a resource to fix
frustrating issues that I couldn't make sense of.

## Struggles
While writing the Java code that provided the base functionality of the app wasn't too difficult, I did experience some speedbumps throughout the course of the project when trying to make API calls. At first, when I was trying to code the app's login and sign up functionality using Firebase's authentication services, I had several version incompatability errors. However, these were all solved after a quick AndroidX migration. The most significant hurdle after that was trying to get nearby places to report their information as a user clicked on them. I realized that Google Maps' Point of Interest (POI) objects don't behave exactly the same way as Places objects. After studying the Google Maps and Places documentation, I changed the design of my app to include a Map View to quickly orient users and a List View to provide the actual information. This revision actually seemed to augment the user experience, as instead of having to click on different POIs each time to get their information, a list would instantly provide that information in a more cohesive way.

## Achievements
This was the first time I built a project that heavily implemented the use of APIs, had an authentication system, and changed views and updated information according to the user's current location. This project taught me a lot more about Android application architecture than I previously knew, and through reading documentation and working through problems using Stack Overflow, allowed me to kill certain bad coding habits (not extracting string resources, to name one) and learning/reinforcing good ones (e.g. not reinventing the wheel when frameworks and libraries exist that achieve what your are trying to accomplish.

## Next steps
I plan on taking on a a full-stack development project that will rely less on APIs to do so much heavy lifting and allow me to figure out how fundamental aspects of many online applications work.

