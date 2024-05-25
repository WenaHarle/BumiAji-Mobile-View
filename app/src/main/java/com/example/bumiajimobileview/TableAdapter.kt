
package com.example.bumiajimobileview
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TableAdapter(private val dataList: List<Comments>) :
    RecyclerView.Adapter<TableAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textId: TextView = itemView.findViewById(R.id.text_id)
        val textGrade: TextView = itemView.findViewById(R.id.text_grade)
        val textBerat: TextView = itemView.findViewById(R.id.text_berat)
        val textPanjang: TextView = itemView.findViewById(R.id.text_panjang)
        val textLebar: TextView = itemView.findViewById(R.id.text_lebar)
        val textTinggi: TextView = itemView.findViewById(R.id.text_tinggi)
        val textTimestamp: TextView = itemView.findViewById(R.id.text_timestamp)
        val textMachineId: TextView = itemView.findViewById(R.id.text_machine_id)
        val photoAtas: ImageView = itemView.findViewById(R.id.photoAtas)
        val photoSamping: ImageView = itemView.findViewById(R.id.photoSamping)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.table_row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.textId.text = "ID: ${currentItem.id}"
        holder.textGrade.text = "Grade: ${currentItem.grade}"
        holder.textBerat.text = "Weight: ${currentItem.berat}"
        holder.textPanjang.text = "Lenght: ${currentItem.panjang}"
        holder.textLebar.text = "Width: ${currentItem.lebar}"
        holder.textTinggi.text = "Height: ${currentItem.tinggi}"
        holder.textTimestamp.text = "Timestamp: ${currentItem.timestamp}"
        holder.textMachineId.text = "Machine ID: ${currentItem.machine_id}"

        val newDomain = "rarief.com"
        val newUrl1 = changeUrl(currentItem.photo_atas_url, newDomain)
        val newUrl2 = changeUrl(currentItem.photo_samping_url, newDomain)

        Picasso.get()
            .load(newUrl1)
            .into(holder.photoAtas)

        Picasso.get()
            .load(newUrl2)
            .into(holder.photoSamping)

    }

    fun changeUrl(oldUrl: String, newDomain: String): String {
        val regex = Regex("http://[^/]+/")
        return oldUrl.replace(regex, "http://$newDomain/")
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}