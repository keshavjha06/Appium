# Appium Mobile Automation

Appium tests for Android (UiAutomator2) and iOS (XCUITest), written in Java with TestNG.

## Tech stack

| Tool | Version |
|---|---|
| Java | 21 |
| Appium java-client | 11.0.0 |
| Selenium | 4.53.0 |
| TestNG | 7.12.0 |
| Appium server | 4.0 with `uiautomator2` and `xcuitest` drivers |

## Prerequisites

1. **JDK 21** and **Maven**.
2. **Appium server** and drivers:
   ```bash
   npm install -g appium
   appium driver install uiautomator2
   appium driver install xcuitest
   ```
3. **Android SDK**, with these lines in `~/.zshrc` (or `~/.zshenv` so non-interactive shells see them too):
   ```bash
   export ANDROID_HOME=$HOME/Library/Android/sdk
   export PATH=$PATH:$ANDROID_HOME/platform-tools
   ```
4. **Android emulator** named `KeshavPhone`, running.
5. **Xcode** and a booted **iPhone 18 Pro** simulator on iOS 27.2 (for the iOS tests only).

## Start the Appium server

```bash
appium --allow-insecure uiautomator2:chromedriver_autodownload
```

The `chromedriver_autodownload` flag lets Appium download the right chromedriver for the hybrid and mobile browser tests.

The base test classes reuse a server already running on `127.0.0.1:4723`. If none is running, they start one themselves.

## Running tests

Run all commands from the project root.

### Android – ApiDemos app

These tests need `-Dapp=ApiDemos-debug.apk`:

```bash
mvn test -Dapp=ApiDemos-debug.apk -Dtest='AppiumBasics,DragDropDemo,LongPress,MisceallanousAppiumActions,ScrollDemo,SwipeDemo'
```

### Android – General-Store app (default)

```bash
mvn test -Dtest='Ecommerce1,Ecommerce2,Ecommerce3,Ecommerce4Hybrid'
```

`Ecommerce4Hybrid` switches into a WebView and needs chromedriver; see [Start the Appium server](#start-the-appium-server).

### Android – Chrome browser

```bash
mvn test -Dtest=MobileBrowserTest
```

### iOS – UIKitCatalog app (default)

```bash
mvn test -Dtest='IOS*'
```

### Run a single test

```bash
mvn test -Dapp=ApiDemos-debug.apk -Dtest=AppiumBasics
mvn test -Dtest=Ecommerce1
mvn test -Dtest=IOSSlider
```

### Running from IntelliJ

Click the ▶ next to a test class or method. For ApiDemos tests, open **Run → Edit Configurations** and add this to **VM options**:

```
-Dapp=ApiDemos-debug.apk
```

## Test overview

| Test | Platform | App |
|---|---|---|
| AppiumBasics | Android | ApiDemos |
| DragDropDemo | Android | ApiDemos |
| LongPress | Android | ApiDemos |
| MisceallanousAppiumActions | Android | ApiDemos |
| ScrollDemo | Android | ApiDemos |
| SwipeDemo | Android | ApiDemos |
| Ecommerce1 / 2 / 3 | Android | General-Store |
| Ecommerce4Hybrid | Android | General-Store (native + WebView) |
| MobileBrowserTest | Android | Chrome |
| IOSBasics | iOS | UIKitCatalog |
| IOSLongPress | iOS | UIKitCatalog |
| IOSScrollTest | iOS | UIKitCatalog |
| IOSSlider | iOS | UIKitCatalog |
| IOSSwipeTest | iOS | Photos (built-in) |

## Configuration options

Pass these with `-D` on the Maven command line or in IntelliJ VM options.

| Property | Default | Used by |
|---|---|---|
| `app` | `General-Store.apk` (Android), `UIKitCatalog.app` (iOS) | `BaseTest`, `IOSBaseTest`; file name in `src/test/java/resources/` |
| `chromedriver` | `~/Downloads/chromedriver` (used only if it exists) | `BaseTest`, `BrowserBaseTest` |

## BrowserStack (optional)

`Sample.java` is a base class for running on BrowserStack. It reads credentials from a `.env` file in the project root (git-ignored), and falls back to environment variables:

```
BROWSERSTACK_USERNAME=your_username
BROWSERSTACK_ACCESS_KEY=your_access_key
BROWSERSTACK_APP_ID=bs://your_app_id
```

A variable exported in your shell takes precedence over the same key in `.env`.

## Troubleshooting

| Error | Fix |
|---|---|
| `Neither ANDROID_HOME nor ANDROID_SDK_ROOT environment variable was exported` | Start the Appium server from a terminal where `ANDROID_HOME` is set, or set it in `~/.zshenv`. |
| `No Chromedriver found that can automate Chrome '…'` | Restart Appium with `--allow-insecure uiautomator2:chromedriver_autodownload`. |
| `NoSuchElementException` in an ApiDemos test | You probably forgot `-Dapp=ApiDemos-debug.apk`. |
| First iOS run times out | The first run builds WebDriverAgent, which can take a few minutes. Run it again. |
