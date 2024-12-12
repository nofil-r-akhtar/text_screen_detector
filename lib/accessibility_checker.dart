import 'package:flutter/services.dart';

class AccessibilityChecker {
  static const platform = MethodChannel('com.example.word_detector/accessibility');

  static Future<bool> isAccessibilityEnabled() async {
    try {
      final result = await platform.invokeMethod<bool>('isAccessibilityEnabled');
      return result ?? false;
    } on PlatformException catch (_) {
      return false;
    }
  }
}