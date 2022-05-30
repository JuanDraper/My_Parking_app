package com.example.my_parking_app.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.my_parking_app.R

class DeleteDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View =  inflater.inflate(R.layout.delete_dialog_fragment, container, false)

        return rootView

    }
}