# Vopgram для Android

Оболочка WebView вокруг vopgram.html (лежит в app/src/main/assets/index.html).
APK подписывается стандартным debug-ключом Android — для установки на свой телефон этого достаточно.

## Как получить APK без Android Studio
1. Создайте на github.com пустой репозиторий и загрузите в него ВСЕ файлы из этой папки (вместе с папкой .github).
2. Откройте вкладку Actions, дождитесь зелёной галочки у «Build APK» (3–5 минут).
3. Откройте запуск → внизу Artifacts → скачайте Vopgram-debug-apk → внутри app-debug.apk.
4. Скопируйте на телефон и установите (разрешите установку из неизвестных источников).

## Как обновить приложение
Замените app/src/main/assets/index.html новой версией vopgram.html и снова загрузите в репозиторий.

## Сборка вручную
Нужны JDK 17 и Android SDK 34:  gradle assembleDebug
Результат: app/build/outputs/apk/debug/app-debug.apk
