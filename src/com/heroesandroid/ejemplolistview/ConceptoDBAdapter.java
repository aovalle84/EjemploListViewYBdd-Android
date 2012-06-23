package com.heroesandroid.ejemplolistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class ConceptoDBAdapter {

	private static final String BASEDATOS_NOMBRE = "concepto.db";
	private static final int BASEDATOS_VERSION = 1;
	
	private static final String TABLA_NOMBRE = "Concepto";
	public static final String LLAVE_ID = "_id";
	public static final String LLAVE_NOMBRE = "nombre";
	public static final int COLUMNA_ID = 0;
	public static final int COLUMNA_NOMBRE = 1;
	
	private SQLiteDatabase mBaseDatos;
	private ConceptoDBOpenHelper mHelper; 
	
	public ConceptoDBAdapter(Context contexto) {
		mHelper = new ConceptoDBOpenHelper(contexto, BASEDATOS_NOMBRE, 
				null, BASEDATOS_VERSION);
		mBaseDatos = mHelper.getWritableDatabase();
	}
	
	public void cerrar() {
		mBaseDatos.close();
	}
	
	public void abrir()
			throws SQLiteException {
		try {
			mBaseDatos = mHelper.getWritableDatabase();
		} catch (SQLiteException ex) {
			mHelper.getReadableDatabase();
		}
	}
	
	public long agregar(String nombre) {
		ContentValues registro = new ContentValues();
		registro.put(LLAVE_NOMBRE, nombre);
		return mBaseDatos.insert(TABLA_NOMBRE, null, registro);
	}
	
	public Cursor consultarTodos() {
		return mBaseDatos.query(TABLA_NOMBRE, 
				new String[] { LLAVE_ID, LLAVE_NOMBRE }, 
				null, null, null, null, null);
	}
	
    public Cursor consultar(long id) throws SQLException {
        Cursor resultado = mBaseDatos.query(true, 
        		TABLA_NOMBRE, 
        		new String[] {LLAVE_ID, LLAVE_NOMBRE}, 
        		LLAVE_ID + "=" + id, 
        		null, null, null, null, null);
        if (resultado != null) {
        	resultado.moveToFirst();
        }
        return resultado;
    }
    
    public boolean actualizar(long id, String nombre) {
    	boolean actualizado = false;
        ContentValues registro = new ContentValues();
        registro.put(LLAVE_NOMBRE, nombre);
        
        int cantidadRegistrosAfectados = mBaseDatos.update(TABLA_NOMBRE, 
        		registro, 
        		LLAVE_ID + "=" + id, 
        		null);
        if (cantidadRegistrosAfectados > 0) {
        	actualizado = true;
        }

        return actualizado;
    }
    
	public void eliminar(int id) {
		String sql = "DELETE FROM " + TABLA_NOMBRE + " WHERE " + LLAVE_ID + " = " + id;
		mBaseDatos.execSQL(sql);
	}
	
	private static class ConceptoDBOpenHelper extends SQLiteOpenHelper {
		
		// Instruccón SQL par crear tabla
        private static final String SQL_CREAR_TABLA = "create table " +
        		TABLA_NOMBRE + " (" + LLAVE_ID + " integer primary key autoincrement, " +
        		LLAVE_NOMBRE + " text not null);";
		
		public ConceptoDBOpenHelper(Context contexto, String nombre,
				CursorFactory factory, int version) {
			super(contexto, nombre, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase basedatos) {
			basedatos.execSQL(SQL_CREAR_TABLA);
		}

		@Override
		public void onUpgrade(SQLiteDatabase basedatos, int versionAntigua, 
				int versionNueva) {
			basedatos.execSQL("DROP TABLE IF EXISTS " + TABLA_NOMBRE);
			onCreate(basedatos);
		}
		
	}
}
