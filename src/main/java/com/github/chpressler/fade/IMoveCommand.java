package com.github.chpressler.fade;

public interface IMoveCommand {

    boolean move(IFile source, IFile target) throws Exception;

}
