package com.jensui.projects.fade.connector.dropbox;

import com.jensui.projects.fade.*;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class DropBoxConnector implements IConnector {

    @Override
    public IFile getFile(URI uri) {
        return null;
    }

    @Override
    public IFile getFile(String path) {
        return null;
    }

    @Override
    public File convert(IFile f) {
        return null;
    }

    @Override
    public List<IFile> getRootFiles() {
        List<IFile> files = new ArrayList<>();
        IFile f = new IFile() {
            @Override
            public int getChildCount() {
                return 0;
            }

            @Override
            public List<IFile> getChildren() {
                return new ArrayList<>();
            }

            @Override
            public String getName() {
                return "DropBox ... ";
            }

            @Override
            public URI getURI() {
                try {
                    return new URI("");
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public boolean isDir() {
                return true;
            }

            @Override
            public String getDescription() {
                return "";
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public long getTotalSpace() {
                return 1500;
            }

            @Override
            public long getFreeSpace() {
                return 1000;
            }

            @Override
            public long getLastChanged() {
                return 0;
            }

            @Override
            public boolean exists() {
                return false;
            }

            @Override
            public File getFile() {
                return null;
            }

            @Override
            public IFile getParent() {
                return null;
            }

            @Override
            public IConnector getConnector() {
                return new DropBoxConnector();
            }

            @Override
            public void setConnector(IConnector connector) {

            }

            @Override
            public boolean mkDir() {
                return false;
            }

            @Override
            public boolean createNewFile() {
                return false;
            }

            @Override
            public boolean setWritable(boolean b) {
                return false;
            }
        };
        files.add(f);
        return files;
    }

    @Override
    public IRenameCommand getRenameCommand() {
        return null;
    }

    @Override
    public ICopyCommand getCopyCommand() {
        return null;
    }

    @Override
    public IDeleteCommand getDeleteCommand() {
        return null;
    }

}
