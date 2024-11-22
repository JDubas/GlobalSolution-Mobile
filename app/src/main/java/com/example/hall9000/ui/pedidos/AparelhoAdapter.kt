package com.example.hall9000.ui.pedidos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hall9000.databinding.ItemUserBinding
import com.example.hall9000.network.AparelhoResponse

class AparelhoAdapter(private val aparelhos: List<AparelhoResponse>, private val onEditClick: (AparelhoResponse) -> Unit, private val onDeleteClick: (AparelhoResponse) -> Unit) : RecyclerView.Adapter<AparelhoAdapter.AparelhoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AparelhoViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AparelhoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AparelhoViewHolder, position: Int) {
        val aparelho = aparelhos[position]
        holder.bind(aparelho)
    }

    override fun getItemCount(): Int {
        return aparelhos.size
    }

    inner class AparelhoViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(aparelho: AparelhoResponse) {
            binding.tvUserNome.text = aparelho.NOME
            binding.tvFrequencia.text = "Frequencia de uso: ${aparelho.FREQUENCIA_USO}"
            binding.tvPotencia.text = "Potencia: ${aparelho.POTENCIA}"
            binding.tvCusto.text = "Custo por mes: R$${aparelho.CUSTO}"
            binding.tvKWH.text = "Consumo de KWH: ${aparelho.CONSUMO_KWH}"
            binding.btnEditar.setOnClickListener { onEditClick(aparelho) }
            binding.btnDeletar.setOnClickListener { onDeleteClick(aparelho) }
        }
    }
}
