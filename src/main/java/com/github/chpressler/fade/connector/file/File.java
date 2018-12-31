package com.github.chpressler.fade.connector.file;

import com.github.chpressler.fade.IConnector;
import com.github.chpressler.fade.IFile;

import java.io.*;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class File implements IFile {

    private java.io.File file;

    public File(java.io.File f, IConnector connector) {
        this.file = f;
        setConnector(connector);
    }

    IConnector connector;

    @Override
    public IConnector getConnector() {
        return connector;
    }

    @Override
    public void setConnector(IConnector connector) {
        this.connector = connector;
    }

    @Override
    public boolean mkDir() {
        return file.mkdir();
    }

    @Override
    public boolean createNewFile() {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean setWritable(boolean b) {
        return file.setWritable(b);
    }

    @Override
    public java.io.File getFile() {
        return file;
    }

    @Override
    public int getChildCount() {
        return file.list().length;
    }

    @Override
    public List<IFile> getChildren() {
        return Arrays.stream(file.listFiles()).map(f -> new File(f, connector)).collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public URI getURI() {
        return file.toURI();
    }

    @Override
    public boolean isDir() {
        return file.isDirectory();
    }

    @Override
    public String getDisplayName() {
        return getURI().getPath();
    }

    @Override
    public long getSize() {
        return file.length();
    }

    @Override
    public long getTotalSpace() {
        return file.getTotalSpace();
    }

    @Override
    public long getFreeSpace() {
        return file.getFreeSpace();
    }

    @Override
    public long getLastChanged() {
        return file.lastModified();
    }

    @Override
    public boolean exists() {
        return file.exists();
    }

    @Override
    public InputStream readFile() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public IFile getParent() {
        if(file.getParentFile() == null) {
            return null;
        }
        return new File(file.getParentFile(), connector);
    }
}
