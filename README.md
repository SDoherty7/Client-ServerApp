# Client-ServerApp
A Java Client-Server application which displays graphical temperature readings on a GUI on the client end. The application was originally designed to have a Raspberry Pi acting as a web server sending CPU core temperature readings to connecting clients over a TCP socket connection. For testing purposes the application is configured to create servers on the localhost and send random integer values to mimic a varying temperature (the commented method for getting the Pi core CPU temperature is included). The server is threaded to allow for multiple client connections. 

The temperature readings are displayed in a  scrolling format on a GUI built using swing. The GUI can connect to 3 seperate servers at once and display each unique graph. For testing purposes it is configured to connect to the localhost for each graph getting three seperate graphs. The user enters the desired server IP address in the GUI and the sample rate for receiving temperature readings which can be updated during runtime. 

![capture](https://user-images.githubusercontent.com/32989169/47155130-072fa080-d2dc-11e8-8f0d-23acba9f8da6.PNG)


## Getting Started
Java SE 8 version installed on the preffered device e.g. Rapsberry Pi, Beaglebone Black or simply the Host Desktop Computer Machine.
Clone this Git Repository on the machine and run!
Change port etc. if neccessary 


### Acknowledgements 
This application heavily expands on the sample client-server application [example](http://ee402.eeng.dcu.ie/introduction/chapter-8---threads-and-networking/8-4-a-multi-threaded-client-server-application) written by D. Molloy 

### Questions?
Please feel free to contact me with any enquiries 
