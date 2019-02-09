import os
while True:
	year = input("Year to deploy...")
	try:
		os.chdir("C:\\Users\\pah\\FRC\\robocode{}".format(year))
	except:
		print("Invalid year")
		continue
	break
failed = 1
counter = 0
while failed == 1:
	counter += 1
	failed = os.system("py -3 robot.py deploy --no-version-check")
os.system("color 2F")
print("It took {!s} attempts to deploy!".format(counter))
input()
quit()