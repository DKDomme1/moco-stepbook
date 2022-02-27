package com.example.stepbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stepbook.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val USE_FIREBASE_EMULATOR = false
    private lateinit var binding: ActivityMainBinding

    val ex1String: String =
        """The one arm dumbbell row is a variation of the dumbbell row and an exercise used to build back muscle and strength.

    The back is a muscle group that requires a fair amount of variation. So, experiment with several different angles and hand positions to maximize your back muscle growth.

    Rows are a foundational movement pattern and are very important to train for balanced muscle growth and strength. So, experiment until you find a rowing variation that you enjoy and work on it.

    The one arm dumbbell row can be performed during your back workouts, upper body workouts, pull workouts, and full body workouts.
    One Arm Dumbbell Row Instructions

    1. Assume a standing position while holding a dumbbell in one hand with a neutral grip.
    2. Hinge forward until your torso is roughly parallel with the floor (or slightly above) and then begin the movement by driving the elbow behind the body while retracting the shoulder blade.
    3. Pull the dumbbell towards your body until the elbow is at (or just past) the midline and then slowly lower the dumbbell back to the starting position under control.
    4. Repeat for the desired number of repetitions on both sides."""

    val ex2String: String =
        """The seated Arnold press is a variation of the seated dumbbell press and an exercise utilized to build shoulder muscle size and strength.

The Arnold press, named after Arnold Schwarzenegger himself, is an exercise used to target every single head of the deltoid.

The exercise is best used by those seeking aesthetic benefits due to its long time under tension, but can also be used as an accessory movement by those attempting to build strength.
Seated Arnold Press Instructions

1. Set up an adjustable angle bench to 90 degrees and select the desired weight from the rack.
2. Pick up the dumbbells from the floor using a neutral grip (palms facing in). Position the end of the dumbbells on your knees and sit down on the bench.
3. Using a safe and controlled motion, kick your knees up one at a time in order to get each dumbbell into place.
4. Once the dumbbells are in place, rotate your palms so they are facing you.
5. Take a deep breath then press the dumbbells overhead by extending the elbows and contracting the deltoids.
6. As you press, rotate the dumbbells until your palms are facing forward.
7. Slowly lower the dumbbells back to the starting position (the arms should be roughly 90 degrees or slightly lower depending upon limb lengths).
8. Repeat for the desired number of repetitions.

"""
    val ex3String: String =
        """The incline dumbbell bench press is a variation of the incline bench press and an exercise used to build the muscles of the chest. The shoulders and triceps will be indirectly involved as well.

Utilizing an incline will allow you to better target the upper portion of the chest, a lagging part for a lot of lifters.

The use of dumbbells will also promote balanced and equal strength among both sides of the chest. They can also help prevent shoulder and pec injuries when performing presses.

You can include incline dumbbell bench press in your chest workouts, upper body workouts, push workouts, and full body workouts.
Incline Dumbbell Bench Press Instructions

1.Pick up the dumbbells off the floor using a neutral grip (palms facing in). Position the ends of the dumbbells in your hip crease, and sit down on the edge of an incline bench.
2.To get into position, lay back and keep the weights close to your chest. Once you are in position, take a deep breath, and press the dumbbells to lockout at the top.
3.Slowly lower the dumbbells under control as far as comfortably possible (the handles should be about level with your chest).
4.Contract the chest and push the dumbbells back up to the starting position.
5.Repeat for the desired number of repetitions.

**Dropping the dumbbells to the side is discouraged unless you are experienced with the technique or using excessively heavy weights.

**Ideally you should twist the dumbbells back to neutral (palms facing each other), bring your knees up so the ends of the dumbbells are touching your thighs, then use the weight of the dumbbells to rock back to an upright, seated position.
"""
    val ex4String: String =
        """The chest supported dumbbell row is a variation of the dumbbell bent over row and an exercise used to build back muscle and strength.

The back is a muscle group that requires a fair amount of variation. So, experiment with several different angles and hand positions to maximize your back muscle growth.

Rows are a foundational movement pattern and are very important to train for balanced muscle growth and strength. So, experiment until you find a rowing variation that you enjoy and work on it.

The chest supported dumbbell row can be performed during your back workouts, upper body workouts, pull workouts, and full body workouts.
Neutral Grip Chest Supported Dumbbell Row Instructions

    Position an adjustable incline bench at 45 degrees and lie prone on the bench.
    Grab a dumbbell in each hand utilizing a neutral grip and then begin the movement by driving the elbows behind the body while retracting the shoulder blades.
    Pull the dumbbells towards your body until the elbows are at (or just past) the midline and then slowly lower the dumbbells back to the starting position under control.
    Repeat for the desired number of repetitions.

"""
    val ex5String: String = """

    Set up for the across body hammer curl by grasping a pair of dumbbells and standing straight up holding them at your sides.
    You should be using a neutral grip (palms facing the body) and the dumbbells should not be resting on your body.
    Take up the tension by slightly bending your arms. This is the starting position for the exercise.
    Starting with your weakest arm (usually the left), slowly curl the dumbbell up across the front of your body as shown in the video demonstration.
    Squeeze the bicep at the top of the movement, and then slowly lower the weight back to the starting position.
    Repeat this movement for your other arm.
    This is one rep, now repeat to complete your set!

Exercise Tips:

    The dumbbells should not touch the body at any point during the exercise.
    Keep the rep timing slow and controlled.
    Keep the tension on both of your biceps throughout the whole set."""


    val ex6String: String =
        """The standing dumbbell tricep extension is a variation of the tricep extension and an exercise used to isolate the muscles of the triceps.

Overhead extension exercises are particularly useful in targeting the long head of the triceps muscle. Having a larger and more dense long head will give you an overall appearance of a larger tricep.

Isolation exercises for the triceps are particularly useful for those looking to achieve aesthetics. They can also be helpful for those looking to increase their strength on other pressing motions.
Two Arm Standing Dumbbell Extension Instructions

    Select the desired weight from the rack and stand in an open area.
    To get into position, lift the dumbbell to the top of your shoulder, take a deep breath, overlap your hands around the dumbbell, then press it into position overhead.
    Maintain an overlapped grip and slowly lower the dumbbell behind your head by unlocking your elbows.
    Once your forearms reach parallel or just below, drive the dumbbell back to the starting point by extending the elbows and flexing the triceps.
    Repeat for the desired number of repetitions.

"""
    val ex7String: String =
        """The dumbbell shrug is a variation of the shrug and an exercise used to build trap muscle size.

Traps, being a stubborn muscle group for many, can be trained with a fairly high frequency during the week. When doing so, be sure to mix up the volume, intensity, and implements you use to train the traps.

Dumbbell shrugs can be included into your shoulder workouts, back workouts, upper body workouts, and full body workouts.
Dumbbell Shrug Instructions

    Assume a standing position with the dumbbells on both sides of your body.
    Hinge forward, inhale, and grab the dumbbells with a neutral grip.
    Stand up tall and ensure your spine remains neutral.
    Contract the traps to elevate the shoulders. Squeeze hard at the top and slowly lower the dumbbells back to the starting position.
    Repeat for the desired number of repetitions.
"""
    val ex8String: String =
        """The deadlift is an extremely popular exercise and a true test of total body strength. It is popular across numerous weight lifting circle including bodybuilders, powerlifts, and Crossfit athletes.

The deadlift focuses on lifting dead weight off the ground to hip level without using momentum to assist the weight on its path up. It is known as one of the “big three” exercises for powerlifters which includes squats, bench press, and the deadlift. Bodybuilders use the deadlift to promote muscle growth in their entire posterior chain (muscles on the back of the body).

The deadlift primarily works the muscles of the hamstrings. However, it is a complete compound exercise and also requires muscle activation from the back, glutes, hamstrings, arms, and core.

The conventional deadlift shouldn’t be performed by just anyone as it involves a lot of technique and skill to execute. However, the hip-hinge movement pattern trained while performing a deadlift is and should be included in every workout program.

For those who cannot deadlift using the conventional pulling method, they can perform the exercise using one of the many deadlift variations out there.

Some of these deadlift variations include:

    Trap Bar Deadlift
    Romanian Deadlift
    Stiff Leg Deadlift
    Landmine Deadlift
    Dumbbell Deadlift
    Smith Machine Deadlift

Grip strength is key to being able to deadlift a lot of weight and there are several grip variations you can use while deadlifting including: an overhand grip, mixed grip, and the hook grip.
Conventional Deadlift Instructions

    Position the bar over the top of your shoelaces and assume a hip width stance.
    Push your hips back and hinge forward until your torso is nearly parallel with the floor.
    Reach down and grasp the bar using a shoulder width, double overhand grip.
    Inhale and pull up slightly on the bar while allowing your hips to drop in a seesaw fashion. This phenomenon is commonly referred to as “pulling the slack out of the bar”.
    As you drop the hips and pull up on the bar, set the lats (imagine you’re trying to squeeze oranges in your armpits) and ensure your armpits are positioned directly over the bar.
    Drive through the whole foot and focus on pushing the floor away.
    Ensure the bar tracks in a straight line as you extend the knees and hips.
    Once you have locked out the hips, reverse the movement by pushing the hips back and hinging forward.
    Return the bar to the floor, reset, and repeat for the desired number of repetitions.

"""
    val ex9String: String = """Dumbbell Lateral Raise Instructions

    The dumbbell lateral raise is a good exercise for building width in your upper body which gives you the "V" shape. Grab a set of dumbbells and stand straight up with the dumbbells at your sides.
    Your palms should be facing your body. You should be holding the dumbbells slightly off your body, as this keeps the tension on the side delts. This is your starting position for the exercise.
    To execute, slowly raise the dumbbells up to around shoulder height. It's important that you do not let your wrists go above your elbows while raising the weight, as this will take the work off the side delts and put it on the front delts.
    Pause at the top of the movement, and then slowly lower the weight back to the starting position.
    Do not let the dumbbells touch your body, and then raise them for the next rep.

Lateral Raise Tips:

    The dumbbell lateral raise is one of those exercises that so many people do incorrectly. First, this is an isolation exercise, so you should be focusing on stretch and muscle contraction, not using heavy weights.
    Second, you MUST keep your rep timing slow and controlled. So many people use momentum to swing heavy weights up, and this is not going to get you the best results from the dumbbell lateral raise.
    Third, it very important that your elbows stay above your wrists. If your wrists come up too far, the focus comes off your side delts and onto your front delts. A good trick to ensure this does not happen is to tilt the dumbbells down as if you were pouring a jug of water as you raise them up. This makes it very hard to raise the wrists higher than the elbows.
    And finally, keep the side delts under stress for the whole set by not allowing the dumbbells to touch your body or "hang" at the bottom of the movement.

"""
    val ex10String: String = """Lat Pull Down Overview

The lat pull down is an exercise used to build the muscles of the back. While the exercise will primarily target the lats, you will also notice a fair amount of bicep and middle back activation.

The back is a muscle group that requires a fair amount of variation. So, experiment with several different angles and hand positions to maximize your back muscle growth.

The lat pull down can be performed during your back workouts, upper body workouts, pull workouts, and full body workouts.
Lat Pull Down Instructions

    Attach a wide grip handle to the lat pulldown machine and assume a seated position.
    Grasp the handle with a pronated grip (double overhand) and initiate the movement by depressing the shoulder blade and then flexing the elbow while extending the shoulder.
    Pull the handle towards your body until the elbows are in line with your torso and then slowly lower the handle back to the starting position under control.
    Repeat for the desired number of repetitions.

"""
    val ex11String: String =
        """The leg press is a variation of the squat and an exercise used to target the muscles of the leg.

One can utilize the leg press to target both the quads and the hamstring muscle, depending on which portion of the foot they push through.

The leg press is commonly thought of as a machine variation of the barbell back squat. The mechanics are fairly similar, however, the leg press does not completely mimic the movement pattern of the squat. Nor does it work all of the muscle groups that the squat does.

The leg press is best used as an accessory movement to the squat, or as a primary movement in gyms which lack the necessary equipment to train the squat movement pattern.
Leg Press Instructions

    Load the machine with the desired weight and take a seat.
    Sit down and position your feet on the sled with a shoulder width stance.
    Take a deep breath, extend your legs, and unlock the safeties.
    Lower the weight under control until the legs are roughly 45 degrees or slightly below.
    Drive the weight back to the starting position by extending the knees but don’t forcefully lockout.
    Repeat for the desired number of repetitions.

"""
    val ex12String: String = """EZ Bar Curl Instructions

    The standing EZ bar curl is a variation of the barbell curl, but using an EZ bar. Grasp an EZ bar at around shoulder width apart using a underhand grip (palms facing up).
    Stand straight up, feet together (you may be more comfortable taking one foot back for stability), back straight, and with your arms fully extended. The bar should not be touching your body.
    Keeping your eyes facing forwards, elbows tucked in at your sides, and your body completely still, slowly curl the bar up.
    Squeeze your biceps hard at the top of the movement, and then slowly lower it back to the starting position.
    Repeat for desired reps.

EZ Bar Curl Tips:

    Use the EZ bar curl when you have had wrist injuries or if you feel pain in the wrists when doing barbell curls.
    Do not swing back when you curl the bar up. This is cheating.
    Keep your body fixed and elbows in at your sides throughout the movement.

"""
    val ex13String: String =
        """The EZ bar skullcrusher is a variation of the skullcrusher and an exercise used to strengthen the muscles of the triceps.

The triceps can be trained in many different ways to promote growth and overhead extensions, such as the EZ bar skullcrusher, are an effective way to target the long head of the tricep.

Having bigger and stronger triceps are not only important from an aesthetic standpoint but can also help contribute to better performance on pressing motions such as the bench press.
EZ Bar Skullcrusher Instructions

    Select your desired weight and sit on the edge of a flat bench.
    To get into position, lay back and keep the bar close to your chest. Once you are supine, press the weight to lockout.
    Lower the weights towards your head by unlocking the elbows and allowing the ez bar to drop toward your forehead or just above.
    Once your forearms reach parallel or just below, reverse the movement by extending the elbows while flexing the triceps to lockout the weight.
    Repeat for the desired number of repetitions.

"""
    val ex14String: String =
        """The plank (AKA hover) is an excellent exercise for building overall core strength. The video above demonstrates the most common plank variation. If you're new to training and do not have the core strength to do a full plank you can start with your knees on the floor and work up to knees off the floor as you get stronger.

You can make the plank harder by raising one foot of the floor. If that's still easy for you, raise one foot off the floor and the opposing hand at the same time. If you're in the gym you can also try weighted planks by adding weight plates to your back for additional resistence. Just make sure you do not let your midsection sag throughout the exercise.
Plank Instructions

    The Plank is a stationary exercise that helps strengthen the entire core of the body. Set up for the plank by getting a mat and laying down on your stomach.
    To start the exercise prop your torso up on your elbows and your feet up on your toes.
    Keeping yourself completely straight, hold this position for as long as possible.
    Typically, the plank is done for 3 x 1 minute sets. However, as you get stronger your should be able to do 1 - 3 minutes.

"""

    val wo1String =
        """The following workout, for those who only have access to a set of dumbbells, is a 5 day per week program.

It can be performed by those who work out at home in their home gym, travel frequently and need a go-to program they can do at a hotel gym, or anyone really who prefers to use dumbbells over other implements at the gym."""

    val wo2String =
        """As Shakespeare so beautifully put it, “to be or not to be, that is the question”.

However, for lifters it looks more like this:

“To train for strength or to train for hypertrophy, that is the question.”

It’s a dilemma we all face too often. But, who’s to say we can’t have it all? When I recently took a second to dissect my workout, I decided going forward I’ll always have a split that has it all and by sharing it, you’ll have it too. The next time you decide you want to build up your strength, without sacrificing your striations, return to this workout. """


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            if (USE_FIREBASE_EMULATOR) {
                FirebaseFirestore.getInstance().useEmulator("10.0.2.2", 8080)
                FirebaseAuth.getInstance().useEmulator("10.0.2.2", 9099)
            }
        }

        val firestore = FirebaseFirestore.getInstance()
        /*
    val exercises = listOf<Exercise>(
        Exercise(null, "One Arm Dumbbell Rows", ex1String),
        Exercise(null, "Dumbbell Arnold Press", ex2String),
        Exercise(null, "Incline Dumbbell Bench Press",ex3String),
        Exercise(null, "Chest Supported Dumbbell Row",ex4String),
        Exercise(null, "Dumbbell Pinwheel Curl",ex5String),
        Exercise(null, "Overhead Dumbbell Tricep Extension", ex6String),
        Exercise(null, "Dumbbell Shrug", ex7String),
        Exercise(null, "Deadlift", ex8String),
        Exercise(null, "Lateral Raise", ex9String),
        Exercise(null, "Pulldown", ex10String),
        Exercise(null, "Leg Press", ex11String),
        Exercise(null, "EZ Bar Curl", ex12String),
        Exercise(null, "Skullcrushers", ex13String),
        Exercise(null, "Plank", ex14String),
    )
    exercises.forEach {
        val docRef = firestore.collection("exercises").document()
        it.docId = docRef.id
        docRef.set(it)
    }

    var tempExercises = ArrayList<WorkoutUnit>()
    tempExercises.add(WorkoutUnit(exercises[0],4,8))
    tempExercises.add(WorkoutUnit(exercises[1],4,8))
    tempExercises.add(WorkoutUnit(exercises[2],4,10))
    tempExercises.add(WorkoutUnit(exercises[3],3,10))
    tempExercises.add(WorkoutUnit(exercises[4],2,10))
    tempExercises.add(WorkoutUnit(exercises[5],3,10))
    tempExercises.add(WorkoutUnit(exercises[6],3,12))
    var docRef = FirebaseFirestore.getInstance().collection("workouts").document()
    var woPlan = WorkoutPlan(docRef.id, null, tempExercises.toList(), "Upper Body Dumbbell Workout", wo1String, true)
    docRef.set(woPlan)

    tempExercises = ArrayList<WorkoutUnit>()
    tempExercises.add(WorkoutUnit(exercises[7],5,5))
    tempExercises.add(WorkoutUnit(exercises[2],4,10))
    tempExercises.add(WorkoutUnit(exercises[8],4,10))
    tempExercises.add(WorkoutUnit(exercises[9],4,10))
    tempExercises.add(WorkoutUnit(exercises[10],4,10))
    tempExercises.add(WorkoutUnit(exercises[11],3,10))
    tempExercises.add(WorkoutUnit(exercises[12],3,10))
    tempExercises.add(WorkoutUnit(exercises[6],3,12))
    tempExercises.add(WorkoutUnit(exercises[13],3,20))
    docRef = FirebaseFirestore.getInstance().collection("workouts").document()
    woPlan = WorkoutPlan(docRef.id, null, tempExercises.toList(), "The Total Package: Full Body Strength", wo2String, true)
    docRef.set(woPlan)*/
    }
}