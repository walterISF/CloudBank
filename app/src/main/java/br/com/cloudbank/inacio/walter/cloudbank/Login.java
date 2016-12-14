package br.com.cloudbank.inacio.walter.cloudbank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements LoginCallback {

    private TaskGetLogin taskGetLogin;
    private final String URLLogin = "http://172.16.131.6:5050/client/auth";
    private Context context;
    private String strEmail;
    private String strSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btLogin = (Button) findViewById(R.id.btLogin);
        final TextView email = (TextView)findViewById(R.id.tvNome);
        final TextView senha = (TextView)findViewById(R.id.tvSenha);



        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strEmail = email.getText().toString();
                strSenha = senha.getText().toString();

                taskGetLogin = new TaskGetLogin(URLLogin+"?email="+strEmail+"&senha="+strSenha,Login.this,Login.this);

                if(taskGetLogin == null)
                    Toast.makeText(context, "Email ou senha estão incorretos.", Toast.LENGTH_SHORT).show();
                else
                    taskGetLogin.execute();


            }
        });

    }

    @Override
    public void lgCallback(JSONObject json) throws Exception {

        if(json!=null){

            if(strEmail.equals(json.getString("email")) &&strSenha.equals(json.getString("senha"))){
                Intent telaCartao = new Intent(Login.this, Cartao.class);
                String id,nome;
                id = json.getString("id");
                nome = json.getString("nome");
                Bundle bundle = new Bundle();
                bundle.putString("id",id);
                bundle.putString("nome",nome);
                telaCartao.putExtras(bundle);
                startActivity(telaCartao);
                this.finish();
            }
            else{
                Toast.makeText(context, "Usuario e senha Invalidos", Toast.LENGTH_SHORT).show();
            }

        }else{
            throw new Exception ("Erro ao validar usuario.");
        }
    }

}
