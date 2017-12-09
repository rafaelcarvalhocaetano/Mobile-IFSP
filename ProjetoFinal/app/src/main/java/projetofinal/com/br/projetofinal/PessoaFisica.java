//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Rafael Carvalho Caetano
//******************************************************

package projetofinal.com.br.projetofinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by rafaelcarvalho on 07/12/2017.
 */

public class PessoaFisica extends Activity implements OnClickListener{
    //declarando as variáveis do mesmo tipo da view
    private Button btnVoltar, btnAdd, btnDeletar, btnUpdate, btnListID, btnListaAll;

    //Declarando uma variável privada do tipo Context para passar para classe Usuario
    private Context c;

    //Instanciando a classe Usuário e atribuindo para ela nossa classe atual
    private Mensagem mensagem = new Mensagem(PessoaFisica.this);

    //Criando variáveis do mesmo tipo para o EditText
    private EditText editNome, editCpf, editIdade, editTelefone, editEmail;

    //Variaáveis do tipo String para receber as variáveis do tipo EditText para manipulação
    private String nome, cpf, idade, telefone, email;

    //Espesifica uma variável para o banco de dados SQLite
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        //atribuindo os componentes da view para as variáveis declaradas nesta classe
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnAdd = (Button) findViewById(R.id.btnSalvar);
        btnDeletar = (Button) findViewById(R.id.btnExcluir);
        btnUpdate = (Button) findViewById(R.id.btnAtualizar);
        btnListID = (Button) findViewById(R.id.btnListID);
        btnListaAll = (Button) findViewById(R.id.btnListaAll);

        //atribuindo os componentes da view para as variáveis declaradas nesta classe
        editNome = (EditText) findViewById(R.id.editNome);
        editCpf = (EditText) findViewById(R.id.editCPF);
        editIdade = (EditText) findViewById(R.id.editIdade);
        editTelefone = (EditText) findViewById(R.id.editTelefone);
        editEmail = (EditText) findViewById(R.id.editEmail);

        //configurando os eventos dos botões
        btnVoltar.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnDeletar.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnListID.setOnClickListener(this);
        btnListaAll.setOnClickListener(this);

        //Criando o banco de dados
        db = openOrCreateDatabase("PessoaFisicaDB", Context.MODE_PRIVATE, null);

