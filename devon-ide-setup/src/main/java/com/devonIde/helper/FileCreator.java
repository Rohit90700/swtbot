package com.devonIde.helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.devonIde.constants.Constants;

/**
 * Object of this class "BatchFileCreator" is to create helper setup bat file and write its contents
 *
 */
public class FileCreator {

  public static boolean createBatFile() throws IOException {

    File file = new File(Constants.USER_HOME + File.separator + "SWTBOT-repo" + File.separator + "projects"
        + File.separator + "my-project" + File.separator + "setup-helper.sh");
    file.createNewFile();
    FileWriter fileWriter = new FileWriter(file);
    fileWriter.write("@echo off\r\n" + "\r\n"
        + "for /F \"usebackq tokens=2*\" %%O in (`call \"%SystemRoot%\"\\system32\\reg.exe query \"HKLM\\Software\\GitForWindows\" /v \"InstallPath\" 2^>nul ^| \"%SystemRoot%\\system32\\findstr.exe\" REG_SZ`) do set GIT_HOME=%%P\r\n"
        + "set \"BASH=%GIT_HOME%\\bin\\bash.exe\"\r\n" + "\"%BASH%\" -c 'source ./home-directory'\r\n"
        + "set \"SETTINGS_URL=-\"\r\n" + "call setup.bat > text.txt");
    System.out.println("Bat file created...........");
    fileWriter.flush();
    fileWriter.close();
    return true;
  }

  public static boolean createBashFile() throws IOException {

    File file = new File(Constants.USER_HOME + File.separator + "SWTBOT-repo" + File.separator + "projects"
        + File.separator + "my-project" + File.separator + "home-directory");
    file.createNewFile();
    FileWriter fileWriter = new FileWriter(file);
    fileWriter.write("#!/bin/bash\r\n" + "DEVON_HOME_DIR=~\r\n" + "echo home directory \"${DEVON_HOME_DIR}\"\r\n"
        + "mkdir -p ~/.devon \r\n"
        + "echo -e \"On $(date +\"%Y-%m-%d\") at $(date +\"%H:%M:%S\") you accepted the devonfw-ide terms of use.\\nhttps://github.com/devonfw/ide/blob/master/TERMS_OF_USE.asciidoc\" > \"${DEVON_HOME_DIR}/.devon/.license.agreement\"");
    System.out.println("Bash file created...........");
    fileWriter.flush();
    fileWriter.close();
    return true;
  }

  public static boolean createTextFfile() throws IOException {

    File file = new File(Constants.USER_HOME + File.separator + "SWTBOT-repo" + File.separator + "projects"
        + File.separator + "my-project" + File.separator + "text.txt");
    file.createNewFile();
    System.out.println("Text file created...........");
    return true;
  }

  public static boolean createDevon4jAppWithCommandLine() {

    System.out.println("createDevon4jAppWithCommandLine started...........");

    File projectPath = new File(
        Constants.USER_HOME + File.separator + "SWTBOT-repo" + File.separator + "devon4jproject");
    projectPath.mkdir();

    if (Constants.OS_NAME.startsWith(Constants.WINDOWS)) {
      try {
        Runtime.getRuntime().exec("cmd /c"
            + "devon mvn -DarchetypeVersion=3.2.1 -DarchetypeGroupId=com.devonfw.java.templates -DarchetypeArtifactId=devon4j-template-server archetype:generate -DgroupId=com.company -DartifactId=devon4japp -Dversion=1.0.0-SNAPSHOT -Dpackage=com.test -DdbType=h2 -Dbatch=batch -DinteractiveMode=false:baseCommand",
            null, projectPath);
      } catch (IOException e) {

        e.printStackTrace();
      }
    } else if (Constants.OS_NAME.startsWith(Constants.LINUX)) {

      try {
        Runtime.getRuntime().exec(
            "/bin/bash -c mvn -DarchetypeVersion=3.2.1 -DarchetypeGroupId=com.devonfw.java.templates -DarchetypeArtifactId=devon4j-template-server archetype:generate -DgroupId=com.company -DartifactId=devon4japp -Dversion=1.0.0-SNAPSHOT -Dpackage=com.test -DdbType=h2 -Dbatch=batch -DinteractiveMode=false:baseCommand",
            null, projectPath);
        System.out.println("...........Enviroment is Linux...............");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    } else {

      System.out.println(".............Operating system is not supported.............");
    }

    return true;
  }
}
