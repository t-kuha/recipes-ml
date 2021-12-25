# 
# LMDB
#   Reference: https://xilinx-wiki.atlassian.net/wiki/spaces/A/pages/18842475/PetaLinux+Yocto+Tips
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

EXTRA_OEMAKE = "SOEXT='.so.${PV}' LDFLAGS='-Wl,-soname,lib${PN}.so.${PV} ${LDFLAGS}'"

do_install () {
    install -d ${D}${libdir}
    oe_libinstall -so lib${PN} ${D}${libdir}

    install -d -m 0655 ${D}${includedir}
    install -m 0644 ${S}/lmdb.h ${D}${includedir}
}
