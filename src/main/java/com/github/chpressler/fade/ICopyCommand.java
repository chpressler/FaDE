package com.github.chpressler.fade;

public interface ICopyCommand {

    void copy(IFile source, IFile target) throws Exception;

}
