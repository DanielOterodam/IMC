package com.example.indicedemasacorporal;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String LOGTAG="MainActivity";
	
	private IndiceMasaCorporal imc;
	EditText editPeso;
	EditText editAltura;
	TextView textViewResultado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editPeso = (EditText) findViewById(R.id.editPeso);
		editAltura = (EditText) findViewById(R.id.editAltura);
		textViewResultado = (TextView) findViewById(R.id.textViewResultado);

	}

	/**
	 * onButtonClickCalcularIMC
	 */
	public void onButtonClickCalcularIMC(View v) {

		String peso;
		String altura;
		String resultado;
		try {

			peso = editPeso.getText().toString();
			altura = editAltura.getText().toString();

			imc = new IndiceMasaCorporal(peso, altura);
			
			resultado = "Valor IMC = " + imc.valorIndiceMasaCorporal() + " - ";
			resultado = resultado.concat(imc.clasificacionOMS());
			textViewResultado.setText(resultado);
			Log.e(LOGTAG, imc.toString());
			editPeso.setBackgroundColor(Color.WHITE);
			editAltura.setBackgroundColor(Color.WHITE);
			
		} catch (IndiceMasaCorporalException e) {
			if (e.isErrorPeso()) {
				editPeso.setBackgroundColor(Color.LTGRAY);
				String texto = "Error en el peso, debe de estar entre 5 y 200";
				Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();}
			if (e.isErrorAltura()){
				editAltura.setBackgroundColor(Color.LTGRAY);
				String texto = "Error en la altura, debe de estar entre 50 y 230";
				Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Log.e(LOGTAG, e.getMessage());
		}
	}
	
	public void onButtonAcercaDe(View v){
		Intent i = new Intent(this, Acerca_de.class);
		startActivity(i);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
