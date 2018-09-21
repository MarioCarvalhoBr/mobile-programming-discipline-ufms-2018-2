package br.ufms.facom.mariocarvalho.p_12_sqlite_login;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    // Instância do database
    SQLiteDatabase mDatabase;
    Context mContext;
    //Constatntes referente ao banco de dados
    private static final String DATABASE_NAME = "my_database_contact.db";
    private static final int DATABASE_VERSION = 1;

    /** Constatntes referente a tabela: contato*/
    private static final String TABLE_CONTACTS_NAME = "contact"; // Nome da tabela
    private static final String TABLE_CONTACTS_COLUM_ID = "id";
    private static final String TABLE_CONTACTS_COLUM_NAME = "name";
    private static final String TABLE_CONTACTS_COLUM_EMAIL = "email";
    private static final String TABLE_CONTACTS_COLUM_USER = "user";
    private static final String TABLE_CONTACTS_COLUM_PASSWORD = "password";

    /** SQL para criaçao da tablea contatos*/
    private static final String SQL_CREATE_TABLE_CONTACTS = " CREATE TABLE "
            + TABLE_CONTACTS_NAME + " ( "
            + TABLE_CONTACTS_COLUM_ID + " integer PRIMARY KEY AUTOINCREMENT, "
            + TABLE_CONTACTS_COLUM_NAME + " TEXT NOT NULL,"
            + TABLE_CONTACTS_COLUM_EMAIL + " TEXT NOT NULL,"
            + TABLE_CONTACTS_COLUM_USER + " TEXT NOT NULL,"
            + TABLE_CONTACTS_COLUM_PASSWORD + " TEXT NOT NULL ) ; " ;

    public DBHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TAG", "onCreate");
        db.execSQL(SQL_CREATE_TABLE_CONTACTS);
        this.mDatabase = db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        Log.i("TAG", "onUpgrade");
        Log.i("TAG", "Versão antiga: " + versaoAntiga);
        Log.i("TAG", "Versão nova: " + versaoNova);
        String query = "DROP TABLE IF EXISTS "+ TABLE_CONTACTS_NAME ;
        mDatabase.execSQL(query);
        this.onCreate(mDatabase);
    }

    // ContactDAO - CRUD
    public void insert(Contact contact){
        mDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_CONTACTS_COLUM_NAME, contact.getName());
        values.put(TABLE_CONTACTS_COLUM_EMAIL, contact.getEmail());
        values.put(TABLE_CONTACTS_COLUM_USER, contact.getUser());
        values.put(TABLE_CONTACTS_COLUM_PASSWORD, contact.getPassword());

        long i = mDatabase.insert(TABLE_CONTACTS_NAME, null,values );
        mDatabase.close();
        if(i > 0){
            Log.i("TAG","Sucesso ao inserir no banco de dados");
        }else {
            Log.e("TAG","Erro ao inserir no banco de dados");
        }
    }
    public String getPassword(String user) {
        mDatabase = this.getWritableDatabase();
        String query = "SELECT "+TABLE_CONTACTS_COLUM_USER+", " +TABLE_CONTACTS_COLUM_PASSWORD+ " FROM " + TABLE_CONTACTS_NAME + ";";
        Cursor cursor = mDatabase.rawQuery(query,null);
        String a, b;
        b="Não encontrado";
        if(cursor.moveToFirst()){
            do {
                a = cursor.getString((cursor.getColumnIndex(TABLE_CONTACTS_COLUM_USER)));
                if (a.equals(user)) {
                    b = cursor.getString((cursor.getColumnIndex(TABLE_CONTACTS_COLUM_PASSWORD)));
                    break;
                }
            } while (cursor.moveToNext());
        }
        return  b;
    }

}
