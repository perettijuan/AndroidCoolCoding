<h2>NavigationComponents demo and exploration</h2>
<p> This project contains a demo of the <a href="https://developer.android.com/topic/libraries/architecture/navigation/">Navigation Component Architecture.</a> 
The goal is to understand the basic behavior and have an example of implementation to use in other projects.
</p>

<h3>Some questions to be answered</h3>

<p>1 - How can we show a navigation drawer to provide navigation to the user?</p>

<p>2 - How can we use a CollapsingToolbarLayout in order to provide the the Flexible Space Pattern?</p>

<p>3 - How can we perform Shared Element Transitions when going from one Fragmengt to another?</p>

<h3>Check <a href="https://github.com/perettijuan/android_cool_coding/blob/master/NavigationComponents1/developer-notes">developer-notes</a> for the answers</h3>

<h3>What I like</h3>

<p>Navigation is pretty straightforward: all you have to do is declare the navigation xml file, add the nodes you
want and then instantiate the Actions whenever you need to move from a Fragment to another Fragment. It is great
that you don't have to manage this manually.
Also, the way that you can pass data between Fragments is great. You don't need to be creating a Bundle manually
and configuring all of the things you need to pass. It is very declarative the way it is implemented in the navigation
xml file.</p>

<h3>What I dislike</h3>
<p>At the moment of this demo, the latest navigation library version available (1.0.0-alpha07) claims that it supports
Shared Element Transitions, but I couldn't have it working.</p>
