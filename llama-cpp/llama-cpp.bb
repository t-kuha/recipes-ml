#
# llama.cpp
#
SUMMARY = "llama.cpp"
DESCRIPTION = "LLM inference in C/C++"

# revision to use
SRCREV = "37c35f0e1c625831687b146cbb0a57654ef88ca2"
PR = "r0"

S = "${WORKDIR}/git"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=1539dadbedb60aa18519febfeab70632"

SRC_URI = "\
    git://github.com/ggml-org/llama.cpp.git;protocol=https;branch=master \
"

DEPENDS = " openssl"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
"

SOLIBS = ".so"
SOLIBSDEV = ".so"
FILES:${PN} = "\
    ${libdir}/* \
    ${bindir}/* \
"
FILES:${PN}-dev = "\
    ${libdir}/* \
    ${bindir}/* \
    ${includedir}/* \
"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-elf"
