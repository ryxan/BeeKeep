# BeeKeep — Native Android v1.8.1

BeeKeep is a mobile-first, native Kotlin app for managing apiaries, hive records, inspections, and NFC hive tags.

## Current app scope

- **Apiaries:** create and organize yards, save GPS coordinates, and view saved locations on the in-app satellite map.
- **Hives:** keep each colony's record inside its apiary, track queen status and colony strength, and access its inspection history.
- **Inspections:** log mite-wash counts and sample size, queen observations, brood and stores, disease flags, field notes, voice dictation, and GPS where available.
- **Hive logs:** record feeding, treatments, and harvests.
- **NFC:** scan tags to open hives, assign or replace tags, verify or remove assignments, and write/read back BeeKeep tag data. Conflicting tag assignments are guarded to avoid silently changing the wrong hive.
- **Field usability:** high-contrast yellow-and-black mode, large controls, and local records that remain available without a network connection.
- **Optional cloud sync:** sign in to a configured Supabase backend to synchronize records between devices. Core record entry remains local-first.

The current app intentionally does **not** include the Calendar, Analytics, camera-capture, photo-album, or PC/browser test-lab features. Legacy records are preserved where supported; these removed UI features are not exposed.

New installations start with an empty apiary list rather than fabricated demo colonies. Existing legacy records are imported where available.

## Build and verify

Use JDK 17 and the Android SDK required by the project.

Build the debug APK:

```bash
./gradlew :app:assembleDebug
```

Run available JVM unit tests and build the APK:

```bash
./gradlew :app:testDebugUnitTest :app:assembleDebug
```

The debug APK is written to `app/build/outputs/apk/debug/app-debug.apk`. GitHub Actions also builds and uploads a debug APK for repository pushes.

For Windows environment checks and install helpers, see `BUILD_ANDROID.md`, `scripts/verify-build-host.sh`, `scripts/build-debug.sh`, and the Windows build/install scripts in `scripts/`.

## Data safety notes

- Room is the on-device source of truth for local use.
- Database migrations are explicit to preserve data across supported upgrades.
- Hive lifecycle changes preserve colony history; permanently deleting a hive is a separate action.
- NFC UID assignments are stored as a ledger so a released tag can be assigned again without losing its history.
- Local photo paths are not synced as usable paths on another device; any legacy photo records remain device-local unless their upload was already supported and configured.
