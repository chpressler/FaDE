package com.github.chpressler.fade;

import com.github.chpressler.fade.connector.dropbox.DropBoxConnector;
import com.github.chpressler.fade.connector.file.FileConnector;

import java.util.ArrayList;
import java.util.List;

public class ConnectorManager {

    static ConnectorManager instance = new ConnectorManager();

    List<IConnector> connectors;

    private ConnectorManager() {
        connectors = new ArrayList<IConnector>();
        //TODO -> scan for installed connectors
        connectors.add(new FileConnector());
        connectors.add(new DropBoxConnector());
    }

    public static ConnectorManager getInstance() {
        return instance;
    }

    public void addConnector(IConnector c) {
        connectors.add(c);
    }

    public List<IConnector> getConnectors() {
        return connectors;
    }

}
