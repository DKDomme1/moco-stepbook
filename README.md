
# Table of Contents

1.  [Application Overview](#org670c79b)
2.  [Implemented Features](#org6aa255b)
3.  [Architecture Overview](#orgcd5d2d7)
4.  [common package](#org2479e55)
    1.  [FirebaseUtil](#orgb62127b)
    2.  [User](#org5d827a9)
5.  [training package](#orgaaf40c1)
    1.  [LoginFragment](#org22c05d4)
    2.  [adapters package](#orge4daeba)
        1.  [CreateWorkoutAdapter](#org8770ed6)
        2.  [ExercisesAdapder](#orgce470c9)
        3.  [PublicWorkoutsAdapter](#orge89a1fd)
        4.  [TrackedExerciseAdapter](#orgf09a1ec)
        5.  [UserWorkoutsAdapter](#orge1b11ee)
        6.  [ViewWorkoutAdapter](#org9a8eb34)
    3.  [data package](#org304fcda)
    4.  [fragments package](#org40a4467)
        1.  [CreateWorkoutFragment](#orgd951e32)
        2.  [EnterExerciseDataDialogFragment](#org8b553d3)
        3.  [ExercisesFragment](#org22b844e)
        4.  [PublicWorkoutsFragment](#org01a32db)
        5.  [TrainingMenuFragment](#org4fe0d9a)
        6.  [UserWorkoutsFragment](#orgcbe9b5d)
        7.  [ViewExerciseFragment](#org3a56e52)
        8.  [ViewWorkoutFragment](#orgaa838ee)
    5.  [viewmodels package](#org07391fd)
        1.  [CreateWorkoutViewModel](#orgc3f6de3)
6.  [progressGallery package](#org86b1eca)
    1.  [ProgressGallery.kt](#orgc890023)
    2.  [AddPhoto.kt](#org4d734b6)
    3.  [ChooseWeight.kt](#orgca0972d)
    4.  [PhotoView.kt](#org790316b)
7.  [Stepcounter](#orgfdfdee2)
8.  [Required permissions](#orgfdd75b0)
    1.  [CAMERA](#org77a4590)
    2.  [WRITE<sub>EXTERNAL</sub><sub>STORAGE</sub>](#orgb4e3c26)
    3.  [READ<sub>EXTERNAL</sub><sub>STORAGE</sub>](#orgef88138)



<a id="org670c79b"></a>

# Application Overview

Stepbook is an application which helps their users track their workout or diet progress by providing the interface for comparing past with current results.
It achieves this with the use of a progress gallery, where users can keep track of their body for differences, a step counter, where users can see how many steps they have taken since, and the option to enter their performance of an exercise which is then shown in a graph for visual comparison.
Stepbook also allows users to create and share workouts for quick reference.
This application would become useful when working out in the gym or outside where a notebook and pen would become unwieldly.


<a id="org6aa255b"></a>

# Implemented Features

-   User Authentication
-   Browse the Database for Exercises which contain text instructions
-   Create workouts based on these exercises
-   Share workouts with other users
-   Add workouts to your list for quick reference
-   Track your progress on an exercise by adding data points to a graph
-   Document your progress with pictures of yourself
-   Take pictures yourself or select them from your gallery
-   Look in detail when the pictures were taken and how much you weighed at that time
-   We implemented a basic stepcounter with an animated progress bar into our App to help our Users during their workout.


<a id="orgcd5d2d7"></a>

# Architecture Overview

Stepbook makes use of Google Firebase and Firestore for user authentication and data storage.
Email and password is the only authentication method implemented and all of the data that is used in displaying workouts, exercises and past exercise data is stored on the Firestore database with the exception of the progress gallery and stepcounter.
The application mainly makes use of the Navigation Component with the SafeArgs Gradle plugin for its Screen/Fragment transitions.
Passing data between Fragments has been solved by making use of the offline persistance feature of the FirebaseFirestore API which resulted in needing fewer ViewModels as data could just be fetched again without the problem of generating alot of network traffic.
Stepbook makes use of the external libraries [MPAndroidChart by PhilJay](https://github.com/PhilJay/MPAndroidChart) and [CircularProgressBar by lopspower](https://github.com/lopspower/CircularProgressBar) for displaying the tracked exercises graph and stepcounter respectively.
Stepbook uses the Camerax library to capture pictures without launching the camera app.
The weight is entered for the respective picture and the picture will be saved in a separate path with a name consisting of date and weight.
Stepbook uses the Gson library to convert the picture name and path into a JSON file.
These JSON files are then read and persistently stored using the Shared Memory API.


<a id="org2479e55"></a>

# common package


<a id="orgb62127b"></a>

## FirebaseUtil

This class contains all of the functions neccessary for reading and writing to the Firestore database.
The functions inside this class make use of the Task API for return values, so you can attach a callback function once your Query/Operation has returned.


<a id="org5d827a9"></a>

## User

Serves as a class for serializing/deserializing User profiles.


<a id="orgaaf40c1"></a>

# training package

This package contains all classes responsable for viewing exercises, creating workouts and related features.


<a id="org22c05d4"></a>

## LoginFragment

Fragment which provides the inteface for user authentication and registration.
Once a user has been authenticated, he will be taken to the TrainingMenuFragment.


<a id="orge4daeba"></a>

## adapters package

Contains all RecyclerView Adapters


<a id="org8770ed6"></a>

### CreateWorkoutAdapter

Adapter for the RecyclerView in the CreateWorkoutFragment class.
Each item represents a WorkoutUnit when creating a new WorkoutPlan.
It accesses data from the CreateWorkoutViewModel class for displaying each workout unit.


<a id="orgce470c9"></a>

### ExercisesAdapder

Adapter for the RecyclerView in the ExercisesFragment class.
Each item represents an Exercise which can either be chosen to be used when creating a workout or be viewed when looking at all available exercises.


<a id="orge89a1fd"></a>

### PublicWorkoutsAdapter

Adapter for the RecyclerView in the PublicWorkoutsFragment class.
Each item represents a workout which has been published and can be saved to the users list or viewed.


<a id="orgf09a1ec"></a>

### TrackedExerciseAdapter

Adapter for the RecyclerView in the ViewExerciseFragment class.
Each item represents a Datapoint which a user added to the exercise.
Each item holds a button for removing this datapoint.


<a id="orge1b11ee"></a>

### UserWorkoutsAdapter

Adapter for the RecyclerView in the UserWorkoutsFragment class.
Each item represents a workout created by a user or a workout which has been added from the publicly available workouts.
Each item holds a button for removing or viewing this workout


<a id="org9a8eb34"></a>

### ViewWorkoutAdapter

Adapter for the RecyclerView in the ViewWorkoutFragment class.
Each item represents a WorkoutUnit inside the chosen WorkoutPlan


<a id="org304fcda"></a>

## data package

Classes in this package allow easy serialization and deserialization when performing database queries/operations


<a id="org40a4467"></a>

## fragments package

Contains all of the projects fragments.
All fragments use the FirestoreUtil class for fetching their data or other operations (remove, add, etc.).
If data has to be passed from fragment A to fragment B, fragment A will give fragment B the database document id, which fragment B can then use to perform a second query.


<a id="orgd951e32"></a>

### CreateWorkoutFragment

Fragment for creating a new WorkoutPlan and adding it to the users list.


<a id="org8b553d3"></a>

### EnterExerciseDataDialogFragment

Dialog fragment which is used when adding datapoints to an exercise graph.
A callback function can be passed in its constructer that gets called with the users input as parameter if the OK button is clicked.


<a id="org22b844e"></a>

### ExercisesFragment

Fragment which is opened when viewing Exercises on the database or choosing an exercise when creating a WorkoutPlan.
To discern between the two cases where the fragment is opened to view exercises from the database or choosing an exercise for creating a WorkoutPlan,
the enum &rsquo;Action&rsquo; is passed as a parameter when navigating to this fragment.
Based on the passed value, the fragments views are set up accordingly.


<a id="org01a32db"></a>

### PublicWorkoutsFragment

Fragment for displaying WorkoutPlans publicly available on the database.


<a id="org4fe0d9a"></a>

### TrainingMenuFragment

Serves as a home screen from where every fragment can be navigated to.


<a id="orgcbe9b5d"></a>

### UserWorkoutsFragment

Fragment for displaying WorkoutPlans from the users list.


<a id="org3a56e52"></a>

### ViewExerciseFragment

Fragment for displaying data related to an exercise.
A LineChart from the external library [MPAndroidChart by PhilJay](https://github.com/PhilJay/MPAndroidChart) is shown to display the datapoints which to user can add.
The code responsable for drawing the chart can be found in the renderChart function.


<a id="orgaa838ee"></a>

### ViewWorkoutFragment

Fragment for displaying data related to a WorkoutPlan.


<a id="org07391fd"></a>

## viewmodels package


<a id="orgc3f6de3"></a>

### CreateWorkoutViewModel

A ViewModel which holds the necessary Data when creating a workout.
It is shared between CreateWorkoutFragment and ExercisesFragment.


<a id="org86b1eca"></a>

# progressGallery package


<a id="orgc890023"></a>

## ProgressGallery.kt

Serves as a class that shows a RecyclerView with all the pictures that have been taken. Also gives the button to add a picture.


<a id="org4d734b6"></a>

## AddPhoto.kt

This class gives the opportunity to either pick a picture of the gallery or take a picture and then progress automaticly.


<a id="orgca0972d"></a>

## ChooseWeight.kt

The weight is then determined with this class.
The weight is then selected with the date as the file name, which is then used to save the picture.
The name and path of the picture is then saved persistently.


<a id="org790316b"></a>

## PhotoView.kt

This class shows a chosen picture of the progress gallery as a bigger picture and also shows the date and weight which are taken from  the name of the given picture.


<a id="orgfdfdee2"></a>

# Stepcounter

The Step Counter acesses the inbuilt step sensor of the users android device, if said does not have one a Toast informing the user of that will be displayed.
The animated progress bar was a online resource which is free to use and easy to impliment.
[Documented here](https://github.com/lopspower/CircularProgressBar)

This Stepcounter is implimented within a fragment and can be acsessed via the home menu. Originally it was planed to be able to reset the step count via a long press action, aswell as a daily reset, however, due to several issues with the implimentation of shared preferences and afte a failed attempt to use datastore as a alternative said feature has been scrapped for now.
The sensor count will only reset after a device reboot.

The functionality of the Stepcounter was tested via a physical device, after innitial issues regarding the onResume function and several trouble shooting attempts said issues could be successfully resolved.
We opted not to impliment the permission  handling for the Stepcounter in this application during the prototyping phase.

Possible ways to scale the Funktionality up with a calorie counter were considered but discarded due to time constraints.


<a id="orgfdd75b0"></a>

# Required permissions


<a id="org77a4590"></a>

## CAMERA

The camera permission allows that the app can take a picture.


<a id="orgb4e3c26"></a>

## WRITE<sub>EXTERNAL</sub><sub>STORAGE</sub>

This permission allows the app to save the picture and the JSON file.


<a id="orgef88138"></a>

## READ<sub>EXTERNAL</sub><sub>STORAGE</sub>

The app can read the JSON file and the pictures if this permission is allowed.

