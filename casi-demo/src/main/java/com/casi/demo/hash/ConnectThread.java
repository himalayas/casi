package com.casi.demo.hash;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.Socket;

    /**
     * Thread to attempt connection.
     * This will be polled by the main thread. We run the risk of filling up w/
     * threads attempting connections if network is down.  However, the falling off
     * mech in the main code should limit this.
     *
     * @author greg whalin <greg@meetup.com>
     * @version 1.2
     */
    public  class ConnectThread extends Thread {

        // logger
        private static Logger log =
            Logger.getLogger(ConnectThread.class.getName());

        private Socket socket;
        private String host;
        private int port;
        boolean error;

        /**
         * Constructor
         *
         * @param host
         * @param port
         */
        public ConnectThread(String host, int port) {
            this.host    = host;
            this.port    = port;
            this.socket  = null;
            this.error   = false;
            this.setDaemon(true);
        }

        /**
         * start thread running.
         * This attempts to establish a connection.
         */
        public void run() {
            try {
                socket = new Socket(host, port);
            }
            catch (IOException ioe) {
                error = true;
            }

            log.debug("socket creation thread leaving for host: " + host);
        }

        /**
         * Is the new socket connected yet
         *
         * @return
         */
        public boolean isConnected() {
            return (socket != null && socket.isConnected())
                ? true
                : false;
        }

        /**
         * Did we have an exception while connecting?
         *
         * @return
         */
        public boolean isError() {
            return error;
        }

        /**
         * Return the socket.
         *
         * @return
         */
        public Socket getSocket() {
            return socket;
        }
    }
