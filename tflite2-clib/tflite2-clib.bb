#
# TensorFlow Lite C library (TF v2)
#

SUMMARY = "TensorFLow Lite C library"
DESCRIPTION = "TensorFlow Lite is TensorFlow's lightweight solution for mobile and embedded devices."

# TFLite version to use
PV = "2.7.0"
PR = "r0"

S = "${WORKDIR}/git/tensorflow/lite/c"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=c7e17cca1ef4230861fb7868e96c387e"

SRC_URI = "\
    git://github.com/tensorflow/tensorflow.git;protocol=https;tag=v${PV};nobranch=1 \
"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DTFLITE_ENABLE_XNNPACK=OFF \
    -DCMAKE_SYSTEM_NAME=Linux \
    -DCMAKE_SYSTEM_PROCESSOR=aarch64 \
"

do_install(){
    install -d ${D}${libdir}
    install -d ${D}${includedir}/tensorflow/lite/c

    install -m 644 libtensorflowlite_c.so ${D}${libdir}
    install -m 644 ${S}/*.h ${D}${includedir}/tensorflow/lite/c
    install -m 644 ${S}/../builtin_ops.h ${D}${includedir}/tensorflow/lite/
}

INSANE_SKIP_${PN}-dev += "dev-elf"
