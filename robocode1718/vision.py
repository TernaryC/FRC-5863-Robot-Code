# Import the camera server
from cscore import CameraServer

# Import OpenCV and NumPy
import cv2
import numpy as np

def main():
    cam_server = CameraServer.getInstance()
    cam_server.enableLogging()

    # Capture from the first USB Camera on the system
    camera = cam_server.startAutomaticCapture()
    camera.setResolution(320, 240)

    # Get a CvSink. This will capture images from the camera
    cvSink = cam_server.getVideo()

    # (optional) Setup a CvSource. This will send images back to the Dashboard
    outputStream = cam_server.putVideo("Name", 320, 240)

    # Allocating new images is very expensive, always try to preallocate
    image = np.zeros(shape=(240, 320, 3), dtype=np.uint8)

    while True:
        # Tell the CvSink to grab a frame from the camera and put it
        # in the source image.  If there is an error notify the output.
        time, image = cvSink.grabFrame(image)
        if time == 0:
            # Send the output the error.
            outputStream.notifyError(cvSink.getError());
            # skip the rest of the current iteration
            continue

        # (optional) send some image back to the dashboard
        outputStream.putFrame(image)