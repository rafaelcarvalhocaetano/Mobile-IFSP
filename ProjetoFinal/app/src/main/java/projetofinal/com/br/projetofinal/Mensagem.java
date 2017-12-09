//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Rafael Carvalho Caetano
//******************************************************

package projetofinal.com.br.projetofinal;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by rafaelcarvalho on 08/12/2017.
 */

public class Mensagem {

    private Context _ctx;

    public Mensagem(Context c) {
        this._ctx = c;
    }

    public void show(String titulo, String info){

        AlertDialog.Builder adb = new AlertDialog.Builder(_ctx);
        adb.setTitle(titulo);
        adb.setMessage(info);
        adb.setNeutralButton("OK", null);
        adb.show();
    }
}
