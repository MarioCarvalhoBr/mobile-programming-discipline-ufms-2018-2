package br.ufms.facom.mariocarvalho.bookkeeping.dao.extras;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.ufms.facom.mariocarvalho.bookkeeping.dao.helper.DatabaseOpenHelper;

/**
 * Created by MARIO on 11/10/2016.
 */

public class DatabaseExtras {

    protected SQLiteDatabase mSQLiteDatabase;
    protected DatabaseOpenHelper mDatabaseOpenHelper;
    private Context mContext;

    public DatabaseExtras(Context context) {
        this.mContext = context;
        mDatabaseOpenHelper = new DatabaseOpenHelper(mContext);
    }

    public void open() throws SQLException {
        mSQLiteDatabase = mDatabaseOpenHelper.getWritableDatabase();
    }

    public void close() {
        mDatabaseOpenHelper.close();
    }
    /*
     *  Limpa todos os dados do banco
     */
    public void clear(){
        mSQLiteDatabase.delete(DatabaseOpenHelper.TABLE_ITENS, null, null);
        Log.e("LOG: ","Limpou a base de dados.");
    }
    /**
     * Testa a base de dados!
     */
    public void test(){
        mSQLiteDatabase.rawQuery("SELECT * FROM "+DatabaseOpenHelper.TABLE_ITENS +" ; ", null);
        Log.e("LOG: ","Base de dados testada com sucesso.");
    }

}
