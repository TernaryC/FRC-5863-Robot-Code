import os
while True:
	year = input("Year to sim...")
	try:
		os.chdir("C:\\Users\\pah\\Documents\\Robocode\\robocode{}".format(year))
	except:
		print("Invalid year")
		continue
	break
os.system("py -3 robot.py sim")
input()
quit()