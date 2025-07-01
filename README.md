# CodeServer

> [!NOTE]
> The application dose not compile code-server environment inside. You need to config code-server in Termux yourself.

## How to config code-server in Termux

1. Get Termux from [Github Release](https://github.com/termux/termux-app/releases) or [F-droid](https://f-droid.org/en/packages/com.termux/)
2. Run `pkg install tur-repo`
3. Run `pkg install code-server`
4. Run `vi ~/.config/code-server/config.yaml` to edit config, if you don't have `vi`, you need to run `pkg install vim` first
5. Edit `bind-addr` to `127.0.0.1:20000` (This step is for CodeServer app)
6. You can start code-server via run `code-server`

## How to use CodeServer

1. Config code-server in Termux
2. Get CodeServer from Release
3. Install CodeServer and grant the permission of RUN_COMMAND
4. Start CodeServer

## Q&A

- Q:Why not using Chromium(Chrome,WebView) to replace Firefox Gecko?
- A:Because the browsers which using Chromium including WebView, Chromium , etc will have display problem in my tablet device. You can think about that when you using a dark theme vscode but there is a white line on the screen bottom to hurt you eyes is very bad.

- Q:Why not using Firefox to replace CodeServer?
- A:In Firefox, the fullscreen mode have some unbearable problem. First, I need to use `F12` to enter fullscreen mode. And then, I couldn't enter fullscreen mode sometimes when you change to other tab page, and I must exit Firefox and start it again.

## LICENSE

MIT LICENSE
