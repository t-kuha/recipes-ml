# 
# ARM Computer Vision and Machine Learning library
# 

SUMMARY = "ARM ComputerLibrary"
DESCRIPTION = "The ARM Computer Vision and Machine Learning library is a set of functions optimised for both ARM CPUs and GPUs using SIMD technologies."

COMPATIBLE_MACHINE = "armv7a|aarch64"

# Version to use
SRCREV = "add70ace1e57f65d1ae4d0cedaec6e4578cf87ff"
PV = "23.11"
PR = "r0"

S = "${WORKDIR}/git"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=a436777748fb1193895c051cea504ab6"

# for nproc command
DEPENDS = "coreutils-native " 

SRC_URI = "\
    git://github.com/ARM-software/ComputeLibrary.git;protocol=https;branch=main \
    file://0001-Fix-compile-error.patch \
    file://0002-Fix-compile-error.patch \
"

inherit scons

EXTRA_OESCONS:armv7a += "\
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
    Werror=0 \
"

EXTRA_OESCONS:aarch64 += "\
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
    Werror=0 \
"

do_install(){
    mkdir -p ${D}${bindir}
    mkdir -p ${D}${libdir}
    mkdir -p ${D}${includedir}

    rm -r ${S}/build/_install/include/CL

    cp -R ${S}/build/_install/bin/* ${D}${bindir}
    cp -R ${S}/build/*.so* ${D}${libdir}
    cp -R ${S}/build/_install/include/* ${D}${includedir}
}

INSANE_SKIP:${PN} = "ldflags rdepends "
