# Repomix Instructions — CS710S Android (Java)

**Goal**  
Produce a single packed context for AI agents to analyze BLE and RFID flows in the CS710S handheld reader app on Android (Java + Gradle), excluding build artifacts, binaries, and noise. Ensure the full output is generated without truncation.

---

## What to Include (Priority Order)
1) Build & Modules  
- `build.gradle`  
- `settings.gradle`  
- `app/build.gradle`  
- `cslibrary4a/build.gradle`  
- `epctagcoder/build.gradle`

2) Core Library (RFID + HAL + Tools)  
- `cslibrary4a/src/main/java/com/csl/cslibrary4a/*.java`  
  - RFID Unified API: `Cs108Library4A.java`, `Cs710Library4A.java`, `CsLibrary4A.java` (Host commands, inventory/access ops; CS710S focus)  
  - Comm Protocol: `RfidReaderChipData.java`, `RfidReaderChipR2000.java`, `RfidReaderChipE710.java`  
  - HAL BLE: `BluetoothConnector.java`, `BluetoothGatt.java`, `RfidConnector.java`  
  - Utilities: `Utility.java`, `AesCmac.java`, `DeviceFinder.java`

3) Android App (UI + Tasks)  
- `app/src/main/java/com/csl/cs710ademoapp/*.java` (Main logic, fragments, tasks)  
- `app/src/main/java/com/csl/cs710ademoapp/fragments/*.java` (e.g., InventoryFragment.java, AccessReadWriteFragment.java)  
- `app/src/main/java/com/csl/cs710ademoapp/adapters/*.java`  
- `app/src/main/AndroidManifest.xml`  
- `app/src/main/res/values/*.xml`  
- `app/src/main/res/layout/*.xml`

4) EPC Tag Coder  
- `epctagcoder/src/main/java/org/epctagcoder/*.java` (Parse/encode EPC schemes: SGTIN, SSCC, etc.)  
- `epctagcoder/src/main/java/org/epctagcoder/option/*.java`  
- `epctagcoder/src/main/java/org/epctagcoder/parse/*.java`  
- `epctagcoder/src/main/java/org/epctagcoder/result/*.java`

5) Docs / Metadata  
- `README.md`  
- `repomix-instructions.md`  
- `repomix-config.json` (For Repomix self-reference)

---

## Exclude (Noise & Generated)
- Build & IDE: `**/build/**`, `**/.gradle/**`, `.idea/**`, `.vscode/**`  
- Packages & Dependencies: `**/*.jar`, `**/*.aar`  
- Generated: `**/R.java`, `**/*.class`  
- Binaries/Archives: `**/*.dll`, `**/*.so`, `**/*.a`, `**/*.nupkg`, `**/*.keystore`, `**/*.apk`, `**/*.aab`, `**/*.zip`  
- Images/Misc: `**/*.png`, `**/*.jpg`, `**/*.gif`, `**/*.pdf`  
- Local Config: `**/*.iml`, `local.properties`, `**/*.user`

---

## Logical Read Order (for AI Prospecting)
Guide AI agents in "prospecting" interconnected paths:  
1) `settings.gradle` → Module dependencies.  
2) App Entry: `app/src/main/java/com/csl/cs710ademoapp/MainActivity.java` (App bootstrap, NFC/USB intents).  
3) Fragments/UI: `app/src/main/java/com/csl/cs710ademoapp/fragments/*.java` (e.g., InventoryFragment.java, ConnectionFragment.java for BLE scanning).  
4) BLE HAL: `cslibrary4a/src/main/java/com/csl/cslibrary4a/BluetoothConnector.java`, `RfidConnector.java` (Device discovery, GATT via Android BLE).  
5) RFID Unified API (CS710S Focus): `cslibrary4a/src/main/java/com/csl/cslibrary4a/Cs710Library4A.java`, `RfidReaderChipE710.java` (Public ops: inventory/read/write/antenna/power—trace to chip-specific layers).  
6) Comm Protocol: `cslibrary4a/src/main/java/com/csl/cslibrary4a/Rx000Commands` (via RfidReaderChipData.HostCommands).  
7) Tasks/Utilities: `app/src/main/java/com/csl/cs710ademoapp/AccessTask.java`, `InventoryRfidTask.java`; `epctagcoder/src/main/java/org/epctagcoder/parse/*.java`.  

This order reflects app flow: Android bootstrap → Fragment navigation → BLE connect/scan → RFID operations. Emphasize tracing dependencies (e.g., how MainActivity invokes library ops in AsyncTasks).

---

## Summarization Hints (Chunking for AI)
- Group by Feature: **BLE Connect** (BluetoothConnector + fragments), **Inventory** (InventoryRfidTask.java, RfidReader), **Read/Write** (AccessTask.java, HostCommands), **Antenna/Power** (SettingData.java, powerLevel params), **Tag Filters/QT** (setSelectCriteria), **Errors/Status** (decodedError in Rx000pkgData).  
- Limit chunks to ≤2k tokens; prefix each with file paths.  
- Cross-link public APIs to private implementations (e.g., CsLibrary4A.publicAccess → RfidReaderChipE710).  
- Note response modes (compact/normal/extended) in tag operations via HostCmdResponseTypes.  
- Flag Token-Heavy Files (>1k tokens): `AccessTask.java`, `MainActivity.java`, `CsLibrary4A.java`—suggest splitting in AI analysis.

---

## Important Callouts
- Android: `MainActivity.java`, `AndroidManifest.xml`, `res/values/strings.xml`.  
- Fragments/Tasks: `InventoryFragment.java`, `AccessReadWriteFragment.java`, `AccessTask.java`.  
- CS710S API: Public (`Cs710Library4A.java`, `RfidReader`), Operations (`RfidReaderChipE710.java` for read/write/inventory/select), Antenna/Power (`SettingData.java`), Protocol (`RfidReaderChipData.HostCommands`).  
- EPC: `epctagcoder/parse/SGTIN/ParseSGTIN.java`, `result/SGTIN.java`.  
- Utilities: `Utility.java`, `Converter.java` (hex/bin conversions).

---

## Output Requirements
- Single packed file: **Summary → Repo Info → Structure → Files (path + contents)**.  
- Preserve code blocks verbatim; include file headers.  
- Mark **excluded** areas clearly.  
- Note token-heavy files with byte/token counts.  
- Use relative paths from repo root.  
- Style: XML, with file summaries and directory structure.  
- Ensure full output without truncation (support up to 100MB).

---

## Build Context
- Targets: Android (minSdk 26, targetSdk 36; armeabi-v7a, arm64-v8a, x86_64 for emulators).  
- Key Dependencies: `com.google.android.material:material:1.12.0`, `org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5`.  
- Build: Open in Android Studio → Sync Gradle → Select `app` → Debug/Release → Deploy to device/emulator.

---

## Quality Checks (for AI Post-Packing)
- Flag BLE timing issues, UI thread handling, and disconnect edges (e.g., in BluetoothGatt, AsyncTask cancellations).  
- Confirm frequency/power settings are configurable (e.g., setAccessPowerLevel).  
- Verify no UI-blocking I/O; check AsyncTask progress in inventory/reads (e.g., AccessTask.doInBackground).