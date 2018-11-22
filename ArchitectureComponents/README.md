<h2>ArchitectureComponents demo and exploration</h2>
<p> This project contains a demo of <a href="https://developer.android.com/topic/libraries/architecture/viewmodel/">View Models</a> 
and <a href="https://developer.android.com/topic/libraries/architecture/livedata/">Live Data</a>.
This project aims to have a referencial architecture to be used in other projects.
</p>

<h3>Some questions to be answered</h3>

<p>1 - How can we use ViewModels with Kotlin Coroutines?</p>

<p>2 - How can we use Dagger to inject the ViewModels?</p>

<h3>Check <a href="https://github.com/perettijuan/android_cool_coding/blob/master/ArchitectureComponents/developer-notes">developer-notes</a> for the answers</h3>

<h3>References</h3>

<h4>ModelViewIntent</h4>
<p>To define the architecture of the ViewModel and how does the different components interact with each other, keeping 
all modules independent and following the single responsibility principle, I've based my architecture in 
<a href="http://hannesdorfmann.com/android/mosby3-mvi-2">this blog<a/> about MVI.
Even though I didn't follow the blog exactly as it is, I did extracted some ideas in order to adapt MVI to the
Architecture Components.
The biggest difference between my implementation and the one in the blog is that I don't use RxJava in my example.
Why? Simply because I'm trying to write functional code without using Reactive, just purely Kotlin code.</p>

<h4>Dagger</h4>
- <a href="https://proandroiddev.com/exploring-the-new-dagger-android-module-9eb6075f1a46">Dagger2 introduction</a></br>
- <a href="https://medium.com/@malinitin/setup-dagger-2-11-on-kotlin-project-2257ad84ad7c">Dagger 2 with Kotlin</a></br>
- <a href="https://proandroiddev.com/viewmodel-with-dagger2-architecture-components-2e06f06c9455">VideModels with Dagger2</a></br>