        //Criando nossa tabela Usuário
        db.execSQL("CREATE TABLE IF NOT EXISTS usuario(nome varchar, cpf varchar, idade varchar, telefone varchar, email varchar)");

    }
    //método responsável pelo evento dos botões
    @Override
    public void onClick(View view) {

        //Atribuindo as variáveis do tipo string para as do tipo EditText pegando os valores dos campos
        nome = editNome.getText().toString();
        cpf = editCpf.getText().toString();
        idade = editIdade.getText().toString();
        telefone = editTelefone.getText().toString();
        email = editEmail.getText().toString();

        //verificando e dando ação ao botão de voltar para a página principal que é a MainActivity
        if(view == btnVoltar) {
            startActivity(new Intent(this, MainActivity.class));
        }
        //Condição de clique no botão de adicionar onde ele verificará
        // os campos preenchidos e salvará no nosso banco de dados
        if(view == btnAdd){
            if(verificandoCampos()){
                //mensagem que aparecerá ao usuário
                mensagem.show("AVISO", "Valores Obrigatórios");
                return;
            }
            //Add registro
            db.execSQL("INSERT INTO usuario VALUES ('" +nome+ "', '" +cpf+ "', '" +idade+ "', '" +telefone+ "','" +email+ "');");
            mensagem.show("SUCESSO", "Usuário: "+nome+" Cadastrado com sucesso! ");
            limpandoCampos();
        }
        //verificando o botão de pesquisar todos os registros da tabela aluno
        if(view == btnListaAll){
            Cursor c = db.rawQuery("SELECT * FROM usuario", null);
            //verificando se o cursor for igual a 0 não buscará nada
            if(c.getCount() == 0){
                //mensagem que aparecerá ao usuário
                mensagem.show("AVISO", "Nenhum Registro encontrado ... ");
                return;
            }
            //Classe do tipo string que armazena muitos caracteres
            StringBuilder info = new StringBuilder();
            //loop que verifica se conseguiu buscar algum registros
            while (c.moveToNext()){
                info.append("NOME: "+c.getString(0)+"\n");
                info.append("CPF: "+c.getString(1)+"\n");
                info.append("IDADE: "+c.getString(2)+"\n");
                info.append("TELEFONE: "+c.getString(3)+"\n");
                info.append("E-mail: "+c.getString(4)+"\n");
                info.append("\n************************************\n\n");
            }
            mensagem.show("Usuários", info.toString());
        }
        //verificando ação do botão que busca as informações através do cpf do usuário
        if(view == btnListID){
            //verificando se existe informação no campo cpf para buscar
            if(cpf.trim().length() == 0){
                mensagem.show("AVISO", "CPF: "+cpf+" não localizado ");
                return;
            }
            //cursor que buscará o usuário com base em seu CPF
            Cursor c = db.rawQuery("SELECT * FROM usuario WHERE cpf='" +cpf+ "'", null);
            //se conseguiu buscar algum registro ele atribuirá aos campos as informações buscadas com referencia nas colunas
            if(c.moveToFirst()){
                editNome.setText(c.getString(0));
                editIdade.setText(c.getString(2));
                editTelefone.setText(c.getString(3));
                editEmail.setText(c.getString(4));
            }else{
                mensagem.show("ERRO", "CPF: "+cpf+" não localizado.");
                return;
            }
        }
        //verificando ação do botão update
        if(view == btnUpdate){
            //verificando se existe informação no campo cpf para buscar
            if(cpf.trim().length() == 0){
                mensagem.show("AVISO", "CPF: "+cpf+" não localizado ");
                return;
            }
            //cursor que buscará o usuário com base em seu CPF
            Cursor c = db.rawQuery("SELECT * FROM usuario WHERE cpf='" +cpf+ "'", null);
            //se conseguiu buscar irá fazer uma atualização nas no registro
            if(c.moveToFirst()){
                db.execSQL("UPDATE usuario SET nome='" +nome+ "', idade='" +idade+ "', " +
                        "telefone='" +telefone+ "', email='" +email+ "' WHERE cpf='" +cpf+ "'; ");
                mensagem.show("AVISO", "CPF: "+cpf+" Atualizado ");
            }else {
                mensagem.show("ERRO", "CPF: "+cpf+" não localizado.");
                return;
            }
            //limpa os campos
            limpandoCampos();
        }
        //botão excluir
        if(view == btnDeletar){
            //verificando se existe informação no campo cpf para buscar
            if(cpf.trim().length() == 0){
                mensagem.show("AVISO", "CPF: "+cpf+" não localizado ");
                return;
            }
            //buscando usuario com base no CPF
            Cursor c = db.rawQuery("SELECT * FROM usuario WHERE cpf='" +cpf+ "'", null);
            //se conseguiu buscar ele irá deletar com base no CPF
            if(c.moveToFirst()){
                db.execSQL("DELETE FROM usuario WHERE cpf='" +cpf+ "';");
                mensagem.show("DELETADO", "Usuário: "+nome+" excluído com sucesso");
            }else{
                mensagem.show("AVISO", "CPF: "+cpf+" não localizado ");
                return;
            }
            //limpando campos
            limpandoCampos();
        }
    }
    //método responsável por verificar os campos tirando os espaços com a função trim
    public boolean verificandoCampos(){
        return (
                nome.trim().length() == 0
                || cpf.trim().length() == 0
                || idade.trim().length() == 0
                || telefone.trim().length() == 0
                || email.trim().length() == 0
        );
    }
    //limpa os campos EDITTEXT e atribui o cursor para o campo nome com o requestFocus()
    public void limpandoCampos(){
        editNome.setText("");
        editCpf.setText("");
        editIdade.setText("");
        editTelefone.setText("");
        editEmail.setText("");
        editNome.requestFocus();
    }
}