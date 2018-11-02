/*
 * Informações sobre a criação do arquivo.
 * Autor: Mário de Araújo Carvalho
 * E-mail: mariodearaujocarvalho@gmail.com
 * GitHub: https://github.com/MarioDeAraujoCarvalho
 * Ano: 13/5/2017
 * Entrar em contado para maiores informações..
 */

package br.ufms.facom.mariocarvalho.bookkeeping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import br.ufms.facom.mariocarvalho.bookkeeping.dao.item.ItemDAO;
import br.ufms.facom.mariocarvalho.bookkeeping.interfaces.OnItemLongClickListener;
import br.ufms.facom.mariocarvalho.bookkeeping.model.Item;
import br.ufms.facom.mariocarvalho.bookkeeping.util.Util;
import br.ufms.facom.mariocarvalho.bookkeeping.view.activities.DetalhesItemActivity;
import br.ufms.facom.mariocarvalho.bookkeeping.R;
import br.ufms.facom.mariocarvalho.bookkeeping.interfaces.OnClickListener;
import br.ufms.facom.mariocarvalho.bookkeeping.view.activities.ItemActivity;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private int mBackground;
    private Context mContext;
    private HashSet<Integer> checkedItems;
    private List<Item> mListaItems;
    private ArrayList<Item> mSearchItems;

    private static OnClickListener mOnClickListener;
    private static OnItemLongClickListener mOnItemLongClickListener;


    private static final int ITEM_VIEW_TYPE_ITEM = 1;
    private static final int ITEM_VIEW_TYPE_HEADER = 0;

    public ItemAdapter(Context context, List<Item> items) {
        this.mContext = context;
        this.mListaItems = items;
        this.checkedItems = new HashSet<>();
        this.mSearchItems = new ArrayList<>();
        this.mSearchItems.addAll(this.mListaItems);

        if (context != null) {
            TypedValue mTypedValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, mTypedValue, true);
            this.mBackground = mTypedValue.resourceId;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View mContainer;

        public TextView mNome, mDescricao;
        public ImageView mMenuPopUpOverFlow;
        public ImageView mImagemFavoritoNaoPreenchida, mImagemFavoritoPreenchida;

        public ViewHolder(View view) {
            super(view);

            mContainer = view;

            mNome = (TextView) view.findViewById(R.id.txt_nome);
            mDescricao = (TextView) view.findViewById(R.id.txt_descricao);
            
            mImagemFavoritoNaoPreenchida = (ImageView) view.findViewById(R.id.img_favorito_nao_preenchida);
            mImagemFavoritoPreenchida = (ImageView) view.findViewById(R.id.img_favorito_preenchida);
            mMenuPopUpOverFlow = (ImageView) view.findViewById(R.id.menu_pop_up_over_flow);

            mContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // -1 Refers to the header
                    Log.e("TAG", "onClick");

                    int position = getAdapterPosition();
                    if (mOnClickListener != null && position >= 0) {
                        mOnClickListener.onClick(v, position);
                    }
                }
            });

            mContainer.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.e("TAG", "onLongClick");

                    int position = getAdapterPosition();
                    if (mOnItemLongClickListener != null && position >= 0) {
                        mOnItemLongClickListener.onLongClick(v, position);
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView;

        mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_card_view, parent, false);
        mItemView.setBackgroundResource(this.mBackground);
        return new ViewHolder(mItemView);
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemViewType(int position) {
        return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(final ViewHolder mHolder, int position) {

        if (mListaItems == null || mListaItems.size() == 0){
            return;
        }else{
            final Item mItem = mListaItems.get(position);

            mHolder.mNome.setText(mItem.getNome());
            mHolder.mDescricao.setText(mContext.getResources().getString(R.string.text_descricao)+" "+ mItem.getDescricao());
            
            if(mItem.getFavorito() == 1){
                mHolder.mImagemFavoritoPreenchida.setVisibility(View.VISIBLE);
                mHolder.mImagemFavoritoNaoPreenchida.setVisibility(View.GONE);
            }else{
                mHolder.mImagemFavoritoPreenchida.setVisibility(View.GONE);
                mHolder.mImagemFavoritoNaoPreenchida.setVisibility(View.VISIBLE);
            }
            mHolder.mImagemFavoritoNaoPreenchida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    favoritar(mItem);
                }
            });

            mHolder.mImagemFavoritoPreenchida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    favoritar(mItem);
                }
            });

            mHolder.mMenuPopUpOverFlow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(mHolder.mMenuPopUpOverFlow, mItem);
                }
            });

        }
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view, Item item) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_pop_up_actions, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(item));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        Item mItemAux = new Item();
        public MyMenuItemClickListener(Item Item) {
            mItemAux = Item;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_visualizar:
                    visualizar();
                    return true;
                case R.id.action_editar:
                    editar(mItemAux);
                    return true;
                case R.id.action_compartilhar:
                    compartilhar(mItemAux);
                    return true;
                default:
            }
            return false;
        }
        public void compartilhar(Item item){
            String texto = "Olá Pessoal,\n" +
                    "Gostaria de Comparlihar esse item: \n" +
                    "\nNome: "+ item.getNome()+
                    "\nDescrição: "+ item.getDescricao()+
                    "\n\n\n--\nEnviado de Fast Framework";

            Util.compartilhar(mContext, texto);
        }

        private void visualizar() {
            Intent newIntent = new Intent(mContext, DetalhesItemActivity.class);
            newIntent.putExtra("codigo",String.valueOf(mItemAux.getId()));
            mContext.startActivity(newIntent);
        }
    }

    private void editar(Item mItemAux) {
        Util.startIntentParseOneValue(mContext, new ItemActivity(),"codigo",(mItemAux.getId()));
        this.notifyDataSetChanged();
    }

    public void favoritar(Item mItem) {

        String texto = "";
        if(mItem.getFavorito() == 1){
            mItem.setFavorito(0);
            texto = "Retirado dos Favoritos!";
        }else{
            mItem.setFavorito(1);
            texto = "Adicionado dos Favoritos!";
        }
        ItemDAO mItemDAO = new ItemDAO(mContext);
        mItemDAO.open();
        mItemDAO.updateIsFavorite(mItem);
        mItemDAO.close();

        //Atualiza o item
        this.notifyDataSetChanged();
    }


    public void remove(int position){
        mListaItems.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mListaItems.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void resetarCheck() {
        this.checkedItems.clear();
        this.notifyDataSetChanged();
    }

    public void setChecked(int position, boolean checked) {
        resetarCheck();

        if (checked) {
            this.checkedItems.add(position);
        } else {
            this.checkedItems.remove(position);
        }

        this.notifyDataSetChanged();
    }

    public void searchWords(CharSequence charText) {

        charText = Util.removeAccent((String) charText).toLowerCase(Locale.getDefault());

        mListaItems.clear();
        if (charText.length() == 0) {
            mListaItems.addAll(mSearchItems);
        } else {
            for (Item mItem : mSearchItems) {
                String nome = Util.removeAccent(mItem.getNome());
                String descricao = Util.removeAccent(mItem.getDescricao());

                if (nome.toLowerCase(Locale.getDefault()).contains(charText) || descricao.toLowerCase(Locale.getDefault()).contains(charText)) {
                    mListaItems.add(mItem);
                }
            }
        }

        notifyDataSetChanged();
    }
}

