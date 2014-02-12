package app.models;

import app.db.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import org.bson.types.ObjectId;

import java.lang.Boolean;
import java.lang.String;
import java.lang.System;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import app.tools.Json;

public class Postit extends BasicDBObject {

  public static String collectionName = "postitsCollection";

  public Postit(String text) {
    this.put("text", text);
    this.put("star", false);
  }

  public Postit() {
    this.put("text", "?");
    this.put("star", false);
  }

  public static Postit getInstanceFromJson(String jsonString) {
    Postit p = new Postit();
    p.putAll(Json.fromJson(Json.parse(jsonString), java.util.TreeMap.class));
    if(p.get("_id")!=null) { p.put("_id", new ObjectId(p.get("_id").toString())); }
    return p;
  }

  public void fromJsonString(String jsonString) {
    this.putAll(Json.fromJson(Json.parse(jsonString), java.util.TreeMap.class));
    if(this.get("_id")!=null) { this.put("_id", new ObjectId(this.get("_id").toString())); }
  }

  public Boolean insert() { //todo try catch
    Database.getCollection(collectionName).insert(this);
    return true;
  }

  public Boolean update() { //todo try catch
    String id = this.getId();
    BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(id));
    Database.getCollection(collectionName).update(searchQuery, this);
    return true;
  }

  public Boolean fetch(String id){ //todo try catch
    BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(id));
    this.putAll(Database.getCollection(collectionName).findOne(searchQuery));
    return true;
  }

  public Boolean remove(String id){ //todo try catch
    BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(id));
    this.putAll(Database.getCollection(collectionName).findOne(searchQuery));
    Database.getCollection(collectionName).remove(this);
    return true;
  }


  public String getId() {
    return this.getObjectId("_id").toString();
  }

  public HashMap<String,Object> readable() {
    HashMap<String,Object> fields = (HashMap<String,Object>) this.toMap();
    fields.put("_id", this.getId());
    return fields;
  }

  public static LinkedList<Postit> fetchAll() {
    DBCursor cursor = Database.getCollection(collectionName).find();
    LinkedList<Postit> postIts = new LinkedList<Postit>() ;

    while (cursor.hasNext()) {
      Postit postit = new Postit();
      postit.putAll(cursor.next().toMap());
      postIts.add(postit);

    }
    cursor.close();
    return postIts;
  }

  public static LinkedList<HashMap<String,Object>> fetchAllReadable() {
    DBCursor cursor = Database.getCollection(collectionName).find();

    LinkedList<HashMap<String,Object>> postIts = new LinkedList<HashMap<String,Object>>() ;

    while (cursor.hasNext()) {
      Postit postit = new Postit();
      postit.putAll(cursor.next().toMap());
      postIts.add(postit.readable());
    }
    cursor.close();
    return postIts;
  }


}