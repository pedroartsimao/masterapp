package com.pedroarthursimao.masterapp.sqlite;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class SQLiteTools {

    private String databaseName;
    private Context context;

    public SQLiteTools(Context context, String databaseName) {
        this.context = context;
        this.databaseName = databaseName;
    }

    public boolean exportDatabase(File to) {
        File from = this.getCurrentDatabaseFile();
        if(!to.getParentFile().exists())
            to.getParentFile().mkdirs();
        return execute(from, to);
    }

    public boolean importDatabase(File from) {
        File to = this.getCurrentDatabaseFile();
        return execute(from, to);
    }

    private File getCurrentDatabaseFile() {
        return new File(context.getDatabasePath(databaseName).getPath());
    }

    private boolean execute(File from, File to){
        try {
            copyFile(from, to);
            Log.i("SQLITETOOLS FROM/TO", from.getAbsolutePath() + "/" + to.getAbsolutePath());
            return true;
        } catch (IOException e) {
            Log.i("SQLITETOOLS ERROR", e.getMessage());
        }
        return false;
    }

    private void copyFile(File from, File to) throws IOException {
        FileInputStream in = new FileInputStream(from);
        FileOutputStream out = new FileOutputStream(to);
        FileChannel fromChannel = null, toChannel = null;
        try {
            fromChannel = in.getChannel();
            toChannel = out.getChannel();
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } finally {
            if (fromChannel != null)
                fromChannel.close();
            if (toChannel != null)
                toChannel.close();
        }
    }

}
