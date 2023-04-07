# recipes-ml

- Yocto recipes for machine learning libraries

__arm-computelibrary__: ARM ComputeLibrary (v23.02.1)

__armnn__: Arm NN ML Software (v23.02)

__caffe__: Caffe v1.0

__oneDNN__: oneAPI Deep Neural Network Library (v3.1)
  - Note: MPSoC (64-bit processor) only

__PyTorch__: PyTorch (v1.13.1)

__PyXIR__: Xilinx PyXIR (v0.3.5)

__tflite__: TensorFlow Lite (TF v1.15.5)

__tflite2__: TensorFlow2 Lite (TF v2.7.3)

__TorchVision__: TorchVision (v0.14.1)

__TVM__: Apache TVM (v0.11.1)

***

## Some notes on compiling libtorch

- Ample amount of RAM will be necessary (preferably >= 16GB)

- yaml (pyyaml), dataclasses, and typing_extensions packages are necessary for configuration
