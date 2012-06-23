package com.heroesandroid.ejemplolistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AdicionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.concepto_edicion);
		
		Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
		btnGuardar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
				String strNombre = edtNombre.getText().toString();
				//Array
				//ConceptoAccesoDatos acceso = new ConceptoAccesoDatos();
				//acceso.agregar(strNombre);
				//Base de Datos
				ConceptoDBAdapter mAdapter = new ConceptoDBAdapter(getBaseContext());
				mAdapter.agregar(strNombre);
				edtNombre.setText("");
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
