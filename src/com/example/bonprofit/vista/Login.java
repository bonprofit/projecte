package com.example.bonprofit.vista;

import com.example.bonprofit.R;
import com.example.bonprofit.controlador.PeticioHttpLogin;

import android.os.Bundle;
import android.app.Activity;

import android.view.View;

import android.widget.EditText;

public class Login extends Activity {
	private EditText nom;
	private EditText contrasenya;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		nom = (EditText) findViewById(R.id.editNom);
		contrasenya = (EditText) findViewById(R.id.editContra);

	}

	public void btnEntrar(View view) {

		new PeticioHttpLogin(this).execute(nom.getText().toString(),
				contrasenya.getText().toString());

	}

}
