#!/usr/bin/python

import socket
import argparse
import os
import traceback

class TcpClient:
    
    def __init__(self, hostname, port):
        self.hostname = hostname
        self.port = port
        self.buffer_size = 1024 * 10
        self.connect()
        
    def connect(self):
        self.client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.client_socket.connect((self.hostname, self.port))
        
    def send(self, message):
        msg_to_send = self.enrich_message(message)
        self.client_socket.send(msg_to_send)
        print 'Sent data: %s' % msg_to_send
        data = self.client_socket.recv(self.buffer_size)
        self.client_socket.close()
        print 'Recv data: %s' % data
        
        
    def enrich_message(self, message):
        return '{:0>6d}{}'.format(len(message),message)
    
class TcpClientApp:
    
    def __init__(self):
        self.parser = self.get_argparser()
        
    def get_argparser(self):
        parser = argparse.ArgumentParser(prog=APP_NAME)
        parser.add_argument('-p', '--port', help='port number', type=int, default=2000)
        parser.add_argument('-hn', '--hostname', help='hostname', default='localhost')
        parser.add_argument('-f', '--file', help='file to be sent', default='input.txt')
        
        return parser
    
    def get_parsed_args(self):
        return self.parser.parse_args()
        
    def run(self):
        args = self.get_parsed_args()
        try:                                  
            tcp_client = TcpClient(args.hostname, args.port)
            
            with open(args.file, 'r') as file_obj:
                msg = file_obj.read()
            
            tcp_client.send(msg)
        except:
            traceback.print_exc()
            self.parser.print_help()
            
        

APP_NAME = os.path.basename(__file__)
    
if __name__ == '__main__':    
    tcp_client_app = TcpClientApp()
    tcp_client_app.run()



