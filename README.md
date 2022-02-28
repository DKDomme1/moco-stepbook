
# Table of Contents

1.  [Application Overview](#org5f5789d)
2.  [Implemented Features](#orgbb14f18)
3.  [Architecture Overview](#org1a1160b)
4.  [common package](#orgb01e09f)
    1.  [FirebaseUtil](#org2bb3d9d)
    2.  [User](#org3a08da0)
5.  [training package](#org0925c61)
    1.  [LoginFragment](#orgb5589ce)
    2.  [adapters package](#orgc1ff441)
        1.  [CreateWorkoutAdapter](#orgf5486fd)
        2.  [ExercisesAdapder](#orgb9bbc7b)
        3.  [PublicWorkoutsAdapter](#orga70502f)
        4.  [TrackedExerciseAdapter](#org7892d12)
        5.  [UserWorkoutsAdapter](#org83f3259)
        6.  [ViewWorkoutAdapter](#org59408ca)
    3.  [data package](#orgf19f40c)
    4.  [fragments package](#org6614daf)
        1.  [CreateWorkoutFragment](#org65a70c6)
        2.  [EnterExerciseDataDialogFragment](#org604642c)
        3.  [ExercisesFragment](#org4c68fe8)
        4.  [PublicWorkoutsFragment](#org5423105)
        5.  [TrainingMenuFragment](#orgbd26f93)
        6.  [UserWorkoutsFragment](#orga5953ff)
        7.  [ViewExerciseFragment](#org2c5b982)
        8.  [ViewWorkoutFragment](#orgcef1188)
    5.  [viewmodels package](#org2e133cc)
        1.  [CreateWorkoutViewModel](#org7986bc8)
6.  [progressGallery package](#org03ffb82)
    1.  [ProgressGallery](#orgde1391a)
    2.  [AddPhoto](#org7e66f07)
    3.  [ChooseWeight](#org4a78bfc)
    4.  [PhotoView](#org5ed3db9)
7.  [Step counter](#orgcc5a42a)
8.  [Required permissions](#orgbec4b65)
    1.  [CAMERA](#org1132949)
    2.  [WRITE\_EXTERNAL\_STORAGE](#org8169e78)
    3.  [READ\_EXTERNAL\_STORAGE](#orgc204981)
    4.  [ACTIVITY\_RECOGNITION\_PERMISSION](#orgba96769)



<a id="org5f5789d"></a>

# Application Overview

Stepbook is an application which helps users track their workout or diet progress by providing the interface for comparing past with current results.
It achieves this with the use of a progress gallery, where users can keep track of their body for differences, a step counter, where users can see how many steps they have taken since, and the option to enter their performance of an exercise which is then shown in a graph for visual comparison.
Stepbook also allows users to create and share workouts for quick reference.
This application would become useful when working out in the gym or outside where a notebook and pen would become unwieldly.


<a id="orgbb14f18"></a>

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
-   We implemented a basic stepcounter with an animated progress bar into our app to help our users during their workout.


<a id="org1a1160b"></a>

# Architecture Overview

Stepbook makes use of Google Firebase and Firestore for user authentication and data storage.
Email and password is the only authentication method implemented and all of the data that is used in displaying workouts, exercises and past exercise data is stored on the Firestore database with the exception of the progress gallery and step counter.
The application mainly makes use of the Navigation Component with the SafeArgs Gradle plugin for its screen/fragment transitions.
Passing data between fragments has been solved by making use of the offline persistance feature of the FirebaseFirestore API which resulted in needing fewer ViewModels as data could just be fetched again without the problem of generating alot of network traffic.
Stepbook makes use of the external libraries [MPAndroidChart by PhilJay](https://github.com/PhilJay/MPAndroidChart) and [CircularProgressBar by lopspower](https://github.com/lopspower/CircularProgressBar) for displaying the tracked exercises graph and stepcounter respectively.
Stepbook uses the Camerax library to capture pictures without launching the camera app.
The weight is entered for the respective picture and the picture will be saved in a separate path with a name consisting of date and weight.
Stepbook uses the Gson library to convert the picture name and path into a JSON file.
These JSON files are then read and persistently stored using the Shared Preferences API.


<a id="orgb01e09f"></a>

# common package


<a id="org2bb3d9d"></a>

## FirebaseUtil

This class contains all of the functions neccessary for reading and writing to the Firestore database.
The functions inside this class make use of the Task API for return values, so you can attach a callback function once your query/operation has returned.


<a id="org3a08da0"></a>

## User

Serves as a class for serializing/deserializing User profiles.


<a id="org0925c61"></a>

# training package

This package contains all classes responsable for viewing exercises, creating workouts and related features.


<a id="orgb5589ce"></a>

## LoginFragment

Fragment which provides the inteface for user authentication and registration.
Once a user has been authenticated, he will be taken to the TrainingMenuFragment.


<a id="orgc1ff441"></a>

## adapters package

Contains all RecyclerView Adapters


<a id="orgf5486fd"></a>

### CreateWorkoutAdapter

Adapter for the RecyclerView in the CreateWorkoutFragment class.
Each item represents a WorkoutUnit when creating a new WorkoutPlan.
It accesses data from the CreateWorkoutViewModel class for displaying each workout unit.


<a id="orgb9bbc7b"></a>

### ExercisesAdapder

Adapter for the RecyclerView in the ExercisesFragment class.
Each item represents an exercise which can either be chosen to be used when creating a workout or be viewed when looking at all available exercises.


<a id="orga70502f"></a>

### PublicWorkoutsAdapter

Adapter for the RecyclerView in the PublicWorkoutsFragment class.
Each item represents a workout which has been published and can be saved to the users list or viewed.


<a id="org7892d12"></a>

### TrackedExerciseAdapter

Adapter for the RecyclerView in the ViewExerciseFragment class.
Each item represents a datapoint which a user added to the exercise.
Each item holds a button for removing this datapoint.


<a id="org83f3259"></a>

### UserWorkoutsAdapter

Adapter for the RecyclerView in the UserWorkoutsFragment class.
Each item represents a workout created by a user or a workout which has been added from the publicly available workouts.
Each item holds a button for removing or viewing this workout


<a id="org59408ca"></a>

### ViewWorkoutAdapter

Adapter for the RecyclerView in the ViewWorkoutFragment class.
Each item represents a WorkoutUnit inside the chosen WorkoutPlan


<a id="orgf19f40c"></a>

## data package

Classes in this package allow easy serialization and deserialization when performing database queries/operations


<a id="org6614daf"></a>

## fragments package

Contains all of the projects fragments.
All fragments use the FirestoreUtil class for fetching their data or other operations (remove, add, etc.).
If data has to be passed from fragment A to fragment B, fragment A will give fragment B the database document id, which fragment B can then use to perform a second query.


<a id="org65a70c6"></a>

### CreateWorkoutFragment

Fragment for creating a new WorkoutPlan and adding it to the users list.


<a id="org604642c"></a>

### EnterExerciseDataDialogFragment

Dialog fragment which is used when adding datapoints to an exercise graph.
A callback function can be passed in its constructer that gets called with the users input as parameter if the OK button is clicked.


<a id="org4c68fe8"></a>

### ExercisesFragment

Fragment which is opened when viewing exercises on the database or choosing an exercise when creating a WorkoutPlan.
To discern between the two cases where the fragment is opened to view exercises from the database or choosing an exercise for creating a WorkoutPlan, the enum &rsquo;Action&rsquo; is passed as a parameter when navigating to this fragment.
Based on the passed value, the fragments views are set up accordingly.


<a id="org5423105"></a>

### PublicWorkoutsFragment

Fragment for displaying WorkoutPlans publicly available on the database.


<a id="orgbd26f93"></a>

### TrainingMenuFragment

Serves as a home screen from where every fragment can be navigated to.


<a id="orga5953ff"></a>

### UserWorkoutsFragment

Fragment for displaying WorkoutPlans from the users list.


<a id="org2c5b982"></a>

### ViewExerciseFragment

Fragment for displaying data related to an exercise.
A LineChart from the external library [MPAndroidChart by PhilJay](https://github.com/PhilJay/MPAndroidChart) is shown to display the datapoints which to user can add.
The code responsable for drawing the chart can be found in the renderChart function.


<a id="orgcef1188"></a>

### ViewWorkoutFragment

Fragment for displaying data related to a WorkoutPlan.


<a id="org2e133cc"></a>

## viewmodels package


<a id="org7986bc8"></a>

### CreateWorkoutViewModel

A ViewModel which holds the necessary data when creating a workout.
It is shared between CreateWorkoutFragment and ExercisesFragment.


<a id="org03ffb82"></a>

# progressGallery package


<a id="orgde1391a"></a>

## ProgressGallery

Serves as a class that shows a RecyclerView with all the pictures that have been taken. Also gives the button to add a picture.


<a id="org7e66f07"></a>

## AddPhoto

This class gives the opportunity to either pick a picture of the gallery or take a picture and then progress automaticly.


<a id="org4a78bfc"></a>

## ChooseWeight

The weight is then determined with this class.
The weight is then selected with the date as the file name, which is then used to save the picture.
The name and path of the picture is then saved persistently.


<a id="org5ed3db9"></a>

## PhotoView

This class shows a chosen picture of the progress gallery as a bigger picture and also shows the date and weight which are taken from  the name of the given picture.


<a id="orgcc5a42a"></a>

# Step counter

The step counter acesses the inbuilt step sensor of the users android device, if the device does not have a step sensor, a toast informing the user will be displayed.
The animated progress bar is from a free external library on github, which is easy to use and incorporate into your project. [Documented here(click)](https://github.com/lopspower/CircularProgressBar)

This step counter is placed inside a fragment and can be acsessed via the home menu. Originally it was planed to be able to reset the step count via a long press action, aswell as a daily reset, however, due to several issues with the implementation of shared preferences and after a failed attempt to use datastore as an alternative, said feature has been scrapped for now.
The sensor count will only reset after a device has rebooted.

The functionality of the step counter was tested with a physical device, after initial issues regarding the onResume function and several trouble shooting attempts, said issues could be successfully resolved.
Permission handling was partially implemented, however, due to the deprecation of several key functions which complicated the process within fragments said redirects the user to the app settings rather then simply allowing them to grant the ACTIVITY\_RECOGNITION\_PERMISSION within the app.

It was decided that this would be enough for presentation purposes and could be fixed if the project was to be developed further.
Possible ways to scale the functionality up with a calorie counter were considered but discarded due to time constraints.


<a id="orgbec4b65"></a>

# Required permissions


<a id="org1132949"></a>

## CAMERA

The camera permission allows that the app can take a picture.


<a id="org8169e78"></a>

## WRITE\_EXTERNAL\_STORAGE

This permission allows the app to save the picture and the JSON file.


<a id="orgc204981"></a>

## READ\_EXTERNAL\_STORAGE

The app can read the JSON file and the pictures if this permission is allowed.


<a id="orgba96769"></a>

## ACTIVITY\_RECOGNITION\_PERMISSION

Required to read steps from the step sensor.

