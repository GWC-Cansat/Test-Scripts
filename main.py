import hc12
from nxp_imu import IMU
import bme680

sensor.set_humidity_oversample(bme680.OS_2X)
sensor.set_pressure_oversample(bme680.OS_4X)
sensor.set_temperature_oversample(bme680.OS_8X)
sensor.set_filter(bme680.FILTER_SIZE_3)
sensor.set_gas_status(bme680.ENABLE_GAS_MEAS)
sensor.set_gas_heater_temperature(320)
sensor.set_gas_heater_duration(150)
sensor.select_gas_heater_profile(0)

sensor = bme680.BME680()
imu = IMU(gs=4, dps=2000, verbose=True)

while True:
        if sensor.get_sensor_data():
            output = str(sensor.data.temperature) + "," + str(sensor.data.pressure) + "," + str(sensor.data.humidity) + "," + str(sensor.data.gas_resistance)
        final="B:" + output + ";"
        a, m, g=imu.get()
        final=final + "D:" + str(a) + "," + str(m) + "," + str(g) + ";"
        hc12.send(final)
