#!/bin/sh
(adb shell dumpsys power | grep 'mHolding'|grep 'false') && adb shell input keyevent KEYCODE_WAKEUP && adb shell input swipe 240 800 240 100 && adb shell input text redC@t33 && adb shell input keyevent 66

