package com.jensui.projects.fade;

import java.io.File;
import java.net.URI;
import java.util.List;

public interface IConnector {

    public IFile getFile(URI uri);

    public IFile getFile(String path);

    public File convert(IFile f);

    public List<IFile> getRootFiles();

    public IRenameCommand getRenameCommand();

    public ICopyCommand getCopyCommand();

    public IDeleteCommand getDeleteCommand();

}
