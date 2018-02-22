import hc12,bme280

pres,temp,hum=bme280.readAll()

print(pres)
print(temp)
print(hum)

hc12.send("B")
hc12.send(str(pres))
hc12.send(str(temp))
hc12.send(str(hum))
