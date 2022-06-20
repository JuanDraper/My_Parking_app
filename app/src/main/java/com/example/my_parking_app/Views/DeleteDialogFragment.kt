package com.example.my_parking_app.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.domain.entities.ReservationDetails
import com.example.my_parking_app.R
import com.example.my_parking_app.tools.DeleteDialogCallBack

class  DeleteDialogFragment(private val dialogCallBack: DeleteDialogCallBack,
                           private val reservation: ReservationDetails
) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.delete_dialog_fragment, container, false)

        rootView.findViewById<View>(R.id.cancelButton).setOnClickListener { dismiss() }

        rootView.findViewById<View>(R.id.deleteButton).setOnClickListener {
            val authorizationText = rootView.findViewById<EditText>(R.id.authCode).text
            dialogCallBack.onDeleteClicked(authorizationText.toString(), reservation)
            dismiss()
        }
        return rootView
    }

    companion object {
        fun newInstance(
            dialogCallBack: DeleteDialogCallBack,
            reservation: ReservationDetails
        ): DeleteDialogFragment {
            return DeleteDialogFragment(dialogCallBack, reservation)
        }
    }
}