package com.heroesandroid.ejemplolistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class EjemploListViewActivity extends Activity {
	private  ListView lstListaConcepto;
	//Arreglo
	//private ConceptoAccesoDatos mAcceso;
	//Base de Datos
	private ConceptoDBAdapter mAcceso;
	private CursorAdapter adapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), AdicionActivity.class);
				startActivity(intent);
			}
		});
        
      
        //Arreglo
        //ConceptoAccesoDatos.inicializar(this);
        //mAcceso = new ConceptoAccesoDatos();
        //Base de Datos
        mAcceso = new ConceptoDBAdapter(this);
        lstListaConcepto = (ListView) findViewById(R.id.lstListaConcepto);
        //Arreglo
        //lstListaConcepto.setAdapter(mAcceso.obtenerAdaptador());
        //Base de Datos
        adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,mAcceso.consultarTodos(),
        		new String[] {ConceptoDBAdapter.LLAVE_NOMBRE},new int[] {android.R.id.text1});
        lstListaConcepto.setAdapter(adapter);
        
        lstListaConcepto.setTextFilterEnabled(true);
        lstListaConcepto.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				String strMensaje = ((TextView) view).getText().toString();
				strMensaje = id + "," + position + ": " + strMensaje;
				//Toast.makeText(getBaseContext(), strMensaje, 
				//		Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(getBaseContext(), EdicionActivity.class);
				intent.putExtra(ConceptoDBAdapter.LLAVE_ID, id);
				startActivity(intent);
			}
		});
    }  // onCreate

    /** Se volverá a asignar el adaptador para actualizar las el ListView. */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//Base de Datos
		adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,mAcceso.consultarTodos(),
        		new String[] {ConceptoDBAdapter.LLAVE_NOMBRE},new int[] {android.R.id.text1});
	    lstListaConcepto.setAdapter(adapter);
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		ConceptoAccesoDatos.finalizar();
	}
    
}