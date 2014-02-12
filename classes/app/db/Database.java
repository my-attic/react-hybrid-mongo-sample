package app.db;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.lang.String;
import java.net.UnknownHostException;

public class Database {

  public static MongoClient mongoClient;
  public static DB dataBase;

  public static void configuration(String host, Integer port, String dbname) {
    try {
      mongoClient = new MongoClient(host, port);
      dataBase = mongoClient.getDB(dbname);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }
  public static DBCollection getCollection(String collectionName) {
    return dataBase.getCollection(collectionName);
  }

  public Database(String host, Integer port, String dbname) {
    configuration(host, port, dbname);
  }

}