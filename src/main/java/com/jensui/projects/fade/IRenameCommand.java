package com.jensui.projects.fade;

public interface IRenameCommand {

    boolean rename(IFile file, String newName) throws Exception;

}
