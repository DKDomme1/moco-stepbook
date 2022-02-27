
# Table of Contents

1.  [Application Overview](#org0eaaa4e)
2.  [Implemented Features](#orgeb5f5e0)
3.  [Archiecture Overview](#orgd219a1b)
4.  [common package](#org123dd69)
    1.  [FirebaseUtil](#org99fba1b)
    2.  [User](#org57e7863)
5.  [training package](#orgecb11e4)
    1.  [LoginFragment](#orge90e500)
    2.  [adapters package](#orge19febe)
        1.  [CreateWorkoutAdapter](#org11922ae)
        2.  [ExercisesAdapder](#org1a98a9d)
        3.  [PublicWorkoutsAdapter](#org26dbedb)
        4.  [TrackedExerciseAdapter](#org3df3fa4)
        5.  [UserWorkoutsAdapter](#org27c35a9)
        6.  [ViewWorkoutAdapter](#orgb341527)
    3.  [data package](#org254e41e)
    4.  [fragments package](#org4bd353d)
        1.  [CreateWorkoutFragment](#org3ff5159)
        2.  [EnterExerciseDataDialogFragment](#org982263a)
        3.  [ExercisesFragment](#orgd69f039)
        4.  [PublicWorkoutsFragment](#org6a299d4)
        5.  [TrainingMenuFragment](#org613afff)
        6.  [UserWorkoutsFragment](#orgc3f8421)
        7.  [ViewExerciseFragment](#orgec1a3c9)
        8.  [ViewWorkoutFragment](#orge92f245)
    5.  [viewmodels package](#orgdf7936b)
        1.  [CreateWorkoutViewModel](#org6569d97)
6.  [progressGallery package](#org1ce496f)
    1.  [ProgressGallery.kt](#org9db13db)
    2.  [AddPhoto.kt](#orga7b2981)
    3.  [ChooseWeight.kt](#org7d04865)
    4.  [PhotoView.kt](#org6f422a4)
7.  [Stepcounter](#org0d8cb3b)
8.  [Required permissions](#org3b00cd4)
    1.  [CAMERA](#org5b12966)
    2.  [WRITE<sub>EXTERNAL</sub><sub>STORAGE</sub>](#org095a556)
    3.  [READ<sub>EXTERNAL</sub><sub>STORAGE</sub>](#org3fc8000)



<a id="org0eaaa4e"></a>

# Application Overview

Stepbook is an application which helps their users track their workout or diet progress by providing the interface for comparing past with current results.
It achieves this with the use of a progress gallery, where users can keep track of their body for differences, a step counter, where users can see how many steps they have taken since, and the option to enter their performance of an exercise which is then shown in a graph for visual comparison.
Stepbook also allows users to create and share workouts for quick reference.
This application would become useful when working out in the gym or outside where a notebook and pen would become unwieldly.
Stepbook uses the Camerax library to capture pictures without launching the camera app.
The weight is entered for the respective picture and the picture will be saved in a separate path with a name consisting of date and weight.
Stepbook uses the Gson library to convert the picture name and path into a JSON file.
These JSON files are then read and persistently stored using the Shared Memory API.


<a id="orgeb5f5e0"></a>

# Implemented Features

-   User Authentication
-   Browse the Database for Exercises which contain text instructions
-   Create workouts based on these workouts
-   Share workouts with other users
-   Add workouts to your list for quick reference
-   Track your progress on an exercise by adding data points to a graph
-   Document your progress with pictures of yourself
-   Take pictures yourself or select them from your gallery
-   Look in detail when the pictures were taken and how much you weighed at that time
-   We implemented a basic stepcounter with an animated progress bar into our App to help our Users during their workout.


<a id="orgd219a1b"></a>

# Archiecture Overview

Stepbook makes use of Google Firebase and Firestore for user authentication and data storage.
Email and password is the only authentication method implemented and all of the data that is used in displaying workouts, exercises and past exercise data is stored on the Firestore database with the exception of the progress gallery and stepcounter.
The application mainly makes use of the Navigation Component with the SafeArgs Gradle plugin for its Screen/Fragment transitions.
Passing data between Fragments has been solved by making use of the offline persistance feature of the FirebaseFirestore API which resulted in needing fewer ViewModels as data could just be fetched again without the problem of generating alot of network traffic.
Stepbook makes use of the external libraries [MPAndroidChart by PhilJay](https://github.com/PhilJay/MPAndroidChart) and [CircularProgressBar by lopspower](https://github.com/lopspower/CircularProgressBar) for displaying the tracked exercises graph and stepcounter respectively.


<a id="org123dd69"></a>

# common package


<a id="org99fba1b"></a>

## FirebaseUtil

This class contains all of the functions neccessary for reading and writing to the Firestore database.
The functions inside this class make use of the Task API for return values, so you can attach a callback function once your Query/Operation has returned.


<a id="org57e7863"></a>

## User

Serves as a class for serializing/deserializing User profiles.


<a id="orgecb11e4"></a>

# training package

This package contains all classes responsable for viewing exercises, creating workouts and related features.


<a id="orge90e500"></a>

## LoginFragment

Fragment which provides the inteface for user authentication and registration.
Once a user has been authenticated, he will be taken to the TrainingMenuFragment.


<a id="orge19febe"></a>

## adapters package

Contains all RecyclerView Adapters


<a id="org11922ae"></a>

### CreateWorkoutAdapter

Adapter for the RecyclerView in the CreateWorkoutFragment class.
Each item represents a WorkoutUnit when creating a new WorkoutPlan.
It accesses data from the CreateWorkoutViewModel class for displaying each workout unit.


<a id="org1a98a9d"></a>

### ExercisesAdapder

Adapter for the RecyclerView in the ExercisesFragment class.
Each item represents an Exercise which can either be chosen to be used when creating a workout or be viewed when looking at all available exercises.


<a id="org26dbedb"></a>

### PublicWorkoutsAdapter

Adapter for the RecyclerView in the PublicWorkoutsFragment class.
Each item represents a workout which has been published and can be saved to the users list or viewed.


<a id="org3df3fa4"></a>

### TrackedExerciseAdapter

Adapter for the RecyclerView in the ViewExerciseFragment class.
Each item represents a Datapoint which a user added to the exercise.
Each item holds a button for removing this datapoint.


<a id="org27c35a9"></a>

### UserWorkoutsAdapter

Adapter for the RecyclerView in the UserWorkoutsFragment class.
Each item represents a workout created by a user or a workout which has been added from the publicly available workouts.
Each item holds a button for removing or viewing this workout


<a id="orgb341527"></a>

### ViewWorkoutAdapter

Adapter for the RecyclerView in the ViewWorkoutFragment class.
Each item represents a WorkoutUnit inside the chosen WorkoutPlan


<a id="org254e41e"></a>

## data package

Classes in this package allow easy serialization and deserialization when performing database queries/operations


<a id="org4bd353d"></a>

## fragments package

Contains all of the projects fragments.
All fragments use the FirestoreUtil class for fetching their data or other operations (remove, add, etc.).
If data has to be passed from fragment A to fragment B, fragment A will give fragment B the database document id, which fragment B can then use to perform a second query.


<a id="org3ff5159"></a>

### CreateWorkoutFragment

Fragment for creating a new WorkoutPlan and adding it to the users list.


<a id="org982263a"></a>

### EnterExerciseDataDialogFragment

Dialog fragment which is used when adding datapoints to an exercise graph.
A callback function can be passed in its constructer that gets called with the users input as parameter if the OK button is clicked.


<a id="orgd69f039"></a>

### ExercisesFragment

Fragment which is opened when viewing Exercises on the database or choosing an exercise when creating a WorkoutPlan.
To discern between the two cases where the fragment is opened to view exercises from the database or choosing an exercise for creating a WorkoutPlan,
the enum &rsquo;Action&rsquo; is passed as a parameter when navigating to this fragment.
Based on the passed value, the fragments views are set up accordingly.


<a id="org6a299d4"></a>

### PublicWorkoutsFragment

Fragment for displaying WorkoutPlans publicly available on the database.


<a id="org613afff"></a>

### TrainingMenuFragment

Serves as a home screen from where every fragment can be navigated to.


<a id="orgc3f8421"></a>

### UserWorkoutsFragment

Fragment for displaying WorkoutPlans from the users list.


<a id="orgec1a3c9"></a>

### ViewExerciseFragment

Fragment for displaying data related to an exercise.
A LineChart from the external library [MPAndroidChart by PhilJay](https://github.com/PhilJay/MPAndroidChart) is shown to display the datapoints which to user can add.
The code responsable for drawing the chart can be found in the renderChart function.


<a id="orge92f245"></a>

### ViewWorkoutFragment

Fragment for displaying data related to a WorkoutPlan.


<a id="orgdf7936b"></a>

## viewmodels package


<a id="org6569d97"></a>

### CreateWorkoutViewModel

A ViewModel which holds the necessary Data when creating a workout.
It is shared between CreateWorkoutFragment and ExercisesFragment.


<a id="org1ce496f"></a>

# progressGallery package


<a id="org9db13db"></a>

## ProgressGallery.kt

Serves as a class that shows a RecyclerView with all the pictures that have been taken. Also gives the button to add a picture.


<a id="orga7b2981"></a>

## AddPhoto.kt

This class gives the opportunity to either pick a picture of the gallery or take a picture and then progress automaticly.


<a id="org7d04865"></a>

## ChooseWeight.kt

The weight is then determined with this class.
The weight is then selected with the date as the file name, which is then used to save the picture.
The name and path of the picture is then saved persistently.


<a id="org6f422a4"></a>

## PhotoView.kt

This class shows a chosen picture of the progress gallery as a bigger picture and also shows the date and weight which are taken from  the name of the given picture.


<a id="org0d8cb3b"></a>

# Stepcounter

The Step Counter acesses the inbuilt step sensor of the users android device, if said does not have one a Toast informing the user of that will be displayed.
The animated progress bar was a online resource which is free to use and easy to impliment.
[Documented here](https://github.com/lopspower/CircularProgressBar)

This Stepcounter is implimented within a fragment and can be acsessed via the home menu. Originally it was planed to be able to reset the step count via a long press action, aswell as a daily reset, however, due to several issues with the implimentation of shared preferences and afte a failed attempt to use datastore as a alternative said feature has been scrapped for now.
The sensor count will only reset after a device reboot.

The functionality of the Stepcounter was tested via a physical device, after innitial issues regarding the onResume function and several trouble shooting attempts said issues could be successfully resolved.
We opted not to impliment the permission  handling for the Stepcounter in this application during the prototyping phase.

Possible ways to scale the Funktionality up with a calorie counter were considered but discarded due to time constraints.


<a id="org3b00cd4"></a>

# Required permissions


<a id="org5b12966"></a>

## CAMERA

The camera permission allows that the app can take a picture.


<a id="org095a556"></a>

## WRITE<sub>EXTERNAL</sub><sub>STORAGE</sub>

This permission allows the app to save the picture and the JSON file.


<a id="org3fc8000"></a>

## READ<sub>EXTERNAL</sub><sub>STORAGE</sub>

The app can read the JSON file and the pictures if this permission is allowed.

