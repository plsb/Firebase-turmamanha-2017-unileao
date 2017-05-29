package br.edu.unileao.firebasemanha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pelusb on 29/05/17.
 */

public class ListaClientesActivity extends AppCompatActivity{

    private DatabaseReference databaseReference
            = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference clienteReference
            = databaseReference.child("cliente");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        final ListView listView = (ListView) findViewById(R.id.listaCliente);

        clienteReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                List<Cliente> clientes = new ArrayList<Cliente>();
                while (iterable.iterator().hasNext()){
                    DataSnapshot clienteSnapShot = iterable.iterator().next();
                    Cliente cliente = clienteSnapShot.getValue(Cliente.class);
                    clientes.add(cliente);
                }
                ArrayAdapter<Cliente> adapter =
                        new ArrayAdapter<Cliente>(ListaClientesActivity.this,
                                android.R.layout.simple_list_item_1, clientes);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}



















