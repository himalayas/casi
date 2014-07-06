package datastax.cassandra;

import com.datastax.driver.core.*;

/**
 * Created by Kevin on 2014/5/7.
 */
public class CassandraSimpleClient {
    private Cluster cluster;

    public void connect(String node) {
        cluster = Cluster.builder().addContactPoint(node).build();
        Metadata metadata = cluster.getMetadata();
        Session session = cluster.connect();
/*        session.execute("CREATE KEYSPACE xiujguo WITH replication= {'class':'SimpleStrategy', 'replication_factor':3};");
        session.execute(
                "CREATE TABLE xiujguo.songs (" +
                        "id uuid PRIMARY KEY," +
                        "title text," +
                        "album text," +
                        "artist text," +
                        "tags set<text>," +
                        "data blob" +
                        ");"
        );
        session.execute(
                "CREATE TABLE xiujguo.playlists (" +
                        "id uuid," +
                        "title text," +
                        "album text, " +
                        "artist text," +
                        "song_id uuid," +
                        "PRIMARY KEY (id, title, album, artist)" +
                        ");"
        );


        session.execute(
                "INSERT INTO xiujguo.songs (id, title, album, artist, tags) " +
                        "VALUES (" +
                        "756716f7-2e54-4715-9f00-91dcbea6cf50," +
                        "'La Petite Tonkinoise'," +
                        "'Bye Bye Blackbird'," +
                        "'Joséphine Baker'," +
                        "{'jazz', '2013'})" +
                        ";"
        );
        session.execute(
                "INSERT INTO xiujguo.playlists (id, song_id, title, album, artist) " +
                        "VALUES (" +
                        "2cc9ccb7-6221-4ccb-8387-f22b6a1b354d," +
                        "756716f7-2e54-4715-9f00-91dcbea6cf50," +
                        "'La Petite Tonkinoise'," +
                        "'Bye Bye Blackbird'," +
                        "'Josphine Baker'" +
                        ");"
        );*/

        ResultSet results = session.execute("SELECT * FROM xiujguo.playlists WHERE id = 2cc9ccb7-6221-4ccb-8387-f22b6a1b354d;");

        System.out.println(String.format("%-30s\t%-20s\t%-20s\n%s", "title","album", "artist",
                "-------------------------------+-----------------------+--------------------"));
        for (Row row : results) {
            System.out.println(String.format("%-30s\t%-20s\t%-20s",
                    row.getString("title"),
                    row.getString("album"), row.getString("artist")));
        }
        System.out.println();
        System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(), host.getRack());
        }
    }

    public void close() {
        cluster.close();
    }

    public static void main(String[] args) {
        CassandraSimpleClient client = new CassandraSimpleClient();
        client.connect("10.224.57.167");
        client.close();
    }
}
