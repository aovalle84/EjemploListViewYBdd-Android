package com.heroesandroid.ejemplolistview;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class ConceptoAccesoDatos {
    private static ArrayList<String> mArrConcepto = new ArrayList<String>();
    
    private static ArrayAdapter<String> mAdaptadorVista;
    
    public static void inicializar(Context context) {
    	mAdaptadorVista = new ArrayAdapter<String>(context, 
        		R.layout.concepto_lista_item, mArrConcepto);
    }
    
    public static void finalizar() {
    	mArrConcepto = null;
    }
    
    public ConceptoAccesoDatos() {
		// No hace nada
	}
    
    public ListAdapter obtenerAdaptador() {
    	return mAdaptadorVista;
    }
    
    public void agregar(String nombre) {
		mArrConcepto.add(nombre);
		mAdaptadorVista.notifyDataSetChanged();
    }
    
}
