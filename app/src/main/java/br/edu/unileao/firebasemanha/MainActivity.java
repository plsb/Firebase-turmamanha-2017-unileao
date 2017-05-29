package br.edu.unileao.firebasemanha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference
            = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference clienteReference
            = databaseReference.child("cliente");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edtNome = (EditText) findViewById(R.id.edtNome);
        final EditText edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        final EditText edtCPF = (EditText) findViewById(R.id.edtCpf);
        Button btSalvar = (Button) findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cliente cliente = new Cliente();
                cliente.setNome(edtNome.getText().toString());
                cliente.setCpf(edtCPF.getText().toString());
                cliente.setEndereco(edtEndereco.getText().toString());

                clienteReference.child(edtCPF.getText().toString()).setValue(cliente);

                edtNome.setText("");
                edtCPF.setText("");
                edtEndereco.setText("");
            }
        });

        clienteReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(MainActivity.this,
                        "Houve mudança no banco", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
