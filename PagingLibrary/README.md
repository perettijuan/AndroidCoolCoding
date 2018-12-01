<h2>Paging Library demo and exploration</h2>
<p> This project contains a demo of the <a href="https://developer.android.com/topic/libraries/architecture/paging/">Paging Library.</a> 
The goal is to understand the basic behavior and have an example of implementation to use in other projects.
</p>

<h3>Some questions to be answered</h3>

<p>1 - How can we integrate the Paging Library with Clean Architecture?</p>

<p>2 - Can we use Kotlin Corountines with the library? Makes sense to use it?</p>

<h3>Check <a href="https://github.com/perettijuan/android_cool_coding/blob/master/PagingLibrary/app/developer-notes">developer-notes</a> for the answers</h3>

<h3>What I like</h3>

<p>The library is very simple to use and does it works. You can forget about the hard work to have a good paging experience.
Adding the components needed to implement it is pretty straightforward.</p>

<h3>What I dislike</h3>
<p>It is not easy to integrate with other patterns (like Clean Architecture). It has a higly coupling with the Android
components that are used to show a list. I'm not sure if this is a problem or not (it doesn't seems to be). </p>
