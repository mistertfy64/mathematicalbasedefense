package com.mathematicalbasedefenders.mathematicalbasedefenders.net;

import com.mathematicalbasedefenders.mathematicalbasedefenders.MathematicalBaseDefenders;
import com.mathematicalbasedefenders.mathematicalbasedefenders.core.Log;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Networking {

    public static BufferedReader in;
    public static PrintWriter out;
    public io.socket.client.Socket socket = null;


    public static String socketID = "";
    public static String currentLoggedInUser = "";
    public static String userIDOfCurrentlyLoggedInUser = "";

    public static boolean loggedIn = false;


    public void configureSocketEvents() {


        MathematicalBaseDefenders.networking.socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                MathematicalBaseDefenders.core.toastNotificationQueue.add("Connected to server!");
            }


        }).on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject jsonObject = (JSONObject) args[0];
                try {
                    socketID = jsonObject.getString("socketID");
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }


        }).on("authenticationResult", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject jsonObject = (JSONObject) args[0];
                try {

                    String result = jsonObject.getString("result");

                    switch (result) {
                        case "success": {
                            String username = jsonObject.getString("username");
                            String userID = jsonObject.getString("userID");


                            MathematicalBaseDefenders.core.toastNotificationQueue.add("Successfully logged in as " + username + "!");

                            currentLoggedInUser = username;
                            userIDOfCurrentlyLoggedInUser = userID;


                            Log.logInfoMessage("Successfully logged in as " + username + "!");
                            break;
                        }
                        case "failed1": {
                            MathematicalBaseDefenders.core.toastNotificationQueue.add("Incorrect password!");
                            Log.logInfoMessage("Incorrect password!");
                            break;
                        }
                        case "failed2": {
                            MathematicalBaseDefenders.core.toastNotificationQueue.add("User doesn't exist!");
                            Log.logInfoMessage("User doesn't exist!");
                            break;
                        }
                    }

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }


        }).on("scoreSubmissionResult", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject jsonObject = (JSONObject) args[0];
                try {

                    String result = jsonObject.getString("result");

                    switch (result) {
                        case "success": {
                            MathematicalBaseDefenders.core.toastNotificationQueue.add("Successfully submitted score!");

                            int rank = jsonObject.getInt("rank");
                            if (rank != -1) {
                                MathematicalBaseDefenders.core.toastNotificationQueue.add("You ranked #" + rank + " on the global leaderboards!");
                            }


                            break;
                        }
                        case "failed1": {
                            MathematicalBaseDefenders.core.toastNotificationQueue.add("Unable to submit score!");
                            Log.logErrorMessage("Unable to submit score!");
                            break;
                        }
                    }

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        });
    }


}
