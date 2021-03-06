package com.example.stepbook.training.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class EnterExerciseDataDialogFragment(val positiveListener: (Int) -> Unit) : DialogFragment() {
    companion object {
        val TAG = "EnterExerciseDataDialogFragment"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        val np = EditText(context)
        np.inputType = InputType.TYPE_CLASS_NUMBER
        return AlertDialog.Builder(requireContext())
            .setMessage("Enter a value")
            .setView(np)
            .setPositiveButton("OK") { _, _ ->
                if (np.text.isNotBlank()) positiveListener(np.text.toString().toInt())
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .create()
    }
}