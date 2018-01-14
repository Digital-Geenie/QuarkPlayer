# Quark Player

Quark Player is a music app that loads the audio songs from a pre-defined REST endpoint. This project is a way to showcase how media files can be fetched from a REST endpoint and then displayed or used in an Android app.

- Features 
1. Login via Firebase (A Google cloud). This helps us in avoiding the boilerplate code of authenticating a user; Firebase does that for us. Among the various login methods preset in Firebase's kitty, this project uses following - Google, Facebook, Phone Number and Email/Password.
2. Complete material design using the RecyclerView and CardView to display the list of songs and their metadata.
3. A user can also create his/her playlist including the songs he/she likes.
4. A user can also search a particular song from the search bar on the home screen using either the song's name or artist(s)' name. This is an AJAX like search i.e. based on characters entered in the search bar, the app prompts to show results.

- Technology Stack
1. Android
2. Java
3. Firebase

- Usage Policy
You can clone this repository for your use. There is no licence needed. It is completely free to use.

- Note
This project contains all the source files minus google-services.json file which needs to be added under the /apps directory. One also needs to put their Facebook app id and app secret in the AndroidManifest.xml file.
