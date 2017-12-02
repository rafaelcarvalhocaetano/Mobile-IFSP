package io.bloco.blocodenotas;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private ListView lv;
    private EditText et;
    private ArrayList<String> notas = new ArrayList<>();
    private ArrayAdapter<String> bloco = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        et = (EditText) findViewById(R.id.et);
        lv = (ListView) findViewById(R.id.lista);

        bloco = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notas);
        lv.setAdapter(bloco);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info = et.getText().toString();
                if(et.length() > 0){
                    et.setText("");
                    et.findFocus();
                    notas.add(info);
                    bloco.notifyDataSetChanged();
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("NOTAS");
                adb.setMessage("Você deseja excluir "+lv.getSelectedItemPosition());
                final int positionRemove = i;
                adb.setNegativeButton("NÃO", null);
                adb.setPositiveButton("SIM", new AlertDialog.OnClickListener(){
                    public void onClick(DialogInterface d, int inte){
                        notas.remove(positionRemove);
                        bloco.notifyDataSetChanged();
                    }
                });
                adb.show();
            }
        });
    }
}
