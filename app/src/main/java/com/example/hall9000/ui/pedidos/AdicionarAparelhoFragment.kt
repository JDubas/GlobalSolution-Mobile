package com.example.hall9000.ui.aparelhos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.hall9000.R
import com.example.hall9000.network.AdicionarAparelho
import com.example.hall9000.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdicionarAparelhoFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var powerEditText: EditText
    private lateinit var horaEditText: EditText
    private lateinit var frequencySpinner: Spinner
    private lateinit var saveButton: Button
    private val args by navArgs<AdicionarAparelhoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_adicionar_aparelho, container, false)

        super.onViewCreated(view, savedInstanceState)
        val usuarioId = args.usuarioId



        nameEditText = view.findViewById(R.id.nameEditText)
        powerEditText = view.findViewById(R.id.powerEditText)
        frequencySpinner = view.findViewById(R.id.frequencySpinner)
        saveButton = view.findViewById(R.id.saveButton)
        horaEditText = view.findViewById(R.id.horasEditText)




        if (usuarioId == null) {
            Toast.makeText(requireContext(), "Usuário não encontrado", Toast.LENGTH_SHORT).show()
            return view
        }


        val frequencyOptions = arrayOf("Diária", "Semanal", "Mensal")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, frequencyOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        frequencySpinner.adapter = adapter


        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val power = powerEditText.text.toString().toFloatOrNull()
            val frequency = frequencySpinner.selectedItem.toString()
            val hora = horaEditText.text.toString().toFloat()

            if (name.isBlank() || power == null) {
                Toast.makeText(requireContext(), "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            registrarAparelho(usuarioId!!.toString(), name, power.toString(), frequency, hora)


        }

        return view
    }


    private fun registrarAparelho(usuarioId: String, nome: String, potencia: String, frequenciaUso: String, hora: Float) {

        val request = AdicionarAparelho(usuarioId, nome, potencia, frequenciaUso, hora)


        RetrofitClient.apiService.registrarAparelho(request).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "Aparelho adicionado com sucesso", Toast.LENGTH_SHORT).show()
                    requireActivity().onBackPressed() // Voltar para a tela anterior
                } else {
                    Toast.makeText(requireContext(), "Erro ao adicionar aparelho", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(requireContext(), "Falha na conexão", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
