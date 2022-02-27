package com.example.stepbook.common

import com.example.stepbook.training.data.Exercise
import com.example.stepbook.training.data.TrackedExercise
import com.example.stepbook.training.data.WorkoutPlan
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase

class FirestoreUtil {
    companion object {
        private const val USERS_COLLECTION = "users"
        private const val WORKOUTS_COLLECTION = "workouts"
        private const val EXERCISES_COLLECTION = "exercises"
        private const val TRACKED_EXERCISES_COLLECTION = "exercise_data"

        private const val TAG = "FirestoreUtil"


        fun fetchPublicWorkouts(): Task<QuerySnapshot> {
            return FirebaseFirestore.getInstance()
                .collection(WORKOUTS_COLLECTION)
                .get()
        }

        fun fetchUserWorkouts(): Task<QuerySnapshot> {
            return FirebaseFirestore.getInstance()
                .collection(USERS_COLLECTION)
                .document(Firebase.auth.currentUser!!.uid)
                .collection(WORKOUTS_COLLECTION)
                .get()
        }

        fun fetchPublicExercises(): Task<QuerySnapshot> {
            return FirebaseFirestore.getInstance()
                .collection(EXERCISES_COLLECTION)
                .get()
        }

        fun getPublicWorkoutById(workoutId: String): Task<DocumentSnapshot> {
            return FirebaseFirestore.getInstance()
                .collection(WORKOUTS_COLLECTION)
                .document(workoutId)
                .get()
        }

        fun getUserWorkoutById(workoutId: String): Task<DocumentSnapshot> {
            return FirebaseFirestore.getInstance()
                .collection(USERS_COLLECTION)
                .document(Firebase.auth.currentUser!!.uid)
                .collection(WORKOUTS_COLLECTION)
                .document(workoutId).get()
        }

        fun removeUserWorkout(workoutId: String): Task<Void> {
            return FirebaseFirestore.getInstance()
                .collection(USERS_COLLECTION)
                .document(Firebase.auth.currentUser!!.uid)
                .collection(WORKOUTS_COLLECTION)
                .document(workoutId)
                .delete()
        }

        fun getExerciseById(exerciseId: String): Task<DocumentSnapshot> {
            return FirebaseFirestore.getInstance()
                .collection(EXERCISES_COLLECTION)
                .document(exerciseId)
                .get()
        }

        fun getUserById(userId: String): Task<DocumentSnapshot> {
            return FirebaseFirestore.getInstance()
                .collection(USERS_COLLECTION)
                .document(userId)
                .get()
        }

        fun addPublicWorkoutToUserWorkouts(workoutId: String): Task<Void> {
            return getPublicWorkoutById(workoutId).continueWithTask {
                val workout = it.result.toObject(WorkoutPlan::class.java)
                val docRef = FirebaseFirestore.getInstance()
                    .collection(USERS_COLLECTION)
                    .document(Firebase.auth.currentUser!!.uid)
                    .collection(WORKOUTS_COLLECTION)
                    .document()

                workout!!.isPublic = true
                workout.publicDocId = workout.docId
                workout.docId = docRef.id
                docRef.set(workout)
            }
        }

        fun addUserDefinedWorkout(workout: WorkoutPlan): Task<Void> {
            val docRef = FirebaseFirestore.getInstance()
                .collection(USERS_COLLECTION)
                .document(Firebase.auth.currentUser!!.uid)
                .collection(WORKOUTS_COLLECTION)
                .document()
            workout.docId = docRef.id
            workout.isPublic = false
            workout.ownerUId = Firebase.auth.currentUser!!.uid
            return docRef.set(workout)
        }

        fun publishUserWorkout(workout: WorkoutPlan): Task<Void> {
            val firestore = FirebaseFirestore.getInstance()
            val publicDocRef = firestore.collection(WORKOUTS_COLLECTION).document()
            val userDocRef = firestore.collection(USERS_COLLECTION)
                .document(Firebase.auth.currentUser!!.uid)
                .collection(WORKOUTS_COLLECTION)
                .document(workout.docId!!)
            val userWorkout = workout
            userWorkout.publicDocId = publicDocRef.id
            userWorkout.isPublic = true

            val publicWorkout = workout.copy()
            publicWorkout.docId = publicDocRef.id


            return publicDocRef.set(publicWorkout).continueWithTask { userDocRef.set(userWorkout) }
        }

        fun addDataPointToExercise(
            value: Int,
            exercise: Exercise,
            trackedExercise: TrackedExercise?
        ): Task<Void> {
            val firestore = FirebaseFirestore.getInstance()
            val uId = Firebase.auth.currentUser!!.uid
            if (trackedExercise == null) {
                val newTrackedExercise = TrackedExercise(
                    null,
                    exercise.docId,
                    HashMap<String, Int>().also { it[Timestamp.now().seconds.toString()] = value }
                )
                val docRef = firestore.collection(USERS_COLLECTION)
                    .document(uId)
                    .collection(TRACKED_EXERCISES_COLLECTION)
                    .document()
                newTrackedExercise.docId = docRef.id
                return docRef.set(newTrackedExercise)
            }
            trackedExercise.datapoints!![Timestamp.now().seconds.toString()] = value
            return firestore.collection(USERS_COLLECTION)
                .document(uId)
                .collection(TRACKED_EXERCISES_COLLECTION)
                .document(trackedExercise.docId!!)
                .set(trackedExercise)
        }

        fun getTrackedExerciseByPublicExercise(exercise: Exercise?): Task<QuerySnapshot> {
            val firestore = FirebaseFirestore.getInstance()
            val uId = Firebase.auth.currentUser!!.uid
            return firestore.collection(USERS_COLLECTION)
                .document(uId)
                .collection(TRACKED_EXERCISES_COLLECTION)
                .whereEqualTo("exerciseDocId", exercise!!.docId)
                .get()
        }

        fun getTrackedExercises(): Task<QuerySnapshot> {
            val firestore = FirebaseFirestore.getInstance()
            val uId = Firebase.auth.currentUser!!.uid
            return firestore.collection(USERS_COLLECTION)
                .document(uId)
                .collection(TRACKED_EXERCISES_COLLECTION)
                .get()
        }

        fun setTrackedExercise(trackedExercise: TrackedExercise): Task<Void> {
            val firestore = FirebaseFirestore.getInstance()
            val uId = Firebase.auth.currentUser!!.uid
            return firestore.collection(USERS_COLLECTION).document(uId).collection(
                TRACKED_EXERCISES_COLLECTION
            ).document(trackedExercise.docId!!).set(trackedExercise)
        }
    }
}