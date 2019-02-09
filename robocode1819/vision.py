# Import the camera server
from cscore import CameraServer

# Import OpenCV and NumPy
import cv2
import numpy as np

def main():
	w = 320 # camera feed width
	h = 240 # camera feed height

	cam_server = CameraServer.getInstance()
	cam_server.enableLogging()

	# Capture from both USB cameras
	#"""
	cameraFC = cam_server.startAutomaticCapture(name="c0",
		#path="/dev/v41/by-path/platform-ci_hdrc.0-usb-0:1.2:1.0-video-index0",
		#path="/dev/v41/by-id/usb-046d_0825_A1143010-video-index0",
		dev=0,
		)
	cameraBC = cam_server.startAutomaticCapture(name="c1",
		#path="/dev/v41/by-path/platform-ci_hdrc.0-usb-0:1.1:1.0-video-index0",
		#path="/dev/v41/by-id/usb-Microsoft_MicrosoftR_LifeCam_HD-3000-video-index0",
		dev=1,
		)
	"""
	cameraBC = cam_server.startAutomaticCapture(dev=0)
	cameraFC = cam_server.startAutomaticCapture(dev=1)
	#"""
	
	cameraFC.setResolution(w, h)
	cameraBC.setResolution(w, h)

	# Get a CvSink. This will capture images from the cameras
	cvSink = cam_server.getVideo()

	# Setup a CvSource. This will send images back to the Dashboard
	driveStream = cam_server.putVideo("DriveCamera", w, h)

	# Preallocate camera image
	image = np.zeros(shape=(240, 320, 3), dtype=np.uint8)

	while True:
		#######################################################################
	
		### Drive Camera ###
		
		# Get Camera Image
		time, image = cvSink.grabFrame(image)
		if time == 0:
			# Send the output the error.
			#driveStream.notifyError(cvSink.getError());
			# skip the rest of the current iteration
			continue
		
		# Center Line
		cv2.line(image, (round(w / 2), 0), 
						(round(w / 2), h), (0, 255, 0), 1)
		
		# Width Guidelines
		cv2.line(image, (0,            round(h * 13/24) ), 
						(round(w / 2), round(h * 81/240)), (0, 255, 255), 1)
		cv2.line(image, (w,            round(h * 13/24) ), 
						(round(w / 2), round(h * 81/240)), (0, 255, 255), 1)

		# Output Drive Camera
		driveStream.putFrame(image)

		########################################################################
		
"""

### Drawing on Camera ###

# if thickness is set to -1, it will fill in the shape

# Regular Drawings
cv2.line(image, (startX, startY), (endX, endY), (B, G, R), thickness)
cv2.rectangle(image, (topLX, topLY), (bottomRX, bottomRY), (B, G, R), thickness)
cv2.circle(image, (centerX, centerY), radius, (B, G, R), thickness)
cv2.ellipse(image, (centerX, centerY), (axisX, axisY), angle, startArc, endArc, (B, G, R), thickness)

# Polygon
pts = np.array([[x1, y1], [x2, y2], [x3, y3], [x4, y4]], np.int32)
pts = pts.reshape((-1, 1, 2)) # Thickness somewhere in here?
cv2.polylines(imgshr, [pts], Fill?, (B, G, R))

# Text
font = cv2.FONT_HERSHEY_SIMPLEX
cv2.putText(image, text, (x, y), font, scale, (B, G, R), thickness, lineType) # cv2.LINE_AA recommended lineType

"""