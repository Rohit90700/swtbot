package com.devonIde.helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Object of this class "BatchFileCreator" is to create helper setup bat file and write its contents
 *
 */
public class FileCreator {

  public static boolean createBatFile() throws IOException {

    File file = new File("\\SWTBOT-repo\\projects\\my-project\\setup-helper.bat");
    file.createNewFile();
    FileWriter fileWriter = new FileWriter(file);
    fileWriter.write("@echo off\r\n" + "\r\n"
        + "for /F \"usebackq tokens=2*\" %%O in (`call \"%SystemRoot%\"\\system32\\reg.exe query \"HKLM\\Software\\GitForWindows\" /v \"InstallPath\" 2^>nul ^| \"%SystemRoot%\\system32\\findstr.exe\" REG_SZ`) do set GIT_HOME=%%P\r\n"
        + "set \"BASH=%GIT_HOME%\\bin\\bash.exe\"\r\n" + "\"%BASH%\" -c 'source ./home-directory'\r\n"
        + "set \"SETTINGS_URL=-\"\r\n" + "call setup.bat > text.txt");
    fileWriter.flush();
    fileWriter.close();
    return true;
  }

  public static boolean createBashFile() throws IOException {

    File file = new File("\\SWTBOT-repo\\projects\\my-project\\home-directory");
    file.createNewFile();
    FileWriter fileWriter = new FileWriter(file);
    fileWriter.write("#!/bin/bash\r\n" + "DEVON_HOME_DIR=~\r\n" + "echo home directory \"${DEVON_HOME_DIR}\"\r\n"
        + "mkdir -p ~/.devon \r\n"
        + "echo -e \"On $(date +\"%Y-%m-%d\") at $(date +\"%H:%M:%S\") you accepted the devonfw-ide terms of use.\\nhttps://github.com/devonfw/ide/blob/master/TERMS_OF_USE.asciidoc\" > \"${DEVON_HOME_DIR}/.devon/.license.agreement\"");
    fileWriter.flush();
    fileWriter.close();
    return true;
  }

  public static boolean createTextFfile() throws IOException {

    File file = new File("\\SWTBOT-repo\\projects\\my-project\\text.txt");
    file.createNewFile();
    return true;
  }

  public static boolean createDevon4jAppWithCommandLine() {

    File projectPath = new File("\\SWTBOT-repo\\devon4jproject");
    projectPath.mkdir();
    try {
      Runtime.getRuntime().exec("cmd /c"
          + "mvn -DarchetypeVersion=3.2.1 -DarchetypeGroupId=com.devonfw.java.templates -DarchetypeArtifactId=devon4j-template-server archetype:generate -DgroupId=com.company -DartifactId=devon4japp -Dversion=1.0.0-SNAPSHOT -Dpackage=com.test -DdbType=h2 -Dbatch=batch -DinteractiveMode=false:baseCommand",
          null, projectPath);
    } catch (IOException e) {

      e.printStackTrace();
    }
    return true;
  }
}
