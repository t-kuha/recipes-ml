#
# Larq Compute Engine
#

SUMMARY = "Larq Compute Engine"
DESCRIPTION = "Highly optimized inference engine for Binarized Neural Networks"

S = "${WORKDIR}/git"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PV = "0.6.2"
PR = "r0"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = " \
	gitsm://github.com/larq/compute-engine.git;protocol=https;tag=v${PV};nobranch=1 \
	file://0001-change-download_dependencies.sh.patch \
	file://0002-update-makefile.patch \
	file://0003-fix-makefile-for-ldflags.patch \
"

DEPENDS += " \
    unzip-native \
"

do_compile() {
	cd ${S}/third_party/tensorflow
	tensorflow/lite/tools/make/download_dependencies.sh

	cd ${S}
	if [[ "${MACHINE}" =~ "zynqmp" ]]; then
		larq_compute_engine/tflite/build_make/build_lce.sh --aarch64
	else
		larq_compute_engine/tflite/build_make/build_lce.sh --rpi
	fi
}

do_install(){
    mkdir -p ${D}${bindir}

	if [[ "${MACHINE}" =~ "zynqmp" ]]; then
		cp -R ${S}/gen/linux_aarch64/* ${D}${bindir}
	else
	    cp -R ${S}/gen/rpi_armv7l/* ${D}${bindir}
	fi
}

FILES_${PN} += " \
    ${bindir}/* \
"
