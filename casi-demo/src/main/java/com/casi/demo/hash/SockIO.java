package com.casi.demo.hash;

import com.casi.demo.hash.ConnectThread;
import com.casi.demo.hash.SockIOPool;
import org.apache.log4j.Logger;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
    /**
	 * MemCached Java client, utility class for Socket IO.
	 *
	 * This class is a wrapper around a Socket and its streams.
	 *
	 * @author greg whalin <greg@meetup.com>
	 * @author Richard 'toast' Russo <russor@msoe.edu>
	 * @version 1.2
	 */
	public  class SockIO {

		// logger
		private static Logger log =
			Logger.getLogger( SockIO.class.getName() );

		// pool
		private SockIOPool pool;

		// data
		private String host;
		private Socket sock;
		private DataInputStream in;
		private BufferedOutputStream out;

		/**
		 * creates a new com.casi.demo.hash.SockIO object wrapping a socket
		 * connection to host:port, and its input and output streams
		 *
		 * @param pool Pool this object is tied to
		 * @param host host to connect to
		 * @param port port to connect to
		 * @param timeout int ms to block on data for read
		 * @param connectTimeout timeout (in ms) for initial connection
		 * @param noDelay TCP NODELAY option?
		 * @throws java.io.IOException if an io error occurrs when creating socket
		 * @throws java.net.UnknownHostException if hostname is invalid
		 */
		public SockIO( SockIOPool pool, String host, int port, int timeout, int connectTimeout, boolean noDelay ) throws IOException, UnknownHostException {

			this.pool = pool;

			sock = ( connectTimeout > 0 )
				? getSocket( host, port, connectTimeout )
				: new Socket( host,port );

			if (timeout >= 0)
				sock.setSoTimeout( timeout );

			// testing only
			sock.setTcpNoDelay( noDelay );

			// wrap streams
			in  = new DataInputStream( sock.getInputStream() );
			out = new BufferedOutputStream( sock.getOutputStream() );

			this.host = host + ":" + port;
		}

		/**
		 * creates a new com.casi.demo.hash.SockIO object wrapping a socket
		 * connection to host:port, and its input and output streams
		 *
		 * @param host hostname:port
		 * @param timeout read timeout value for connected socket
		 * @param connectTimeout timeout for initial connections
		 * @param noDelay TCP NODELAY option?
		 * @throws java.io.IOException if an io error occurrs when creating socket
		 * @throws java.net.UnknownHostException if hostname is invalid
		 */
		public SockIO( SockIOPool pool, String host, int timeout, int connectTimeout, boolean noDelay ) throws IOException, UnknownHostException {

			this.pool = pool;

			String[] ip = host.split(":");

			// get socket: default is to use non-blocking connect
			sock = ( connectTimeout > 0 )
				? getSocket( ip[ 0 ], Integer.parseInt( ip[ 1 ] ), connectTimeout )
				: new Socket( ip[ 0 ], Integer.parseInt( ip[ 1 ] ) );

			if ( timeout >= 0 )
				sock.setSoTimeout( timeout );

			// testing only
			sock.setTcpNoDelay( noDelay );

			// wrap streams
			in   = new DataInputStream( sock.getInputStream() );
			out  = new BufferedOutputStream( sock.getOutputStream() );
			this.host = host;
		}

		/**
		 * Method which spawns thread to get a connection and then enforces a timeout on the initial
		 * connection.
		 *
		 * This should be backed by a thread pool.  Any volunteers?
		 *
		 * @param host host to establish connection to
		 * @param port port on that host
		 * @param timeout connection timeout in ms
		 * @return connected socket
		 * @throws java.io.IOException if errors connecting or if connection times out
		 */
		protected static Socket getSocket( String host, int port, int timeout ) throws IOException {

			// Create a new thread which will attempt to connect to host:port, and start it running
			ConnectThread thread = new ConnectThread( host, port );
			thread.start();

			int timer     = 0;
			int sleep     = 25;

			while ( timer < timeout ) {

				// if the thread has a connected socket
				// then return it
				if ( thread.isConnected() )
					return thread.getSocket();

				// if the thread had an error
				// then throw a new IOException
				if ( thread.isError() )
					throw new IOException();

				try {
					// sleep for short time before polling again
					Thread.sleep( sleep );
				}
				catch ( InterruptedException ie ) { }

				// Increment timer
				timer += sleep;
			}

			// made it through loop without getting connection
			// the connection thread will timeout on its own at OS timeout
			throw new IOException( "Could not connect for " + timeout + " milliseconds" );
		}

		/**
		 * returns the host this socket is connected to
		 *
		 * @return String representation of host (hostname:port)
		 */
		String getHost() { return this.host; }

		/**
		 * closes socket and all streams connected to it
		 *
		 * @throws java.io.IOException if fails to close streams or socket
		 */
		void trueClose() throws IOException {
			log.debug( "++++ Closing socket for real: " + toString() );

			boolean err = false;
			StringBuffer errMsg = new StringBuffer();

			if ( in == null || out == null || sock == null ) {
				err = true;
				errMsg.append( "++++ socket or its streams already null in trueClose call" );
			}

			if ( in != null ) {
				try {
					in.close();
				}
				catch( IOException ioe ) {
					log.error( "++++ error closing input stream for socket: " + toString() + " for host: " + getHost() );
					log.error( ioe.getMessage(), ioe );
					errMsg.append( "++++ error closing input stream for socket: " + toString() + " for host: " + getHost() + "\n" );
					errMsg.append( ioe.getMessage() );
					err = true;
				}
			}

			if ( out != null ) {
				try {
					out.close();
				}
				catch ( IOException ioe ) {
					log.error( "++++ error closing output stream for socket: " + toString() + " for host: " + getHost() );
					log.error( ioe.getMessage(), ioe );
					errMsg.append( "++++ error closing output stream for socket: " + toString() + " for host: " + getHost() + "\n" );
					errMsg.append( ioe.getMessage() );
					err = true;
				}
			}

			if ( sock != null ) {
				try {
					sock.close();
				}
				catch ( IOException ioe ) {
					log.error( "++++ error closing socket: " + toString() + " for host: " + getHost() );
					log.error( ioe.getMessage(), ioe );
					errMsg.append( "++++ error closing socket: " + toString() + " for host: " + getHost() + "\n" );
					errMsg.append( ioe.getMessage() );
					err = true;
				}
			}

			// check in to pool
			if ( sock != null )
				pool.checkIn( this, false );

			in = null;
			out = null;
			sock = null;

			if ( err )
				throw new IOException( errMsg.toString() );
		}

		/**
		 * sets closed flag and checks in to connection pool
		 * but does not close connections
		 */
		void close() {
			// check in to pool
			log.debug("++++ marking socket (" + this.toString() + ") as closed and available to return to avail pool");
			pool.checkIn( this );
		}

		/**
		 * checks if the connection is open
		 *
		 * @return true if connected
		 */
		boolean isConnected() {
			return (sock != null && sock.isConnected());
		}

		/**
		 * reads a line
		 * intentionally not using the deprecated readLine method from DataInputStream
		 *
		 * @return String that was read in
		 * @throws java.io.IOException if io problems during read
		 */
		String readLine() throws IOException {
			if (sock == null || !sock.isConnected()) {
				log.error("++++ attempting to read from closed socket");
				throw new IOException("++++ attempting to read from closed socket");
			}

			byte[] b = new byte[1];
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			boolean eol = false;

			while (in.read(b, 0, 1) != -1) {

				if (b[0] == 13) {
					eol = true;

				} else {
					if (eol) {
						if (b[0] == 10)
							break;

						eol = false;
					}
				}

				// cast byte into char array
				bos.write(b, 0, 1);
			}

			if (bos == null || bos.size() <= 0) {
				throw new IOException("++++ Stream appears to be dead, so closing it down");
			}

			// else return the string
			return bos.toString().trim();
		}

		/**
		 * reads up to end of line and returns nothing
		 *
		 * @throws java.io.IOException if io problems during read
		 */
		void clearEOL() throws IOException {
			if (sock == null || !sock.isConnected()) {
				log.error("++++ attempting to read from closed socket");
				throw new IOException("++++ attempting to read from closed socket");
			}

			byte[] b = new byte[1];
			boolean eol = false;
			while (in.read(b, 0, 1) != -1) {

				// only stop when we see
				// \r (13) followed by \n (10)
				if (b[0] == 13) {
					eol = true;
					continue;
				}

				if (eol) {
					if (b[0] == 10)
						break;

					eol = false;
				}
			}
		}

		/**
		 * reads length bytes into the passed in byte array from dtream
		 *
		 * @param b byte array
		 * @throws java.io.IOException if io problems during read
		 */
		void read(byte[] b) throws IOException {
			if (sock == null || !sock.isConnected()) {
				log.error("++++ attempting to read from closed socket");
				throw new IOException("++++ attempting to read from closed socket");
			}

			int count = 0;
			while (count < b.length) {
				int cnt = in.read(b, count, (b.length - count));
				count += cnt;
			}
		}

		/**
		 * flushes output stream
		 *
		 * @throws java.io.IOException if io problems during read
		 */
		void flush() throws IOException {
			if (sock == null || !sock.isConnected()) {
				log.error("++++ attempting to write to closed socket");
				throw new IOException("++++ attempting to write to closed socket");
			}
			out.flush();
		}

		/**
		 * writes a byte array to the output stream
		 *
		 * @param b byte array to write
		 * @throws java.io.IOException if an io error happens
		 */
		void write(byte[] b) throws IOException {
			if (sock == null || !sock.isConnected()) {
				log.error("++++ attempting to write to closed socket");
				throw new IOException("++++ attempting to write to closed socket");
			}
			out.write(b);
		}

		/**
		 * use the sockets hashcode for this object
		 * so we can key off of SockIOs
		 *
		 * @return int hashcode
		 */
		public int hashCode() {
			return (sock == null) ? 0 : sock.hashCode();
		}

		/**
		 * returns the string representation of this socket
		 *
		 * @return string
		 */
		public String toString() {
			return (sock == null) ? "" : sock.toString();
		}
	}
