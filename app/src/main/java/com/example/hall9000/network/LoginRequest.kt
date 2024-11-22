package com.example.hall9000.network

import android.hardware.camera2.CameraExtensionSession.StillCaptureLatency
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

data class LoginRequest(val email: String, val senha: String)

data class AparelhoRequest(val usuario_id: String)

data class RegistrarRequest(val nome: String, val senha: String, val email: String)

data class UpdateRequest(val email: String, val senha: String, val nome: String)
data class UpdateAparelho(val APARELHO_ID: String, val NOME: String, val POTENCIA: String, val FREQUENCIA_USO: String, val HORA: String)
data class AdicionarAparelho(val usuario_id: String,val nome: String, val potencia: String, val frequencia_uso: String, val hora: Float)
data class senhaRequest(val email: String)

data class PedidoCompleto(
    val CLIENTEID: Int,
    val DATAPEDIDO: String,
    val NOME: String,
    val PEDIDOID: Int,
    val VALOR: String
)
data class LoginResponse(
    val email: String,
    val senha: String,
    val nome: String
)

data class LoginRetorno(
    val login_success: Boolean,
    val message: String,
    val usuario_id: String
)

data class AparelhoResponse(
    val APARELHO_ID: Int,
    val FREQUENCIA_USO: String,
    val NOME: String,
    val POTENCIA: Float,
    val CONSUMO_KWH: String,
    val CUSTO: String,
    val TEMPO_USO: String
)

data class SenhaResponse(
    val message: String,
    val senha: String
)




interface ApiService {
    @POST("login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginRetorno>

    @POST("add-usuario")
    fun registrarUser(@Body loginWithEmailRequest: RegistrarRequest): Call<Void>

    @POST("recover-password")
    fun recuperarSenha(@Body senhaRequest: senhaRequest): Call<SenhaResponse>

    @POST("add-aparelho")
    fun registrarAparelho(@Body AdicionarAparelho: AdicionarAparelho): Call<Void>

    @POST("get-aparelhos")
    fun getAparelhos(@Body AparelhoRequest: AparelhoRequest): Call<List<AparelhoResponse>>


    @DELETE("delete-aparelho")
    fun deleteAperelho(@Query("aparelho_id") aparelhoId: String): Call<Void>

    @PUT("edit-aparelho")
    fun updateAparelho(@Body UpdateAparelho: UpdateAparelho): Call<Void>



    @GET("pedidos_completos")
    fun getPedidos(): Call<List<PedidoCompleto>>

    @GET("recuperar-senha")
    fun getLogins(): Call<List<LoginResponse>>

    @PUT("login-update")
    fun updateUser(@Body updateRequest: UpdateRequest): Call<Void>

    @DELETE("login-delete")
    fun deleteUser(@Query("email") email: String): Call<Void>
}

