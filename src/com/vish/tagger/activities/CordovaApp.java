/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.vish.tagger.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.cordova.CordovaActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.vish.tagger.dto.Album;

public class CordovaApp extends CordovaActivity
{
	 // the items (songs) we have queried
    private static List<Album> mItems = new ArrayList<Album>();
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init();
        // Set by <content src="index.html" /> in config.xml
        loadAllAlbums();
        WebView.setWebContentsDebuggingEnabled(true);//added for debugging
        //loadUrl("file:///android_asset/www/index.html");
        //loadUrl("file:///android_asset/www/polymer/core-scroll-header-panel/demo.html");
        loadUrl("file:///android_asset/www/html/AlbumList.html");
        
    }

    public static String getMItems(){
    	StringBuffer output = new StringBuffer();
    	output.append("<table style=\"width:100%\">");
    	for(Album album : mItems){
    		output.append("<tr>");
    		output.append(album.toString());
    		output.append("</tr>");
    	}
    	Gson gson = new GsonBuilder().create();
    	JsonArray myCustomArray = gson.toJsonTree(mItems).getAsJsonArray(); 
		return myCustomArray.toString();
    	
    } 
    
    private void loadAllAlbums(){
    	String where = null;
        ContentResolver cr = this.getApplicationContext().getContentResolver();
        final Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        //String[]columns={id,albumId,albumName, artist,albumArt,numberOfSongs};
        
        Cursor cursor = cr.query(uri,null,where,null, null);
        
        int id = cursor.getColumnIndex(MediaStore.Audio.Albums._ID); 
        int albumName =cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
        int artist = cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST);
        int albumArt = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
        int numberOfSongs =  cursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS);
        
        if (cursor==null || !cursor.moveToFirst()) {
            // Nothing to query. There is no music on the device. How boring.
            Log.e(TAG, "Failed to move cursor to first row (no query results).");
            return;
        }
        
        do {
            Log.i(TAG, "ID: " + cursor.getLong(id) + " Album Name: " + cursor.getBlob(albumName));
            mItems.add(
            		new Album(cursor.getLong(id), cursor.getString(artist), cursor.getString(artist), cursor.getString(albumName), cursor.getString(albumArt),cursor.getBlob(albumArt) , cursor.getLong(numberOfSongs))
            );
        } while (cursor.moveToNext());
        
        Log.i(TAG,"Completed loading albums");
    }
    
    
    /*private void loadAllSongs(){
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Log.i(TAG, "Querying media...");
        Log.i(TAG, "URI: " + uri.toString());

        // Perform a query on the content resolver. The URI we're passing specifies that we
        // want to query for all audio media on external storage (e.g. SD card)
        Cursor cur = this.getApplicationContext().getContentResolver().query(uri, null,
                MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);
        Log.i(TAG, "Query finished. " + (cur == null ? "Returned NULL." : "Returned a cursor."));

        if (cur == null) {
            // Query failed...
            Log.e(TAG, "Failed to retrieve music: cursor is null :-(");
            return;
        }
        if (!cur.moveToFirst()) {
            // Nothing to query. There is no music on the device. How boring.
            Log.e(TAG, "Failed to move cursor to first row (no query results).");
            return;
        }

        Log.i(TAG, "Listing...");

        // retrieve the indices of the columns where the ID, title, etc. of the song are
        int artistColumn = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int titleColumn = cur.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int albumColumn = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int durationColumn = cur.getColumnIndex(MediaStore.Audio.Media.DURATION);
        int idColumn = cur.getColumnIndex(MediaStore.Audio.Media._ID);

        Log.i(TAG, "Title column index: " + String.valueOf(titleColumn));
        Log.i(TAG, "ID column index: " + String.valueOf(titleColumn));

        // add each song to mItems
        do {
            Log.i(TAG, "ID: " + cur.getString(idColumn) + " Title: " + cur.getString(titleColumn));
            mItems.add(new Song(
                    cur.getLong(idColumn),
                    cur.getString(artistColumn),
                    cur.getString(titleColumn),
                    cur.getString(albumColumn),
                    cur.getLong(durationColumn)));
        } while (cur.moveToNext());

        Log.i(TAG, "Done querying media. MusicRetriever is ready.");

    }*/
    }
