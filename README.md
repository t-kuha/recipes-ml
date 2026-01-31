# meta-ml

- Yocto recipes for machine learning libraries
- Yocto version: Scarthgap (5.0.4)

***

## available libraries

- __ARM ComputeLibrary (ACL)__: v52.7.0 (25.03, 24.06, 23.08)
- __ARM NN__: 25.02 (, 24.11, 24.08, 24.05)
- __ExecuTorch__: v1.0.1
- __libtorch__: v2.6.0
- __llama.cpp__: llama.cpp (release: b4957 / commit: 053b3f9)
- __oneDNN__: v3.7.2 (, v3.4)
- __TFLite2__: v2.14.0

***

## Note on PyTorch

- Now that aarch64 version of wheel is available via PyPI, PyTorch recipe has been dropped.

_ = vars.UnknownVariables().pop('PREFIX', None)
_ = vars.UnknownVariables().pop('prefix', None)
_ = vars.UnknownVariables().pop('MAXLINELENGTH', None)