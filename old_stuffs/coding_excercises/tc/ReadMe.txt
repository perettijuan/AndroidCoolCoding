Code source explanation:

- The project is splitted into two separate packages: 
   * A UI package that contains all User Interface elements.
   * A background work package that contains all the Internet access classes along with the String processing utilities.


- The core of the app is an AsyncTask that executes a given request, retrieving the data from the Truecaller webpage and executing the
  algorithm for content processing that is requested on the start of the task. Once that the process is over, a callback method is invoked
  over a Listener object that is listening for events on the main application thread.

- When the button is pressed, a Fragment creates three AsyncTasks, each one of them with a different algorithm request, and it passes itself
  as listener for the events. The Fragment is marked as "not destroyable" (setRetainInstance(true)) in order to avoid an excesive coding to handle
  an Activity rotation.