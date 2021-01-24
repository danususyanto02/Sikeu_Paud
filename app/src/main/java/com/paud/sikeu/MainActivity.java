package com.paud.sikeu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //login
    FirebaseAuth mAuth;
    EditText textEmailAddress, userPassword ;



    //Deklarasi Variable [Hidepassword]
    private EditText PassInput;
    private CheckBox ShowPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //login
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.btn_login).setOnClickListener(this);
        textEmailAddress = (EditText) findViewById(R.id.userEmail);
        userPassword = (EditText) findViewById(R.id.userPassword);



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

        final TextView daftarbaru = findViewById(R.id.daftarbaru);
        daftarbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, register_app.class);
                startActivity(a);
            }
        });

        TextView forgot = findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(MainActivity.this,forgotPassword.class);
                startActivity(f);
            }
        });
    }
    //mengubah inputan yang ditulis menjadi string
    private void userLogin(){
        String email = textEmailAddress.getText().toString().trim();
        String password = userPassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //jika berhasil maka akan menampilkan halaman home
                if (task.isSuccessful()){
                    finish();
                    Intent intent = new Intent(MainActivity.this, HomeAct.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                //jika tidak maka akan tertahan pada menu login
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //inisialiasasi id pada database jika button login di klik
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                userLogin();
                break;
        }
    }
}
