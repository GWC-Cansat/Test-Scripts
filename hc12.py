import serial,time

def send(data):
  with serial.Serial('/dev/ttyS1', 9600) as hc12:
    hc12.write(data)
    hc12.write('\n')
    time.sleep(0.01655)

def recieve():
  with serial.Serial('/dev/ttyS1', 9600) as hc12:
    return( hc12.readline() )
