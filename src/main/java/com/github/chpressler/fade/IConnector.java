package com.github.chpressler.fade;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

public interface IConnector {

    public IFile getFile(URI uri);

    public IFile getFile(String path);

    public File readFile(IFile f) throws IOException;

    public List<IFile> getRootFiles();

    public IRenameCommand getRenameCommand();

    public ICopyCommand getCopyCommand();

    public IDeleteCommand getDeleteCommand();

    public IMoveCommand getMoveCommand();

    public ICreateCommand getCreateCommand();

}
