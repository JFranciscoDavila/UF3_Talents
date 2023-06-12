package com.balmongo.restaurants;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class App {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    static {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("restaurantdb");
        collection = database.getCollection("restaurants");
    }

    public static void main(String[] args) {
        listRestaurants();
        filterRestaurantsByBorough();
        insertNewRestaurant();
    }

    private static void listRestaurants() {
        System.out.println("Listado de restaurantes:");
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            String name = document.getString("name");
            String cuisine = document.getString("cuisine");
            String borough = document.getString("borough");
            System.out.println("Nombre: " + name);
            System.out.println("Tipo de cocina: " + cuisine);
            System.out.println("Barrio: " + borough);
            System.out.println("---------------------------");
        }
        cursor.close();
    }

    private static void filterRestaurantsByBorough() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el nombre del barrio:");
        try {
            String borough = reader.readLine();
            System.out.println("Restaurantes en el barrio " + borough + ":");
            Document filter = new Document("borough", borough);
            MongoCursor<Document> cursor = collection.find(filter).iterator();
            while (cursor.hasNext()) {
                Document document = cursor.next();
                String name = document.getString("name");
                String cuisine = document.getString("cuisine");
                System.out.println("Nombre: " + name);
                System.out.println("Tipo de cocina: " + cuisine);
                System.out.println("---------------------------");
            }
            cursor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertNewRestaurant() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Ingrese los datos del nuevo restaurante:");
            System.out.print("Nombre: ");
            String name = reader.readLine();
            System.out.print("Tipo de cocina: ");
            String cuisine = reader.readLine();
            System.out.print("Barrio: ");
            String borough = reader.readLine();

            Document restaurant = new Document("name", name)
                    .append("cuisine", cuisine)
                    .append("borough", borough);

            collection.insertOne(restaurant);

            System.out.println("Restaurante agregado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
