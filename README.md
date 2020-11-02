# recipes-ml

- Yocto recipes for machine learning libraries

__arm-computelibrary__: ARM ComputeLibrary (v20.08)

__armnn__: Arm NN ML Software (v20.08)

__caffe__: Caffe v1.0

__libtorch__: PyTorch C++ API (v1.7.0)

__torchvision__: Torch Vision (v0.8.1)

__tflite__: TensorFlow Lite (TF v1.15.4)

__tflite2__: TensorFlow Lite (TF v2.3.0)

***

## Some note on compiling libtorch

- Ample amount of RAM will be necessary (preferably 3GB per CPU core)

- Python (>= 3.6) is required for configuring libtorch

- yaml (pyyaml) & dataclasses packages are necessary for configuration
