package br.com.cloudbank.inacio.walter.cloudbank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aluno on 24/11/16.
 */
public class AdapterCartoes extends RecyclerView.Adapter<AdapterCartoes.ViewHolder> {

    private final List<ListaCartoes> cartoesList;
    private final Context context;
    private final OnItemClickListener listener;


    public AdapterCartoes(Context context, List<ListaCartoes> cartoesList, OnItemClickListener listener) {
        this.cartoesList = cartoesList;
        this.context = context;
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(ListaCartoes item);
    }

    @Override
    public AdapterCartoes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_lista,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterCartoes.ViewHolder holder, int position) {
        holder.bind(cartoesList.get(position), listener);
        ListaCartoes cartao = cartoesList.get(position);
        holder.tvConta.setText(cartao.getNumero());
        holder.tvNome.setText(cartao.getNumeroSeguranca());
    }

    @Override
    public int getItemCount() {
        return cartoesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvNome;
        protected TextView tvConta;
        protected RelativeLayout rvConta;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvConta = (TextView)itemView.findViewById(R.id.usuario_cartao);
            this.tvNome = (TextView)itemView.findViewById(R.id.conta_cartao);
            this.rvConta = (RelativeLayout)itemView.findViewById(R.id.conta);


        }

        public void bind(final ListaCartoes item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }



}
