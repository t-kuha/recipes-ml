# 
# ARM Computer Vision and Machine Learning library
# 

SUMMARY = "ARM ComputerLibrary"
DESCRIPTION = "The ARM Computer Vision and Machine Learning library is a set of functions optimised for both ARM CPUs and GPUs using SIMD technologies."

COMPATIBLE_MACHINE = "armv7a|aarch64"

# Version to use
PV = "21.02"
PR = "r0"

S = "${WORKDIR}/git/"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=9598101cf48c5f479cfda9f3fc6fc566"

# for nproc command
DEPENDS = "coreutils-native " 

SRC_URI = "\
    git://github.com/ARM-software/ComputeLibrary.git;protocol=https;tag=v${PV} \
	file://0001-Fix-compiler-option-confliction.patch \
"

inherit scons

EXTRA_OESCONS_armv7a += "\
	arch=armv7a \
	extra_cxx_flags="-fPIC -Wno-unused-but-set-variable -Wno-ignored-qualifiers -Wno-noexcept ${TOOLCHAIN_OPTIONS}" \
	benchmark_tests=1 \
	validation_tests=0 \
	neon=1 \
	openmp=1 \
	opencl=0 \
	set_soname=1 \
	toolchain_prefix=' ' \
	install_dir=_install \
"

EXTRA_OESCONS_aarch64 += "\
	arch=arm64-v8a \
    extra_cxx_flags="-fPIC -Wno-unused-but-set-variable -Wno-ignored-qualifiers -Wno-noexcept ${TOOLCHAIN_OPTIONS}" \
    benchmark_tests=1 \
    validation_tests=0 \
    neon=1 \
    openmp=1 \
    opencl=0 \
    set_soname=1 \
    toolchain_prefix=' ' \
    install_dir=_install \
"

do_install(){
    mkdir -p ${D}${bindir}
    mkdir -p ${D}${libdir}
    mkdir -p ${D}${includedir}

    cp -R ${S}build/_install/bin/* ${D}${bindir}
    cp -R ${S}build/*.so* ${D}${libdir}
    cp -R ${S}build/_install/include/* ${D}${includedir}
}

INSANE_SKIP_${PN} = "ldflags rdepends "
