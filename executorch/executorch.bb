# executorch

SUMMARY = "executorch"
DESCRIPTION = "On-device AI across mobile, embedded and edge for PyTorch"

# Version to use
SRCREV = "7b220c6f4ecc0a8b504a9c516e5643a2cf2d4376"
PV = "1.0.1"
PR = "r0"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=83c85f96f0e7ed209d1d879999598d2a"

# The ExecuTorch repo must be cloned into a directory named exactly `executorch`
S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = " \
    gitsm://github.com/pytorch/executorch.git;protocol=https;branch=release/1.0 \
    file://0001-Workaround-1.0.1.patch \
"

inherit cmake python3native

DEPENDS += " \
    zstd-native \
    python3-pip-native \
    flatbuffers-native \
"

EXTRA_OECMAKE = "\
    -DBUCK2=buck2 \
    -DEXECUTORCH_BUILD_EXECUTOR_RUNNER=ON \
    -DEXECUTORCH_BUILD_HOST_TARGETS=OFF \
    -DEXECUTORCH_ENABLE_EVENT_TRACER=OFF \
    -DCMAKE_BUILD_TYPE=Release \
    -DFLATC_EXECUTABLE=flatc \
"

do_configure[network] = "1"
do_configure:prepend(){
    # install required modules
    pip3 install pyyaml torch==2.9.1

    # buck2
    wget -q "https://github.com/facebook/buck2/releases/download/2024-05-15/buck2-${TARGET_ARCH}-unknown-linux-gnu.zst"
    zstd -f -d buck2-${TARGET_ARCH}-unknown-linux-gnu.zst -o ${STAGING_DIR_NATIVE}/usr/bin/buck2
    chmod +x ${STAGING_DIR_NATIVE}/usr/bin/buck2

    # flatc is now from flatbuffers-native
}

do_compile:prepend() {
    # workaround for issue https://github.com/pytorch/executorch/issues/6475
    # add "executorch" directory symlink so that compiler can see the headers
    ln -sf ${S} "${WORKDIR}/executorch"
}

FILES:${PN} += " \
    ${prefix}/share/cpuinfo/* \
    ${libdir}/* \
    ${includedir}/* \
"

FILES:${PN}-dev += " \
    ${prefix}/share/cpuinfo/* \
    ${libdir}/* \
    ${libdir}/*.a \
    ${includedir}/* \
"
