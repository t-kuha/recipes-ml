#
# TensorFlow Lite (TF v2)
#

SUMMARY = "TensorFLow Lite"
DESCRIPTION = "TensorFlow Lite is TensorFlow's lightweight solution for mobile and embedded devices."

# TFLite version to use
PV = "2.7.0"
PR = "r0"

S = "${WORKDIR}/git/tensorflow/lite"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=c7e17cca1ef4230861fb7868e96c387e"

SRC_URI = "\
    git://github.com/tensorflow/tensorflow.git;protocol=https;tag=v${PV};nobranch=1 \
    file://0001-Disable-Hexagon-delegate.patch \
"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DTFLITE_ENABLE_XNNPACK=OFF \
    -DCMAKE_SYSTEM_NAME=Linux \
    -DCMAKE_SYSTEM_PROCESSOR=aarch64 \
"

do_compile_append(){
    cmake --build . -j -t benchmark_model
    cmake --build . -j -t label_image
}

do_install(){
    install -d ${D}${bindir}

    install -m 755 examples/label_image/label_image ${D}${bindir}
    install -m 755 tools/benchmark/benchmark_model ${D}${bindir}
}
