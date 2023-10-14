#
# TensorFlow Lite (TF v2)
#

SUMMARY = "TensorFLow Lite"
DESCRIPTION = "TensorFlow Lite is TensorFlow's lightweight solution for mobile and embedded devices."

# TFLite version to use
SRCREV = "4dacf3f368eb7965e9b5c3bbdd5193986081c3b2"
PV = "2.14.0"
PR = "r0"

S = "${WORKDIR}/git/tensorflow/lite"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=4158a261ca7f2525513e31ba9c50ae98"

SRC_URI = "\
    git://github.com/tensorflow/tensorflow.git;protocol=https;branch=r2.14 \
    file://0001-Compile-bag-fix.patch \
"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DTFLITE_ENABLE_XNNPACK=OFF \
    -DTFLITE_ENABLE_INSTALL=OFF \
    -DCMAKE_SYSTEM_NAME=Linux \
    -DCMAKE_SYSTEM_PROCESSOR=aarch64 \
"
# reference: https://zenn.dev/nbo/scraps/d485855bbf2d9b
EXTRA_OECMAKE:append = "-DFETCHCONTENT_FULLY_DISCONNECTED=OFF"

do_configure[network] = "1"
do_compile:append(){
    cmake --build . -j -t benchmark_model
    cmake --build . -j -t label_image
}

do_install(){
    install -d ${D}${bindir}

    install -m 755 examples/label_image/label_image ${D}${bindir}
    install -m 755 tools/benchmark/benchmark_model ${D}${bindir}
}
