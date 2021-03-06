# TensorFlow Lite (TF v2)
# 

SUMMARY = "TensorFLow Lite"
DESCRIPTION = "TensorFlow Lite is TensorFlow's lightweight solution for mobile and embedded devices."

# TFLite version to use
PV = "2.4.1"
PR = "r0"

S = "${WORKDIR}/git/"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=64a34301f8e355f57ec992c2af3e5157"

DEPENDS = "unzip-native "

SRC_URI = "\
    git://github.com/tensorflow/tensorflow.git;protocol=https;tag=v${PV};nobranch=1 \
    file://0001-change-download_dependencies.sh.patch \
    file://0002-update-makefile.patch \
    "


do_compile(){
    ${S}tensorflow/lite/tools/make/download_dependencies.sh
    oe_runmake -f ${S}tensorflow/lite/tools/make/Makefile \
    TARGET_ARCH=${OECORE_TARGET_ARCH}
}

do_install(){
    mkdir -p ${D}${bindir}
    mkdir -p ${D}${libdir}

    cp -R ${S}tensorflow/lite/tools/make/gen/linux_/bin/* ${D}${bindir}
    cp -R ${S}tensorflow/lite/tools/make/gen/linux_/lib/* ${D}${libdir}
}


FILES_${PN} += " \
    ${bindir}/* \
"

FILES_${PN}-dev += " \
    ${bindir}/* \
    ${libdir}/* \
"

FILES_${PN}-staticdev += " \
    ${bindir}/* \
    ${libdir}/* \
"

FILES_${PN}-dbg += " \
    ${bindir}/* \
    ${bindir}/.debug/* \
"
