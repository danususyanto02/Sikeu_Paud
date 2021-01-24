package com.paud.sikeu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class tagihan extends AppCompatActivity {
    TextView nama,tag1,tag2,tag3,tag4,tag5,tag6,tag7,tag8,tag9,tag10,tag11,tag12,
            va1,va2,va3,va4,va5,va6,va7,va8,va9,va10,va11,va12;
    ImageView ava;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String  userId;
    FirebaseUser user;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagihan);
        tag1 = findViewById(R.id.tag1);
        tag2 = findViewById(R.id.tag2);
        tag3 = findViewById(R.id.tag3);
        tag4 = findViewById(R.id.tag4);
        tag5 = findViewById(R.id.tag5);
        tag6 = findViewById(R.id.tag6);
        tag7 = findViewById(R.id.tag7);
        tag8 = findViewById(R.id.tag8);
        tag9 = findViewById(R.id.tag9);
        tag10 = findViewById(R.id.tag10);
        tag11 = findViewById(R.id.tag11);
        tag12 = findViewById(R.id.tag12);

        va1 = findViewById(R.id.va1);
        va2 = findViewById(R.id.va2);
        va3 = findViewById(R.id.va3);
        va4 = findViewById(R.id.va4);
        va5 = findViewById(R.id.va5);
        va6 = findViewById(R.id.va6);
        va7 = findViewById(R.id.va7);
        va8 = findViewById(R.id.va8);
        va9 = findViewById(R.id.va9);
        va10 = findViewById(R.id.va10);
        va11 = findViewById(R.id.va11);
        va12 = findViewById(R.id.va12);

        ava = findViewById(R.id.ava);
        nama = findViewById(R.id.textView2);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(ava);
            }
        });

        user = fAuth.getCurrentUser();
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                {
                    nama.setText(documentSnapshot.getString("Nama"));
                    tag1.setText(documentSnapshot.getString("Tagihan1"));
                    tag2.setText(documentSnapshot.getString("Tagihan2"));
                    tag3.setText(documentSnapshot.getString("Tagihan3"));
                    tag4.setText(documentSnapshot.getString("Tagihan4"));
                    tag5.setText(documentSnapshot.getString("Tagihan5"));
                    tag6.setText(documentSnapshot.getString("Tagihan6"));
                    tag7.setText(documentSnapshot.getString("Tagihan7"));
                    tag8.setText(documentSnapshot.getString("Tagihan8"));
                    tag9.setText(documentSnapshot.getString("Tagihan9"));
                    tag10.setText(documentSnapshot.getString("Tagihan10"));
                    tag11.setText(documentSnapshot.getString("Tagihan11"));
                    tag12.setText(documentSnapshot.getString("Tagihan12"));
                    va1.setText(documentSnapshot.getString("KodeTagihan1"));
                    va2.setText(documentSnapshot.getString("KodeTagihan2"));
                    va3.setText(documentSnapshot.getString("KodeTagihan3"));
                    va4.setText(documentSnapshot.getString("KodeTagihan4"));
                    va5.setText(documentSnapshot.getString("KodeTagihan5"));
                    va6.setText(documentSnapshot.getString("KodeTagihan6"));
                    va7.setText(documentSnapshot.getString("KodeTagihan7"));
                    va8.setText(documentSnapshot.getString("KodeTagihan8"));
                    va9.setText(documentSnapshot.getString("KodeTagihan9"));
                    va10.setText(documentSnapshot.getString("KodeTagihan10"));
                    va11.setText(documentSnapshot.getString("KodeTagihan11"));
                    va12.setText(documentSnapshot.getString("KodeTagihan12"));

                }
            }
        });
    }

}