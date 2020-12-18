import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:applemusic/applemusic.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  Applemusic appleMusic = new Applemusic();

  @override
  void initState() {
    super.initState();
  }

  appleMusicRequestPermission() async {
    String resp = await appleMusic.appleMusicRequestPermission();
    print(resp.toString());
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.center,
            children:[
              FlatButton(
                color: Colors.blue,
                child: Text("PRESS MUH BUTTON", style: TextStyle(color: Colors.white)),
                onPressed: () {
                  appleMusicRequestPermission();
                },
              )
            ]
          )
        ),
      ),
    );
  }
}
