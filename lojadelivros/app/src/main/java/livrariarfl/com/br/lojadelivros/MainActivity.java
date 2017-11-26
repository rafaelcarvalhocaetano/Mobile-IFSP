package livrariarfl.com.br.lojadelivros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private CheckBox c1, c2, c3, c4;
    private TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        c1 = (CheckBox) findViewById(R.id.java);
        c2 = (CheckBox) findViewById(R.id.angular);
        c3 = (CheckBox) findViewById(R.id.react);
        c4 = (CheckBox) findViewById(R.id.spring);
        tv = (TextView) findViewById(R.id.info);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "Você escolheu o Livro";
                if(c1.isChecked()){
                    msg = msg + "Java";
                    tv.setText(msg);
                }
                if(c2.isChecked()){
                    msg = msg + "Angular 4";
                    tv.setText(msg);
                }
                if(c3.isChecked()){
                    msg = msg + "React";
                    tv.setText(msg);
                }
                if(c4.isChecked()){
                    msg = msg + "SpringBoot";
                    tv.setText(msg);
                }
            }
        });
    }
}
