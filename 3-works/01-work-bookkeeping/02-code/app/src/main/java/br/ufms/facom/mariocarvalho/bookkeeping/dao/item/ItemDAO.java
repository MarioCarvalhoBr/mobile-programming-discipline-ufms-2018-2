/*
 * Informações sobre a criação do arquivo.
 * Autor: Mário de Araújo Carvalho
 * E-mail: mariodearaujocarvalho@gmail.com
 * GitHub: https://github.com/MarioDeAraujoCarvalho
 * Ano: 13/5/2017
 * Entrar em contado para maiores informações.
 */
package br.ufms.facom.mariocarvalho.bookkeeping.dao.item;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufms.facom.mariocarvalho.bookkeeping.dao.helper.DatabaseOpenHelper;
import br.ufms.facom.mariocarvalho.bookkeeping.model.Item;
/**
 * Classe que contem uma interface para manipulacao e acesso ao dados das tabelas do banco
 * @author  Mário de Araújo Carvalho.
 */
public class ItemDAO {
	protected SQLiteDatabase database;
	protected DatabaseOpenHelper helper;
	//Nomes de todas a colunas da tabela Item
	protected static final String TABLE_ITENS_COLUMNS[] = {

			DatabaseOpenHelper.TABLE_ITENS_COLUMN_ID,
			DatabaseOpenHelper.TABLE_ITENS_COLUMN_NOME,
			DatabaseOpenHelper.TABLE_ITENS_COLUMN_DESCRICAO,
			DatabaseOpenHelper.TABLE_ITENS_COLUMN_IS_FAVORITE,
	};
    private Context mContext;
	public ItemDAO(Context context){
		helper = new DatabaseOpenHelper(context);
		mContext = context;
	}
	public void open() throws SQLException {
		database = helper.getWritableDatabase();
	}
	public void close(){
		helper.close();
	}
	/**
	 * Salva item no banco de dados. Caso o item não exista no banco de
	 * dados, ele o adiciona. Caso o item exista no banco de dados, apenas
	 * atualiza os valores dos campos modificados.
	 *
	 * @param item
	 */
	public void salva(Item item) {
		/**
		 * Se o ID do item é nulo é porque ele ainda não existe no banco de
		 * dados, logo subentende-se que queremos adicionar o item no banco de
		 * dados. Sendo assim, chamaremos o método adiciona() já definido no
		 * DAO.
		 */

		Item mItem = getItem(item.getId());

		if ( mItem == null) {

			//Log.e("TAG","NÃO EXISTE. CRIA UM NOVO!");
			insert(item);
			/**
			 * Caso o item possua um ID é porque ele já existe no banco de
			 * dados, logo subentende-se que queremos alterar seus dados no
			 * banco de dados. Sendo assim, chamaremos o método atualiza() já
			 * definido no DAO.
			 */
		} else {
			update(item, item.getId());
			//Log.e("TAG","EXISTE. ATUALIZA O EXISTENTE!");
		}
	}

	/**
	 * Inseri uma novo item no banco de dados.
	 */
	public boolean insert(Item item){
		ContentValues values = new ContentValues();
		//Item
		values.put(DatabaseOpenHelper.TABLE_ITENS_COLUMN_NOME, item.getNome());
		values.put(DatabaseOpenHelper.TABLE_ITENS_COLUMN_DESCRICAO, item.getDescricao());
		values.put(DatabaseOpenHelper.TABLE_ITENS_COLUMN_IS_FAVORITE, "0");

		if(database.insert(DatabaseOpenHelper.TABLE_ITENS, null, values) == -1){
			return false;
		}
		return true;
	}

