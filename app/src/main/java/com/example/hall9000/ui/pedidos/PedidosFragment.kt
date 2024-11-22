package com.example.hall9000.ui.pedidos

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hall9000.R
import com.example.hall9000.network.AparelhoRequest
import com.example.hall9000.network.AparelhoResponse
import com.example.hall9000.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PedidosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var aparelhoAdapter: AparelhoAdapter

    private val args: PedidosFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)


        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)


        getAparelhos()

        view.findViewById<Button>(R.id.buttonteste).setOnClickListener {

            val action = PedidosFragmentDirections
                .actionPedidosFragmentToAdicionarAparelhoFragment(usuarioId = args.usuarioId)
            findNavController().navigate(action)
        }

        return view
    }
    private fun getAparelhos() {

        val usuarioId = args.usuarioId
        val call = RetrofitClient.apiService.getAparelhos(AparelhoRequest(usuarioId.toString()))
        call.enqueue(object : Callback<List<AparelhoResponse>> {
            override fun onResponse(call: Call<List<AparelhoResponse>>, response: Response<List<AparelhoResponse>>) {
                if (response.isSuccessful) {
                    val aparelhos = response.body() ?: return


                    aparelhoAdapter = AparelhoAdapter(aparelhos, { aparelho ->

                        val action = PedidosFragmentDirections.actionPedidosFragmentToEditAparelhoFragment(
                            aparelhoId = aparelho.APARELHO_ID,
                            aparelhoNome = aparelho.NOME,
                            aparelhoPotencia = aparelho.POTENCIA,
                            frequenciaUso = aparelho.FREQUENCIA_USO,
                            hora = aparelho.TEMPO_USO ?: "0"
                        )
                        findNavController().navigate(action)
                    }, { aparelho ->

                        deleteAparelho(aparelho.APARELHO_ID.toString())
                        getAparelhos();
                    })
                    recyclerView.adapter = aparelhoAdapter
                }
            }

            override fun onFailure(call: Call<List<AparelhoResponse>>, t: Throwable) {

            }
        })
    }
    private fun deleteAparelho(aparelhoId: String) {

        AlertDialog.Builder(requireContext())
            .setTitle("Confirmação")
            .setMessage("Você tem certeza que deseja deletar este aparelho?")
            .setPositiveButton("Sim") { dialog, which ->

                val call = RetrofitClient.apiService.deleteAperelho(aparelhoId)
                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {

                            getAparelhos()
                            Toast.makeText(context, "Aparelho deletado com sucesso!", Toast.LENGTH_SHORT).show()
                        } else {
                            // Mostrar mensagem de erro caso a exclusão falhe
                            Toast.makeText(context, "Erro ao deletar o aparelho", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {

                        Toast.makeText(context, "Falha ao conectar-se ao servidor", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            .setNegativeButton("Não") { dialog, which ->

                dialog.dismiss()
            }
            .show()
    }

}
