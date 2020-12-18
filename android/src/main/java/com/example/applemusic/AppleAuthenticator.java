package com.example.applemusic;

import android.content.Context;
import android.content.Intent;

import com.apple.android.sdk.authentication.AuthenticationFactory;
import com.apple.android.sdk.authentication.AuthenticationManager;

import java.util.HashMap;

public class AppleAuthenticator {
    private static AuthenticationManager authenticationManager;

    public static Intent logIn(Context context) {
        if (authenticationManager == null) {
            authenticationManager = AuthenticationFactory.createAuthenticationManager(context);
        }
        // Start the canned login process
        Intent intent = authenticationManager.createIntentBuilder("eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjRCSDNWWjVWOVUifQ.eyJpc3MiOiI5S1VBNzVZVlE1IiwiaWF0IjoxNjA4MjI0OTI1LCJleHAiOjE2MDgyNjgxMjV9.9zyRzI3QTTZXCfG9Teaz9d24xfOorE2YuAL9WRebjp1grE4rxLchWtcWa_ShcJrLiZOyTYbUNiD_83vuNZhadA19216816")
                .setHideStartScreen(false)
                .setStartScreenMessage("Please log into Apple Music to access your library.")
                .build();

        return intent;
    }
}