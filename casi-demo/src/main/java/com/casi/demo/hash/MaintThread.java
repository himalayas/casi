package com.casi.demo.hash;

import com.casi.demo.hash.SockIOPool;

/**
 * User: Think
 * Date: 12-11-20
 * Time: 下午9:15
 */
    /**
     * Class which extends thread and handles maintenance of the pool.
     *
     * @author greg whalin <greg@meetup.com>
     * @version 1.3.2
     */
    public  class MaintThread extends Thread {

        private SockIOPool pool;
        private long interval      = 1000 * 3; // every 3 seconds
        private boolean stopThread = false;
        private boolean running;

        protected MaintThread( SockIOPool pool ) {
            this.pool = pool;
            this.setDaemon( true );
        }

        public void setInterval( long interval ) { this.interval = interval; }

        public boolean isRunning() {
            return this.running;
        }

        /**
         * sets stop variable
         * and interupts any wait
         */
        public void stopThread() {
            this.stopThread = true;
            this.interrupt();
        }

        /**
         * Start the thread.
         */
        public void run() {
            this.running = true;

            while ( !this.stopThread ) {
                try {
                    Thread.sleep( interval );

                    // if pool is initialized, then
                    // run the maintenance method on itself
                    if ( pool.isInitialized() )
                        pool.selfMaint();

                }
                catch ( Exception e ) {
                    break;
                }
            }

            this.running = false;
        }
    }
