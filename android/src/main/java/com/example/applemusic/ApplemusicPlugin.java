package com.example.applemusic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import io.flutter.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;

import com.apple.android.sdk.authentication.AuthenticationManager;
import com.apple.android.sdk.authentication.AuthenticationFactory;
import com.apple.android.sdk.authentication.TokenResult;
import com.example.applemusic.AppleAuthenticator;

import org.json.JSONObject;


public class ApplemusicPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware, PluginRegistry.ActivityResultListener {

  private MethodChannel channel;
  private Context mContext;
  private Activity activity;
  public AuthenticationManager appleAuthenticationManager;
  public Result result;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "applemusic");
    channel.setMethodCallHandler(this);
    this.mContext = flutterPluginBinding.getApplicationContext();
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    this.activity = binding.getActivity();
    binding.addActivityResultListener(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if(call.method.equals("appleMusicRequestPermission")) {
      this.result = result;
      appleMusicRequestPermission();
    }
    else {
      result.notImplemented();
    }
  }

  public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
    TokenResult tokenResult = appleAuthenticationManager.handleTokenResult(data);

    if (!tokenResult.isError()) {
      String appleMusicUserToken = tokenResult.getMusicUserToken();
      Log.d("Apple Music", "User Token: " + appleMusicUserToken);
      this.result.success(appleMusicUserToken);
    } else {
      Log.e("Apple Music", "Error getting token: " + tokenResult.getError());
    }
    return true;
  }


  public void appleMusicRequestPermission() {
    this.appleAuthenticationManager = AuthenticationFactory.createAuthenticationManager(mContext);
    Intent intent = this.appleAuthenticationManager
            .createIntentBuilder("eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjRCSDNWWjVWOVUifQ.eyJpc3MiOiI5S1VBNzVZVlE1IiwiaWF0IjoxNjA4MzAxMjY4LCJleHAiOjE2MDgzNDQ0Njh9.-4IcedozoszW8uz7UxRaAHwvdZarlnwL8rwSEZWHGV52shbJH1Gias3YR8lKKVFpcJCyyXs2abSNA1Jq4gwgZg")
            .setHideStartScreen(false)
            .setStartScreenMessage("Login to Apple Music to import your playlists.")
            .build();

    this.activity.startActivityForResult(intent, 3456);
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onDetachedFromActivityForConfigChanges(){

  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {

  }

  @Override
  public void onDetachedFromActivity() {

  }
}