	/**
	 * Inseri uma copia de um item no banco de dados.
	 */
	public int insertCopy(Item item){
		ContentValues values = new ContentValues();
		//Item
		values.put(DatabaseOpenHelper.TABLE_ITENS_COLUMN_NOME, item.getNome());
		values.put(DatabaseOpenHelper.TABLE_ITENS_COLUMN_DESCRICAO, item.getDescricao());
		values.put(DatabaseOpenHelper.TABLE_ITENS_COLUMN_IS_FAVORITE, item.getFavorito());

		int idInsert = (int) database.insert(DatabaseOpenHelper.TABLE_ITENS, null, values);
		if( idInsert != -1){
			return idInsert;
		}
		return -1;
	}
	/**
	 * Atualiza os dados de um item ja cadastrado.
	 */
	public boolean update(Item item, int codigo){
		ContentValues values = new ContentValues();
		//Item
		values.put(DatabaseOpenHelper.TABLE_ITENS_COLUMN_ID, item.getId());
		values.put(DatabaseOpenHelper.TABLE_ITENS_COLUMN_NOME, item.getNome());
		values.put(DatabaseOpenHelper.TABLE_ITENS_COLUMN_DESCRICAO, item.getDescricao());
		
		if(database.update(DatabaseOpenHelper.TABLE_ITENS, values, DatabaseOpenHelper.TABLE_ITENS_COLUMN_ID + " = '" + codigo +"'", null) == -1){
			return false;
		}
		return true;
	}
	/**
	 * Atualiza os dados de um item ja cadastrado.
	 */
	public boolean updateIsFavorite(Item item) {
		ContentValues values = new ContentValues();
		//Item
		values.put(DatabaseOpenHelper.TABLE_ITENS_COLUMN_IS_FAVORITE,  item.getFavorito());

		if(database.update(DatabaseOpenHelper.TABLE_ITENS, values, DatabaseOpenHelper.TABLE_ITENS_COLUMN_ID + " = '" + item.getId() +"'", null) == -1){
			return false;
		}

		return true;
	}
	/**
	 * Deleta um item cadastrado com base em seu codigo de identificacao  (ID).
	 */
	public void delete(int codigo){
		database.delete(DatabaseOpenHelper.TABLE_ITENS, DatabaseOpenHelper.TABLE_ITENS_COLUMN_ID + " = '" + codigo + "'", null);
	}
	/**
	 * Retorna todos os itens cadastrados.
	 */
	public List<Item> getAllItens(){
		Cursor cursor = database.query(DatabaseOpenHelper.TABLE_ITENS, TABLE_ITENS_COLUMNS, null, null, null, null, DatabaseOpenHelper.TABLE_ITENS_COLUMN_NOME + " ASC ");
		cursor.moveToFirst();

		List<Item> items = new ArrayList<Item>();
		while(!cursor.isAfterLast()){
			items.add(cursorForItem(cursor));
			cursor.moveToNext();
		}
		cursor.close();
		return items;
	}
	/**
	 * Retorna todos os itens favoritos.
	 */
	public List<Item> getAllItensFavorites(){
		Cursor cursor = database.query(DatabaseOpenHelper.TABLE_ITENS, TABLE_ITENS_COLUMNS, DatabaseOpenHelper.TABLE_ITENS_COLUMN_IS_FAVORITE +" = '1';"  , null, null, null, DatabaseOpenHelper.TABLE_ITENS_COLUMN_NOME + " ASC ");
		cursor.moveToFirst();

		List<Item> items = new ArrayList<Item>();
		while(!cursor.isAfterLast()){
			items.add(cursorForItem(cursor));
			cursor.moveToNext();
		}
		cursor.close();
		return items;
	}

	/**
	 * @name: getItem
	 * @escope: public Item getItem(int codigo)
	 * @description: Recebe um codigo como parametro, faz uma busca pelo codigo no banco de dados e retorna
	 * um objeto do tipo Item caso o codigo seja encontrado, caso contrario, retornara NULL;
	 * @param codigo
	 * @return {@link br.ufms.facom.mariocarvalho.bookkeeping.model.Item}
	 */
	public Item getItem(int codigo){
		Cursor cursor = database.query(DatabaseOpenHelper.TABLE_ITENS, TABLE_ITENS_COLUMNS, DatabaseOpenHelper.TABLE_ITENS_COLUMN_ID + " = '" + codigo + "'", null, null, null, null);
		cursor.moveToFirst();

		Item item = null;
		if(!cursor.isAfterLast()){
			item = cursorForItem(cursor);
		}

		cursor.close();
		return item;
	}

	/**
	 * @name: cursorForItem
	 * @escope: public Item getItem({@link Cursor} cursor)
	 * @description: Recebe como parâmetro um objeto do tipo curso e retorna um objeto
	 * do tipo Item do curso passado.
	 * @param cursor
	 * @return {@link Item}
	 */
	public Item cursorForItem(Cursor cursor){
		Item item = new Item();

		item.setId(cursor.getInt(cursor.getColumnIndex(DatabaseOpenHelper.TABLE_ITENS_COLUMN_ID)));
		item.setNome(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.TABLE_ITENS_COLUMN_NOME)));
		item.setDescricao(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.TABLE_ITENS_COLUMN_DESCRICAO)));
		item.setFavorito(cursor.getInt(cursor.getColumnIndex(DatabaseOpenHelper.TABLE_ITENS_COLUMN_IS_FAVORITE)));

		return item;
    }
}