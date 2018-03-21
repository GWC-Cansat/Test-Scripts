import hc12
from nxp_imu import IMU
import bme280

imu = IMU(gs=4, dps=2000, verbose=True)

while True:
        pres,temp,hum=bme280.readAll()
        final="B:" + str(pres) + "," + str(temp)  + "," + str(hum) + ";"
        a, m, g=imu.get()
        final=final + "D:" + str(a) + "," + str(m) + "," + str(g) + ";"
        hc12.send(final)
