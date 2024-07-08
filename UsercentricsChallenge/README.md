# UI Architecture
A very simplified version of MVI has been used to present the state of the UI. I have decided to use this architecture because I have experience with it and I think 
it goes pretty well with Jeptack Compose.

# Libraries used
- Android Startup: used to add an Initializer that takes care of initializing the Usercentrics SDK on app startup.
- All Jetpack Compose required libraries.
- Coroutines to perform tasks easily (not necessarily background tasks only).

# Probable improvements
- User consent state restore: the application could probably use the Usercentrics SDK to restore the state of the application (score) on app startup. I didn't implemented this just to not go beyond the scope of the requirement.
- Another probable improvement is around the algorithm used to find the costs for calculation coming from the CMP data. This algorithm, in large datasets, could have performance issues. A probable improvements is to use a Map to represent the `ServiceCost`, where the key is `templateId` and the value is the actual cost of that service, that way, the search becomes direct.  
