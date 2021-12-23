# recipes-ml

- Yocto recipes for machine learning libraries

__arm-computelibrary__: ARM ComputeLibrary (v21.11)

__armnn__: Arm NN ML Software (v21.11)

__oneDNN__: oneAPI Deep Neural Network Library (v2.4.4)
  - Note: MPSoC (64-bit processor) only

__caffe__: Caffe v1.0

__libtorch__: PyTorch C++ API (v1.10.1)

__torchvision__: Torch Vision (v0.11.2)

__tflite__: TensorFlow Lite (TF v1.15.5)

__tflite2__: TensorFlow Lite (TF v2.7.0)

__larq-compute-engine__: Larq Compute Engine (v0.6.2)

***

## Some notes on compiling libtorch

- Ample amount of RAM will be necessary (preferably >= 16GB)

- yaml (pyyaml), dataclasses, and typing_extensions packages are necessary for configuration
