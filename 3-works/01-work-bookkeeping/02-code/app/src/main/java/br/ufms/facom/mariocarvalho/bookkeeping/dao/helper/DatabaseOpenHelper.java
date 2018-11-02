
/*
 * Informações sobre a criação do arquivo.
 * Autores: Mário de Araújo Carvalho e Wiliams Magalhães Primo
 * E-mail: mariodearaujocarvalho@gmail.com
 * GitHub: https://github.com/MarioDeAraujoCarvalho
 * Ano: 13/5/2017
 * Entrar em contado para maiores informações.
 */

package br.ufms.facom.mariocarvalho.bookkeeping.dao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Essa classe serve de interface para criar e abrir o banco de dados.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper{
	/**
	 * Contexto da Classe
	 */
	private Context mContext;

	/**
	 * Versao do banco de dados
	 */
	public static final int DATA_VERSION_ID = 13;

	/**
	 * Nome do banco de dados
	 */
	public static final String DATABASE_NAME = "database_fast_framework_v1.db";

	/**
	 * Nome da tabela de itens.
	 */
	public static final String TABLE_ITENS = "tb_itens";

	/**
	 * Campos da tabela de itens.
	 */
	public static final String
			TABLE_ITENS_COLUMN_ID = "id",
			TABLE_ITENS_COLUMN_NOME = "nome",
			TABLE_ITENS_COLUMN_DESCRICAO = "descricao",
			TABLE_ITENS_COLUMN_IS_FAVORITE = "favorito";
	/**
	 * String SQL para a criacao da tabela de itens.
	 */
	public static final String SQL_CREATE_TABLE_ITENS = "CREATE TABLE " + TABLE_ITENS + " ( "

			+ TABLE_ITENS_COLUMN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ TABLE_ITENS_COLUMN_NOME + " TEXT , "
			+ TABLE_ITENS_COLUMN_DESCRICAO + " TEXT , "
			+ TABLE_ITENS_COLUMN_IS_FAVORITE + " TEXT  ) ";

	/**
	 * Construtor da classe.
	 */
	public DatabaseOpenHelper(Context context){
		super(context, DATABASE_NAME, null, DATA_VERSION_ID);
		mContext = context;
	}

	/**
	 * Esta funcao eh chamada para a criacao da base de dados, no caso de nao existir.
	 */
	@Override
	public void onCreate(SQLiteDatabase database){
		/*
		 * Executa a criacao de cada uma das tabela com base nas strings SQL definidas.
		 */
		database.execSQL(SQL_CREATE_TABLE_ITENS);
		Log.e("TABLE:", SQL_CREATE_TABLE_ITENS);

	}

	/**
	 * Esta funcao eh chamada no caso de a versao de ID mudar, para alterar a estrutura ou dropar os dados
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
		/*
		 * Apaga a tabela atual caso haja atualizacoes.
		 */
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_ITENS);

		/**
		 * Apos apagar a tabela do banco de dados eh necessario cria-la novamente.
		 */
		onCreate(database);
	}
}
