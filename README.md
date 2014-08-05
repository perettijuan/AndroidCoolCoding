<h1>android_cool_coding</h1>

My own repository for cool stuffs in Android


<h3>android_sync_service_library</h3>
<p>This is a library used to execute background operations in a very simple way. It's based on the IntentService implementation of the Android SDK, with the singularity of allowing multiple requests to run in parallel. As the IntentService implementation, this Service spreads a new thread (as many as requested for every independent request) and executes the task in that thread. Once that the work is done, the client is notifyed using a ResultReceiver that is passed as parameter in the request. I will add an example of usage in some future.</p>


