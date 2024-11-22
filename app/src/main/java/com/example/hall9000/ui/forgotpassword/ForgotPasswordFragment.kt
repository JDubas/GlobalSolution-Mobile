package com.example.hall9000.ui.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hall9000.R
import com.example.hall9000.network.SenhaResponse
import com.example.hall9000.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var buscarSenhaButton: Button
    private lateinit var senhaTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)


        emailEditText = view.findViewById(R.id.etEmail)
        buscarSenhaButton = view.findViewById(R.id.btnBuscarSenha)
        senhaTextView = view.findViewById(R.id.tvSenha)


        buscarSenhaButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            if (email.isNotEmpty()) {
                buscarSenha(email)
            } else {
                Toast.makeText(requireContext(), "Por favor, digite um e-mail", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun buscarSenha(email: String) {

        val senhaRequest = com.example.hall9000.network.senhaRequest(email)

        val call = RetrofitClient.apiService.recuperarSenha(senhaRequest)

        call.enqueue(object : Callback<SenhaResponse> {
            override fun onResponse(call: Call<SenhaResponse>, response: Response<SenhaResponse>) {
                if (response.isSuccessful) {
                    val senhaResponse = response.body()

                    if (senhaResponse != null) {
                        if (senhaResponse.senha.isNotEmpty()) {
                            senhaTextView.text = "Sua senha é: ${senhaResponse.senha}"
                        } else {
                            senhaTextView.text = "Mensagem: ${senhaResponse.message}"
                        }
                    } else {
                        Toast.makeText(requireContext(), "Erro ao recuperar a senha", Toast.LENGTH_SHORT).show()
                    }
                } else {

                    Toast.makeText(requireContext(), "Erro ao recuperar a senha", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SenhaResponse>, t: Throwable) {

                Toast.makeText(requireContext(), "Falha ao buscar a senha", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
