package com.example.hall9000.ui.pedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.hall9000.R
import com.example.hall9000.network.UpdateAparelho
import com.example.hall9000.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditAparelhoFragment : Fragment() {

    private val args: EditAparelhoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_user, container, false)

        val nameEditText = view.findViewById<EditText>(R.id.nameEditText)
        val powerEditText = view.findViewById<EditText>(R.id.powerEditText)
        val horaEditText = view.findViewById<EditText>(R.id.horasEditText)
        val frequencySpinner = view.findViewById<Spinner>(R.id.frequencySpinner)
        val saveButton = view.findViewById<Button>(R.id.saveButton)


        nameEditText.setText(args.aparelhoNome)
        powerEditText.setText(args.aparelhoPotencia.toString())
        horaEditText.setText(args.hora)


        val frequencies = arrayOf("Diário", "Semanal", "Mensal")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, frequencies)
        frequencySpinner.adapter = adapter
        frequencySpinner.setSelection(frequencies.indexOf(args.frequenciaUso))

        saveButton.setOnClickListener {

            val nome = nameEditText.text.toString()
            val potencia = powerEditText.text.toString()
            val hora = horaEditText.text.toString()
            val frequenciaUso = frequencySpinner.selectedItem.toString()

            if (nome.isEmpty() || potencia.isEmpty() || hora.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val updateAparelho = UpdateAparelho(
                APARELHO_ID = args.aparelhoId.toString(),
                NOME = nome,
                POTENCIA = potencia,
                FREQUENCIA_USO = frequenciaUso,
                HORA = hora
            )


            RetrofitClient.apiService.updateAparelho(updateAparelho).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "Aparelho atualizado com sucesso!", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(requireContext(), "Erro ao atualizar o aparelho", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(requireContext(), "Falha na conexão com o servidor", Toast.LENGTH_SHORT).show()
                }
            })
        }

        return view
    }
}
