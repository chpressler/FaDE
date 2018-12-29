package com.github.chpressler.fade;

public interface ICreateCommand {

    boolean createFile(String name, IFile dir) throws Exception;

    boolean createDirectory(String name, IFile dir) throws Exception;

}
