# Offline-First Students (Android / Kotlin / Compose)

An offline-first sample that manages **Students** and their **ScoreCards** with local Room storage and background synchronization. Focuses on:
- Offline-first CRUD with Room
- Ordered sync (Student before ScoreCard)
- Conflict resolution (`updatedAt` wins; remote deletion overrides local)
- Background sync with WorkManager + exponential backoff
- Manual retry for permanent failures
- Clear sync status UI badges (PENDING / SYNCED / FAILED)

> **Note:** This project uses a **Mock Remote API** that mimics a server for demonstration. It respects order, IDs, conflict rules, and simulates network errors. WorkManager requires `NetworkType.CONNECTED` to run syncs, so turning **device network off/on** will naturally trigger sync work according to constraints.

## Architecture (Clean-ish)
- `data/` — Room entities/dao, repositories, mock remote api, mappers
- `domain/` — models, repositories (interfaces), use cases
- `sync/` — Sync engine, workers, conflict resolution
- `ui/` — Jetpack Compose screens (Student list/detail, ScoreCards), state via ViewModel (Hilt)
- `di/` — Hilt modules
- `util/` — small helpers

## Run
- Open in **Android Studio Hedgehog+** (or newer).
- Let Gradle sync; hit **Run**.
- Toggle network to see WorkManager constraints kick in.
- You can also tap **Retry** on failed items, or use the **Sync Now** action in the top bar.

## Conflict Rules
- **Latest `updatedAt` wins** (ISO-8601 UTC on both sides).
- **Remote deletion overrides local**: if remote says deleted, app deletes locally even if local changed later.
- Delete Student **cascades** to ScoreCards (both local & remote).

## Bonus
- Room Schema Migration example: add `teacherRemark` column in `ScoreCard` from DB v1 -> v2.
- Paging 3 stub included for Students list (local paging; RemoteMediator scaffold included & easily extendable).

## Packages Overview
See comments in each class; key files:
- `data/local/*` — Room DB, DAO, migrations
- `data/remote/MockRemoteApi.kt` — in-app "server"
- `data/repo/*` — repository impl + sync orchestration hooks
- `sync/SyncWorker.kt`, `sync/SyncEngine.kt` — background + order handling
- `ui/*` — Compose screens

## Testing
- Basic unit tests for mappers and conflict resolution in `app/src/test/...`

## Notes
- This uses **Hilt**, **Room**, **WorkManager**, **Retrofit (for interface only)**, **DataStore** (for demo online toggle), **Kotlinx Serialization**.
- Compose BOM manages UI dependencies.
