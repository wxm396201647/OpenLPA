brew uninstall openjdk@8; brew install openjdk@17
export PATH=/Users/mobile/Anaconda/usr/local/opt/openjdk@11/bin:$PATH
./gradlew assemble; mv app/build/outputs/apk/release/app-release.apk OpenLPA.apk; ./gradlew clean
scp -P 8022 magisk-module-openlpa-js2597.zip 10.254.92.212:~/storage/downloads

### FIX ###
# getprop persist.service.seek > fullaccess
resetprop -nv ro.debuggable 1 # or add this to Magisk post-fs-data.sh
# /default.prop -> ro.debuggable=1
setprop persist.service.seek fullaccess
