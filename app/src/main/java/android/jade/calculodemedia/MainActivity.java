package android.jade.calculodemedia;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private EditText nota1, nota2, nota3, nota4, numeroFaltas;

    private Button btnCalcular;

    private TextView resultado;

    private String nome;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initComponents();

        btnCalcular.setOnClickListener(view->{
            validaCampos();
            if(!(validaCampos())){
                calculoMedia();
            }else{
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void calculoMedia() {
        double n1 = Double.parseDouble(nota1.getText().toString());
        double n2 = Double.parseDouble(nota2.getText().toString());
        double n3 = Double.parseDouble(nota3.getText().toString());
        double n4 = Double.parseDouble(nota4.getText().toString());
        double media = (n1 + n2 + n3 +n4) / 4;
        double faltas = Double.parseDouble(numeroFaltas.getText().toString());

        if (media > 7){
            if (faltas < 20){
                resultado.setTextColor(Color.parseColor("#437845"));
                resultado.setText("Aluno aprovado com média: " + media);
            }else{
                resultado.setTextColor(Color.parseColor("#F44336"));
                resultado.setText("Excesso de falta " + faltas);
            }
        }else{
            resultado.setTextColor(Color.parseColor("#F44336"));
            resultado.setText("Aluno retido com média " + media);
        }
    }

    private boolean validaCampos() {
        return nota1.getText().toString().isEmpty()
                && nota2.getText().toString().isEmpty()
                && nota3.getText().toString().isEmpty()
                && nota4.getText().toString().isEmpty();
    }

    @SuppressLint("WrongViewCast")
    private void initComponents() {
        nota1        = findViewById(R.id.nota1);
        nota2        = findViewById(R.id.nota2);
        nota3        = findViewById(R.id.nota3);
        nota4        = findViewById(R.id.nota4);
        numeroFaltas = findViewById(R.id.numeroFaltas);
        btnCalcular  = findViewById(R.id.btn_calcular);
        resultado    = findViewById(R.id.txt_resultado);
    }
}