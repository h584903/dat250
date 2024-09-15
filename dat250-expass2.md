# Rapport for assignment 2 in class DAT250
Quite late to this task, as I've been sick for most parts of last week.
Therefore I've not done any of the optional tasks, and the API might still need some fleshing out.

## Step 1
I started the task on the basic program that was made in the last one.
Simply making a new repository on github, cloning that
and adding all the code from the old task into the new one.

## Step 2
As for step 2 I added a new folder in the project called domains to add the PollApp.java file
for the domains.
As for the PollManager, I added that to a new components folder.

## Step 3
This is where I started to get some of my first troubles.
I decided on a lightweight approach so I wanted to use HTTPie. And It seemed to be working
However after setting up the endpoints, I couldn't get it to respond to them.
I then realised a bit later that I couldn't run the HTTPie in a different shell than the base app.
So as soon as I started starting the app and editing it in the same Shell instance it worked.
But it took some time to figure out as I've just started to mess with containers.

The testing was done with the commands
```
http POST localhost:8080/newUser username=tester email=tester@gmail
http GET localhost:8080/listUsers
http POST localhost:8080/newUser username=tester2 email=tester2@gmail
http GET localhost:8080/listUsers
http POST localhost:8080/createPoll?username=tester <./data.json
http GET localhost:8080/listPolls
http POST localhost:8080/{pollId}/vote?username=tester&voteOptionId=1
```

At the time of writing I'm still missing deleting and changing votes, but it should be easy to fix
when I get time. // This is now fixed, and even added to automated testing

## Step 4
After testing with HTTPie, and setting it up I started with setting up the controllers.
These were honestly pretty easy to set up as I've done similar assignments in earlier courses.
I made a directory for the controllers and made one for the users and one for the polls.
After that I adde the appropriate API endpoints.

## Step 5
Automating testing is the most difficult step, as I went into it trying to figure out RESTClient.
I couldn't manage to get it working so ended up just using mockMvC that I was seeing others using.
mockMvC function mostly like I figured the RestClient were supposed to. So got it working after a while.
Still had some problems on for example APPLICATION_JSON not being used correctly.
