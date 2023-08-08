package co.com.upb.volley_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etCurso: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegitrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniView()

        btnRegitrar.setOnClickListener {
            val name: String = etNombre.text.toString()
            val correo: String = etCorreo.text.toString()
            val curso: String = etCurso.text.toString()
            val pass: String = etPassword.text.toString()

            insertStudent(name, correo, curso, pass)
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

    private fun insertStudent(name: String, correo: String, curso: String, pass: String){
        val url: String = "http://10.8.102.152/apiAndroid/insert.php"
        val req: RequestQueue = Volley.newRequestQueue(this)
        val strReq: StringRequest = object: StringRequest(Method.POST, url, { response ->
            Toast.makeText(this, response.trim(), Toast.LENGTH_LONG).show()
        },{error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        }){
            override fun getParams(): MutableMap<String, String>? {
                var params = HashMap<String, String>()
                params.put("name", name)
                params.put("email", correo)
                params.put("curso", curso)
                params.put("password", pass)
                return params
            }
        }
        req.add(strReq)


    }

    private fun iniView(){
        etNombre = findViewById(R.id.etName)
        etCorreo = findViewById(R.id.etCorreo)
        etCurso = findViewById(R.id.etCurso)
        etPassword = findViewById(R.id.etCPassword)
        btnRegitrar = findViewById(R.id.btnRegistro)
    }
}