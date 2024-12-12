import 'package:flutter/material.dart';
import 'package:word_detector/accessibility_checker.dart';
import 'package:word_detector/accessibility_notifer.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const AccessibilityScreen(),
    );
  }
}

class AccessibilityScreen extends StatefulWidget {
  const AccessibilityScreen({super.key});

  @override
  _AccessibilityScreenState createState() => _AccessibilityScreenState();
}

class _AccessibilityScreenState extends State<AccessibilityScreen> {
  bool _isServiceEnabled = false;

  @override
  void initState() {
    super.initState();
    checkServiceStatus();
  }

  Future<void> checkServiceStatus() async {
    final isEnabled = await AccessibilityChecker.isAccessibilityEnabled();
    setState(() {
      _isServiceEnabled = isEnabled;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Word Detector Service")),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              _isServiceEnabled
                  ? "Accessibility Service is Enabled"
                  : "Accessibility Service is Disabled",
              style: const TextStyle(fontSize: 18),
            ),
            const SizedBox(height: 20),
            const ElevatedButton(
              onPressed: openAccessibilitySettings,
              child: Text("Enable Word Detector"),
            ),
          ],
        ),
      ),
    );
  }
}
