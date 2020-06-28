/*package customUtil;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

*//**
 * Utility Class used for authenticating Firebase Admin SDK
 *//*

public class GoogleAuthentication {

    public void authenticate(){
        googleSdkAuthorization();
        databaseConnection();
    }

    *//**
     * Server Side Methods for Firebase Admin SDK
     *//*
    public void googleSdkAuthorization(){
        FileInputStream refreshToken = null;
        try {
            refreshToken = new FileInputStream("../reservation_manager/hotelreservationmanager-d4233-firebase-adminsdk-a6ghn-7b078d1952.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl("https://hotelreservationmanager-d4233.firebaseio.com/")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseApp.initializeApp(options);
    }

    public void databaseConnection(){
        // As an admin, the app has access to read and write all data, regardless of Security Rules
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("/restricted_access/secret_document");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println(document);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

}*/
