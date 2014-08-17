from paramiko.client import AutoAddPolicy

__author__ = 'meng'

import paramiko, base64

hostname = 'localhost'
port = 3000
username = 'meng'
password = 'password'

client = paramiko.SSHClient()
client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
client.connect(hostname, port, username, password)

sftp_client  = client.open_sftp()
files = sftp_client.listdir()

print 'list of files :'
for file in files:
    print '...   ', file