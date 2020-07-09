# Caffe
# 

SUMMARY = "Caffe"
DESCRIPTION = "Caffe: a fast open framework for deep learning."

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LICENSE;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Caffe version to use
PV = "1.0"
PR = "r0"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "\
	git://github.com/BVLC/caffe.git;protocol=https;tag=${PV} \
    file://0001-Update-Makefile.config.patch  \
    file://0002-Use-CPU-as-solver-mode.patch \
    file://0003-Fix-test-when-CPU-only-mode.patch \
    file://0004-Remove-Python-from-build-target.patch \
    file://0005_disable-hdf5.patch \
	"

S = "${WORKDIR}/git/"


# Dependencies
DEPENDS = "protobuf boost gflags glog opencv "
DEPENDS += "leveldb snappy lmdb openblas "
DEPENDS += "protobuf-native "

do_unpack_append(){
    # Copy Makefile.config.example as Makefile.config
    import os
    import shutil
    
    s = d.getVar('S')
    shutil.copy(os.path.join(s, 'Makefile.config.example'), os.path.join(s, 'Makefile.config'))
}


do_compile () {
	echo "----------------------------------"
	echo ${STAGING_BINDIR_NATIVE}
    oe_runmake distribute
}

do_install () {
    rm -rf ${D}
    mkdir -p ${D}
    cp -R ${S}/distribute/* ${D}
}
