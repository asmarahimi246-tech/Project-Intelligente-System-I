## Application specific error handling guidelines
read this for general guidelines: 
[General](https://medium.com/@AlexanderObregon/effective-error-handling-in-java-strategies-and-best-practices-b64226c9970b)

### Game
This is for everything in the games folder.
Game specific guidelines:
 - Dont interupt the game thread (the user chooses when the game session/thread ends)
 - Throw an descriptive unchecked exception when the game or builder cant recover from an error
