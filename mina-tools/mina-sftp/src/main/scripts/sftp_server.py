__author__ = 'meng'

import paramiko

sftp_server = paramiko.SFTPServer()

sftp_server.start()