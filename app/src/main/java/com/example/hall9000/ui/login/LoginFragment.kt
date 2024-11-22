package com.example.hall9000.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hall9000.databinding.FragmentLoginBinding
import androidx.navigation.fragment.findNavController
import com.example.hall9000.R
import com.example.hall9000.network.LoginRequest
import com.example.hall9000.network.LoginRetorno
import com.example.hall9000.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextUsername.text.toString()
            val senha = binding.editTextPassword.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()) {
                loginUser(email, senha)
            } else {
                Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonLogin2.setOnClickListener({
            findNavController().navigate(R.id.navigation_esqueceu)
        })
        return binding.root
    }

    private fun loginUser(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        RetrofitClient.apiService.loginUser(loginRequest).enqueue(object : Callback<LoginRetorno> {
            override fun onResponse(call: Call<LoginRetorno>, response: Response<LoginRetorno>) {
                if (response.isSuccessful) {
                    val loginRetorno = response.body()
                    if (loginRetorno != null && loginRetorno.login_success) {
                        val action = LoginFragmentDirections
                            .actionNavigationLoginToNavigationPedidos(usuarioId = loginRetorno.usuario_id.toInt())
                        findNavController().navigate(action)
                    } else {
                        Toast.makeText(context, loginRetorno?.message ?: "Erro no login", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Erro na requisição: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginRetorno>, t: Throwable) {
                Toast.makeText(context, "Falha na conexão: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }



}
