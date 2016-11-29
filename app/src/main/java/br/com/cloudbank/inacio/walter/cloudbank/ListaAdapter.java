package br.com.cloudbank.inacio.walter.cloudbank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aluno on 24/11/16.
 */
public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    private final List<ListaCartoes> cartoesList;
    private final Context context;

    public ListaAdapter(Context context, List<ListaCartoes> cartoesList) {
        this.cartoesList = cartoesList;
        this.context = context;
    }

    @Override
    public ListaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_lista,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListaAdapter.ViewHolder holder, int position) {
        ListaCartoes cartao = cartoesList.get(position);
        holder.tvConta.setText(cartao.getConta());
        holder.tvNome.setText(cartao.getNome());
    }

    @Override
    public int getItemCount() {
        return cartoesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvNome;
        protected TextView tvConta;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvConta = (TextView)itemView.findViewById(R.id.usuario_cartao);
            this.tvNome = (TextView)itemView.findViewById(R.id.conta_cartao);
        }
    }
}
