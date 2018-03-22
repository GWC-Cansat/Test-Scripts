import hc12
from nxp_imu import IMU
import bme680

sensor = bme680.BME680()
imu = IMU(gs=4, dps=2000, verbose=True)

while True:
        if sensor.get_sensor_data():
            output = str(sensor.data.temperature) + "," + str(sensor.data.pressure) + "," + str(sensor.data.humidity) + "," + str(sensor.data.gas_resistance)
        final="B:" + output + ";"
        a, m, g=imu.get()
        final=final + "D:" + str(a) + "," + str(m) + "," + str(g) + ";"
        hc12.send(final)
