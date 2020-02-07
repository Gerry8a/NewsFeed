package com.gerardochoa.newsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    private SharedPreferences preferences;

    private EditText etPassword, etEmail;
    private Button btnLogin;
    private Switch switchRemember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarComponentes();
        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        colocarDatos();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if(login(email, password)){
                    accederFeed();
                    guardarPreferencias(email, password);
                }
            }
        });

    }


    /**
     * Método para inicializar los componenter gráficos de la actividad.
     */
    private void iniciarComponentes() {
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etMail);
        btnLogin = findViewById(R.id.btLogin);
        switchRemember = findViewById(R.id.switch1);
    }

    /**
     * Validar que la cadena introducida en el campo de email sea un email.
     * @param email
     * @return
     */
    private boolean validarCorreo(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches(); //vaidar correo con TextUtils
    }

    /**
     * Validar que la cadena en password cumpla los requerimientos.
     * @param password
     * @return
     */
    private boolean validarPassword(String password){
        return password.length() > 4;
    }


    private boolean login(String email, String password){
        if(!validarCorreo(email)){
            Toast.makeText(this, getString(R.string.invalid_mail), Toast.LENGTH_SHORT).show();
            return false;
        } else if(!validarPassword(password)){
            Toast.makeText(this, getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Mpetodo para acceder a la actividad una vez iniciada sesión
     */
    private void accederFeed(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //Acceder y si regresa, cerrar aplicación.
        startActivity(intent);
    }

    /**
     * Método para poder guardar los valores escritos en los campos de email y password
     * @param email
     * @param password
     */
    private void guardarPreferencias(String email, String password){
        if(switchRemember.isChecked()){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", email);
            editor.putString("pass", password);
            editor.commit();
            editor.apply();
        }
    }

    private void colocarDatos() {
        String email = obtenerMail();
        String password = obtenerPass();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            etEmail.setText(email);
            etPassword.setText(password);
        }
    }

    /**
     * Método para asignar el correo en la actividad si los datos están guardados
     * @return
     */
    private String obtenerMail(){
        return preferences.getString("email", "");
    }

    /**
     * Método para asignar el correo en la actividad si los datos están guardados
     * @return
     */
    private String obtenerPass(){
        return preferences.getString("pass", "");
    }
}
