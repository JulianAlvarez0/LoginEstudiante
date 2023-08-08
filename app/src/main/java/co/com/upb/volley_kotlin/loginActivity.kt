package co.com.upb.volley_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class loginActivity : AppCompatActivity() {

    private lateinit var etEmailEst: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

        btnLogin.setOnClickListener {
            val emailEst = etEmailEst.text.toString()
            val passEst = etPassword.text.toString()
            loginEst(emailEst, passEst)
        }
        btnRegistrar.setOnClickListener { val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() }

    }

    private fun loginEst(emailEst: String, passEst: String) {
        val url = "http://10.8.102.152/apiAndroid/login.php"
        val reqQueue = Volley.newRequestQueue(this)
        val strReq = object: StringRequest(Method.POST, url,{
            if(it.trim().equals("c-Aprobado")){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()

            }
        }, {
            Toast.makeText(this, "ERROR! ${it.message}", Toast.LENGTH_LONG).show()
        }){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params.put("email", emailEst)
                params.put("password", passEst)
                return params
            }
        }
        reqQueue.add(strReq)
    }

    private  fun initView(){
        etEmailEst = findViewById(R.id.etEmailEst)
        etPassword = findViewById(R.id.etPasswordEst)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegistrar = findViewById(R.id.btnRegistro2)
    }

}