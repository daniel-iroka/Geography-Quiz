package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

// source of the message to be displayed in the logcat
private const val TAG = "QuizViewModel"

// ViewModel class - The purpose of the ViewModel is to acquire and keep the information that is necessary for an Activity or a Fragment.
// That means that a ViewModel will not be destroyed if the owner is destroyed for a configuration change(Rotation)
// So ViewModel simply "saves" or "takes" the state of a lifecycle into account
class QuizViewModel : ViewModel() {

    init {
        Log.d(TAG, "ViewModel instance created")
    }

    // The onCleared function is called when the ViewModel is about to be destroyed so that you can observe
    // the current state of the activity(lifecycle)
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }

}