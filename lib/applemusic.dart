import 'dart:async';

import 'package:flutter/services.dart';

class Applemusic {
  static const MethodChannel _channel =
  const MethodChannel('applemusic');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  appleMusicRequestPermission() async {
    final String resp = await _channel.invokeMethod('appleMusicRequestPermission');
    return resp;
  }
}
