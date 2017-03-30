package josep.com.firebasedatabasefirstexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView db_version;
    private DatabaseReference myReference;
    private Button b_read,b_write;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        myReference=firebaseDatabase.getReference("db_version");
        db_version= (TextView) findViewById(R.id.text_base_datos_version);
        b_read= (Button) findViewById(R.id.b_read_message);
        b_write= (Button) findViewById(R.id.b_write_message);
        b_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeMesssage(view);
            }
        });
        b_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readMessage(view);
            }
        });
    }

    private void readMessage(View view) {
        myReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String valor=dataSnapshot.getValue(String.class);
                db_version.setText(valor);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void writeMesssage(View view) {
        myReference.setValue("12345");
    }

}
