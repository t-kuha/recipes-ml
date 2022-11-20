# recipes-ml

- Yocto recipes for machine learning libraries

__arm-computelibrary__: ARM ComputeLibrary (v22.08)

__armnn__: Arm NN ML Software (v22.08)

__oneDNN__: oneAPI Deep Neural Network Library (v2.7.2)
  - Note: MPSoC (64-bit processor) only

__caffe__: Caffe v1.0

__PyXIR__: Xilinx PyXIR (v0.3.5)

__tflite__: TensorFlow Lite (TF v1.15.5)

__tflite2__: TensorFlow2 Lite (TF v2.7.3)

__TVM__: Apache TVM (v0.10.0)

***

## Some notes on compiling libtorch

- Ample amount of RAM will be necessary (preferably >= 16GB)

- yaml (pyyaml), dataclasses, and typing_extensions packages are necessary for configuration
