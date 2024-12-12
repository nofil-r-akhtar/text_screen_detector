import 'package:android_intent_plus/android_intent.dart';

void openAccessibilitySettings() async {
  try {
    const intent = AndroidIntent(
      action: 'android.settings.ACCESSIBILITY_SETTINGS',
    );
    await intent.launch();
    print("Intent to Accessibility Settings launched successfully.");
  } catch (e) {
    print("Failed to launch Accessibility Settings: $e");
  }
}