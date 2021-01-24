package com.paud.sikeu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//User-Permission
public class register_app extends AppCompatActivity {
    private static final String TAG = "Taskssample";
    EditText userEmail,userName,noHp,nis,userPassword,tghn1,tghn2,tghn3,tghn4,tghn5,tghn6,tghn7,tghn8,tghn9,tghn10,tghn11,tghn12,kdthn1,kdthn2,kdthn3,kdthn4,kdthn5,kdthn6,kdthn7,kdthn8,kdthn9,kdthn10,kdthn11,kdthn12;
    Button btn_daftar;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;


    //Deklarasi Variable [Hidepassword]
    private EditText PassInput;
    private CheckBox ShowPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_app);

        userEmail = findViewById(R.id.userEmail);
        userName = findViewById(R.id.userName);
        noHp = findViewById(R.id.noHp);
        nis = findViewById(R.id.nis);
        userPassword = findViewById(R.id.userPassword);
        btn_daftar = findViewById(R.id.btn_daftar);
        tghn1 = findViewById(R.id.tghn1);
        tghn2 = findViewById(R.id.tghn2);
        tghn3 = findViewById(R.id.tghn3);
        tghn4 = findViewById(R.id.tghn4);
        tghn5 = findViewById(R.id.tghn5);
        tghn6 = findViewById(R.id.tghn6);
        tghn7 = findViewById(R.id.tghn7);
        tghn8 = findViewById(R.id.tghn8);
        tghn9 = findViewById(R.id.tghn9);
        tghn10 = findViewById(R.id.tghn10);
        tghn11 = findViewById(R.id.tghn11);
        tghn12 = findViewById(R.id.tghn12);

        kdthn1 = findViewById(R.id.kdthn1);
        kdthn2 = findViewById(R.id.kdthn2);
        kdthn3 = findViewById(R.id.kdthn3);
        kdthn4 = findViewById(R.id.kdthn4);
        kdthn5 = findViewById(R.id.kdthn5);
        kdthn6 = findViewById(R.id.kdthn6);
        kdthn7 = findViewById(R.id.kdthn7);
        kdthn8 = findViewById(R.id.kdthn8);
        kdthn9 = findViewById(R.id.kdthn9);
        kdthn10 = findViewById(R.id.kdthn10);
        kdthn11 = findViewById(R.id.kdthn11);
        kdthn12 = findViewById(R.id.kdthn12);


        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        //[HidePassword]
        PassInput = findViewById(R.id.userPassword);
        ShowPass = findViewById(R.id.showPass);

        //Set onClickListener, untuk menangani kejadian saat Checkbox diklik [Hidepassword]
        ShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ShowPass.isChecked()){
                    //Saat Checkbox dalam keadaan Checked, maka password akan di tampilkan [Hidepassword]
                    PassInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    //Jika tidak, maka password akan di sembuyikan [Hidepassword]
                    PassInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                final String namapanjang = userName.getText().toString().trim();
                final String nisiswa = nis.getText().toString().trim();
                final String nohape = noHp.getText().toString().trim();
                final String tagih1 = tghn1.getText().toString().trim();
                final String tagih2 = tghn2.getText().toString().trim();
                final String tagih3 = tghn3.getText().toString().trim();
                final String tagih4 = tghn4.getText().toString().trim();
                final String tagih5 = tghn5.getText().toString().trim();
                final String tagih6 = tghn6.getText().toString().trim();
                final String tagih7 = tghn7.getText().toString().trim();
                final String tagih8 = tghn8.getText().toString().trim();
                final String tagih9 = tghn9.getText().toString().trim();
                final String tagih10 = tghn10.getText().toString().trim();
                final String tagih11 = tghn11.getText().toString().trim();
                final String tagih12 = tghn12.getText().toString().trim();

                final String kodetagih1 = kdthn1.getText().toString().trim();
                final String kodetagih2 = kdthn2.getText().toString().trim();
                final String kodetagih3 = kdthn3.getText().toString().trim();
                final String kodetagih4 = kdthn4.getText().toString().trim();
                final String kodetagih5 = kdthn5.getText().toString().trim();
                final String kodetagih6 = kdthn6.getText().toString().trim();
                final String kodetagih7 = kdthn7.getText().toString().trim();
                final String kodetagih8 = kdthn8.getText().toString().trim();
                final String kodetagih9 = kdthn9.getText().toString().trim();
                final String kodetagih10 = kdthn10.getText().toString().trim();
                final String kodetagih11 = kdthn11.getText().toString().trim();
                final String kodetagih12 = kdthn12.getText().toString().trim();


                if (TextUtils.isEmpty(email)){
                    userEmail.setError("Harap Masukkan Email");
                }
                if (TextUtils.isEmpty(password)){
                    userPassword.setError("Harap Masukkan Password");
                }
                if (password.length()<6){
                    userPassword.setError("Password Harus Lebih Dari 6 Karakter");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(register_app.this, "User Baru Berhasil Terinput", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("id",userID);
                            user.put("Password",password);
                            user.put("Nama",namapanjang);
                            user.put("Nis",nisiswa);
                            user.put("NoTelp",nohape);
                            user.put("Email",email);
                            user.put("Tagihan1",tagih1);
                            user.put("Tagihan2",tagih2);
                            user.put("Tagihan3",tagih3);
                            user.put("Tagihan4",tagih4);
                            user.put("Tagihan5",tagih5);
                            user.put("Tagihan6",tagih6);
                            user.put("Tagihan7",tagih7);
                            user.put("Tagihan8",tagih8);
                            user.put("Tagihan9",tagih9);
                            user.put("Tagihan10",tagih10);
                            user.put("Tagihan11",tagih11);
                            user.put("Tagihan12",tagih12);

                            user.put("KodeTagihan1",kodetagih1);
                            user.put("KodeTagihan2",kodetagih2);
                            user.put("KodeTagihan3",kodetagih3);
                            user.put("KodeTagihan4",kodetagih4);
                            user.put("KodeTagihan5",kodetagih5);
                            user.put("KodeTagihan6",kodetagih6);
                            user.put("KodeTagihan7",kodetagih7);
                            user.put("KodeTagihan8",kodetagih8);
                            user.put("KodeTagihan9",kodetagih9);
                            user.put("KodeTagihan10",kodetagih10);
                            user.put("KodeTagihan11",kodetagih11);
                            user.put("KodeTagihan12",kodetagih12);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "Berhasil mendaftar akun baru " + userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(register_app.this, "Gagal Membuat User Baru" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });


    }

}