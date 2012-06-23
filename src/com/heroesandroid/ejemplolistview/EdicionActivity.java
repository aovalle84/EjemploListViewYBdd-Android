package com.heroesandroid.ejemplolistview;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EdicionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.concepto_edicion);
		//final long id = 1;
		
		Intent intentoRecibido = getIntent();
		final long id = intentoRecibido.getLongExtra(ConceptoDBAdapter.LLAVE_ID, -1);
		
		ConceptoDBAdapter adaptador = new ConceptoDBAdapter(this);
		Cursor cursor = adaptador.consultar(id);
		
		String nombre = "";
		if (cursor != null) {
			nombre = cursor.getString(
					cursor.getColumnIndexOrThrow(ConceptoDBAdapter.LLAVE_NOMBRE));
		}
		
		EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
		edtNombre.setText(nombre);
		
		
		Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
		btnGuardar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
				String nombreModificado = edtNombre.getText().toString();
				//Array
				//ConceptoAccesoDatos acceso = new ConceptoAccesoDatos();
				//acceso.(strNombre);
				//Base de Datos
				ConceptoDBAdapter mAdapter = new ConceptoDBAdapter(getBaseContext());
				mAdapter.actualizar(id, nombreModificado);
				finish();
			}
		});
		
		Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
		btnCancelar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
		
	}

}
