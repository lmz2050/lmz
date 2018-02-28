#!/bin/sh

rpwd=$(pwd)

SOURCE="$0"
while [ -h "$SOURCE"  ]; do 
    RUN_PATH="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
    SOURCE="$(readlink "$SOURCE")"
    [[ $SOURCE != /*  ]] && SOURCE="$RUN_PATH/$SOURCE" 
done
RUN_PATH="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"

export RUN_PATH
cd ${RUN_PATH}


LPARAM=@LP@:$1,@LF@:$2,@LD@:${rpwd},@LA@:${sa}


JAVA_HOME="/usr/java/jdk1.8.0_151"
CLASS_PATH=${JAVA_HOME}/jre/lib/rt.jar:${RUN_PATH}/lib/*:.:${RUN_PATH}/bin
JVM_OPTS="-Xms128m -Xmx256m"
JAVA_OPTS="-Dfile.encoding=UTF-8 -Djava.awt.headless=true -Djava.io.tmpdir=${RUN_PATH}/tmp -DAPPHOME=${RUN_PATH}/ -Dlog4j.configurationFile=${RUN_PATH}/etc/log4j2.xml"
LTIME=$(date +"%Y%m%d%H%M%S")


echo "[${LTIME}] ${JAVA_HOME}/bin/java ${JVM_OPTS} ${JAVA_OPTS} -cp ${CLASS_PATH} cn.lmz.mike.lmz.sys.Lmz ${LPARAM}"
${JAVA_HOME}/bin/java ${JVM_OPTS} ${JAVA_OPTS} -cp ${CLASS_PATH} cn.lmz.mike.lmz.sys.Lmz ${LPARAM}