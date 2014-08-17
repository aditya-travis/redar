Build And Run
================

Start the Local Sftp Server:
----------------------------

    mvn clean install
    cd <the target dir>
    java -jar mina-sftp.jar -p 3000  (only with password auth - user:meng password:password)
    or java -jar mina-sftp.jar -p 3000 -k (enable public key authentication)

Verify the Sftp Server from commandline
----------------------------------------

  using UNIX cmd line tool sftp

       sftp -P 3000 meng@localhost

  using python paramiko sftp client

       python sftp_client.py