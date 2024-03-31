# YAMF^2 :D

Yet Another Yet Another [Mi-FreeForm](https://github.com/sunshine0523/Mi-FreeForm)

More my style floating windows!

## Requirements
- Android 13+ (>= api 33)
- LSPosed

## Features
- Launch from recents app icon
- Launch by home button long press (read further)
- Launch from app icon long press from homescreen/app drawer
- Launch from taskbar
- Multiple window support
- Minimize window to take up less space
- Resize window
- Scale window maybe?
- FLAG_SECURE support
- Quick Settings tiles 
- App Rotation detection
- Custom default window size
- Custom DPI setting
- Bunch of others in app :D

## Launch by home button long press (or swipe gesture)
- Be willing to give up google assistant
- Go to Settings -> Assistant App
- Change it to YAMF^2
- Profit

## How to install
- Grab the latest APK from the [releases section](https://github.com/kaii-lb/YAMFsquared/releases)
- Install it
- Enable module in LSPosed
- Reboot

## "API" 
- Broadcast `io.github.kaii_lb.yamfsquared.action.CURRENT_TO_WINDOW` to float the currently visible app
- Maybe more to come

## Issues
- The system will crash if the module is different from the injected version, its an xposed thing
- Some apps can't seem to launch in small windows
- Some apps scale abnormally at certain sizes

## TODO
- Minimize to app icon 
- Scale window (not resize)
- Hide default virtual display window on pressing back button enough times
- RtL support
- You tell me

## Special Thanks
- MASSIVE thanks to [duzhaokun123](https://github.com/duzhaokun123)

- [AOSP](https://source.android.com/)
- [EzXHelper](https://github.com/KyuubiRan/EzXHelper)
- [FlexboxLayout](https://github.com/google/flexbox-layout)
- [Hide-My-Applist](https://github.com/Dr-TSNG/Hide-My-Applist)
- [LSPosed](https://github.com/LSPosed/LSPosed)
- [Material](https://material.io/)
- [Mi-FreeForm](https://github.com/sunshine0523/Mi-FreeForm)
- [QAuxiliary](https://github.com/cinit/QAuxiliary)
- [ViewBindingUtil](https://github.com/matsudamper/ViewBindingUtil)
- [gson](https://github.com/google/gson)
- [xposed](https://forum.xda-developers.com/xposed)
