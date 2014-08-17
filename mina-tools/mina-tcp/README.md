Build And Run
================

Start the Local TCP Server:
----------------------------

    mvn clean install
    cd <the target dir>
    java -jar mina-tcp.jar -p 3000

Verify the TCP Server From Command line
----------------------------------------

Using the Java client:

    java -cp mina-tcp.jar  mina.tcp.MinaTCPClient -hn localhost -p 2500 -f /tmp/input.txt

Using The Python Client:

    python tcpclient.py -hn localhost -p <your port> -f <Your file path>

