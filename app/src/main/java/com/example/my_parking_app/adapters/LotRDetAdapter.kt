package com.example.my_parking_app.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.my_parking_app.*
import com.example.my_parking_app.ViewHolders.LotResDetViewHolder
import com.example.my_parking_app.Views.DeleteDialogFragment
import com.example.my_parking_app.RerservationDetails

class LotRDetAdapter(private val RDList: List<RerservationDetails>, private val onItemClicked: OnItemClicked, private val compat : AppCompatActivity ):
    RecyclerView.Adapter<LotResDetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotResDetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lot_reservation_detail, parent, false)
        return LotResDetViewHolder(view)

    }

    override fun onBindViewHolder(holder: LotResDetViewHolder, position: Int) {

        val details = RDList[position]
        holder.bind(details)
        holder.DeleteBTn.setOnClickListener {
            var dialog = DeleteDialogFragment()

            dialog.show(compat.supportFragmentManager, "smth")
        }
    }



    override fun getItemCount(): Int {
        return RDList.size
    }
    private fun removeItem(position: Int) {
        val list = mutableListOf<RerservationDetails>()
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}

