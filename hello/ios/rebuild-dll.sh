#!/bin/sh

if [ -z "$IKVM_HOME" ]; then
    IKVM_HOME=$HOME/projects/ikvm-monotouch
fi
MAVEN_REPO=$HOME/.m2/repository
export MONO_PATH=/Developer/MonoTouch/usr/lib/mono/2.1

echo "Converting Java bytecode to CLR dll..."
rm -f hello-core.dll
mono $IKVM_HOME/bin/ikvmc.exe -nostdlib -debug -target:library -out:hello-core.dll \
    -r:$MONO_PATH/mscorlib.dll \
    -r:$MONO_PATH/System.dll \
    -r:$MONO_PATH/System.Core.dll \
    -r:$MONO_PATH/System.Data.dll \
    -r:$MONO_PATH/OpenTK.dll \
    -r:$MONO_PATH/monotouch.dll \
    -r:$MONO_PATH/Mono.Data.Sqlite.dll \
    $MAVEN_REPO/com/samskivert/pythagoras/1.1/pythagoras-1.1.jar \
    $MAVEN_REPO/com/googlecode/playn/playn-core/1.1-SNAPSHOT/playn-core-1.1-SNAPSHOT.jar \
    $MAVEN_REPO/com/googlecode/playn/playn-ios/1.1-SNAPSHOT/playn-ios-1.1-SNAPSHOT.jar \
    ../core/target/playn-hello-core-1.0-SNAPSHOT.jar
