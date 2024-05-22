
package com.example.bumiajimobileview
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
        holder.textBerat.text = "Berat: ${currentItem.berat}"
        holder.textPanjang.text = "Panjang: ${currentItem.panjang}"
        holder.textLebar.text = "Lebar: ${currentItem.lebar}"
        holder.textTinggi.text = "Tinggi: ${currentItem.tinggi}"
        holder.textTimestamp.text = "Timestamp: ${currentItem.timestamp}"
        holder.textMachineId.text = "Machine ID: ${currentItem.machine_id}"
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}