package projetofinal.com.br.projetofinal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by rafaelcarvalho on 07/12/2017.
 */

public class PessoaFisica extends Activity implements OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);
    }

    @Override
    public void onClick(View view) {

    }
}
