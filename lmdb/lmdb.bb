# 
# LMDB
#   Based on: https://phabricator.kde.org/R868:f096b1e5112ffc0f2c669b3f15531fdc0ff06e9b
# 

SUMMARY = "Lightning Memory-Mapped Database"
DESCRIPTION = "Lightning Memory-Mapped Database"

S = "${WORKDIR}/git/libraries/liblmdb"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LICENSE = "OpenLDAP"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=153d07ef052c4a37a8fac23bc6031972"

# LMDB version to use
PV = "0.9.29"
PR = "r0"

SRC_URI = "\
    git://github.com/LMDB/lmdb.git;protocol=https;tag=LMDB_${PV};nobranch=1 \
    file://0001-change-for-cross-compilation.patch \
"

inherit base

do_compile () {
    oe_runmake SOEXT=".so.${PV}" LDFLAGS="-Wl,-soname,lib${PN}.so.${PV} ${LDFLAGS}"
}

do_install () {
    oe_runmake install DESTDIR=${D} SOEXT=".so.${PV}" LDFLAGS="-Wl,-soname,lib${PN}.so.${PV} ${LDFLAGS}
"
    cd ${D}/${libdir}
    ln -s liblmdb.so.${PV} liblmdb.so
}
