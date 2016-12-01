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
 * Created by aluno on 01/12/16.
 */
public class AdapterFaturas extends RecyclerView.Adapter<AdapterFaturas.ViewHolder> {

    private final List<ListaCartoes> cartoesList;
    private final Context context;
    private final OnItemClickListener listener;

    public AdapterFaturas(List<ListaCartoes> cartoesList, Context context, OnItemClickListener listener) {
        this.cartoesList = cartoesList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public AdapterFaturas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lista_fatura,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterFaturas.ViewHolder holder, int position) {
        holder.bind(cartoesList.get(position), listener);
        ListaCartoes cartao = cartoesList.get(position);
        holder.tvLocal.setText(cartao.getNome());
        holder.tvPreco.setText(cartao.getNome());
        holder.tvData.setText(cartao.getNome());
    }

    @Override
    public int getItemCount() {
        return cartoesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        protected TextView tvLocal;
        protected TextView tvPreco;
        protected TextView tvData;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvLocal = (TextView)itemView.findViewById(R.id.tvLocal);
            this.tvPreco = (TextView)itemView.findViewById(R.id.tvPreco);
            this.tvData = (TextView) itemView.findViewById(R.id.tvData);


        }

        public void bind(final ListaCartoes item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(ListaCartoes item);
    }
}
