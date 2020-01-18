import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImportantResources {

    public static final String title = "FRC Project Manager";

    public static String getPathFolderChooser(String title, String description, boolean chooseFiles) {

        FileChooserDescriptor fileChooserDescriptor = new FileChooserDescriptor(chooseFiles, true, false, false, false, false);
        fileChooserDescriptor.setTitle(title);
        fileChooserDescriptor.setDescription(description);


        //FileChooser.chooseFile(fileChooserDescriptor, null, virtualFile, null);
        //fileChooserDescriptor.

        VirtualFile files = FileChooser.chooseFile(fileChooserDescriptor, (Component) null, null, null);
        if (files != null) {
            //Messages.showMessageDialog((Project) null,files.getPath(), "PATH", Messages.getInformationIcon());
            return files.getPath();
        }
        return null;

    }


    public static void copyFolder(File sourceFolder, File destinationFolder) throws IOException {
        //Check if sourceFolder is a directory or file
        //If sourceFolder is file; then copy the file directly to new location
        if (sourceFolder.isDirectory()) {
            //Verify if destinationFolder is already present; If not then create it
            if (!destinationFolder.exists()) {
                destinationFolder.mkdir();
                System.out.println("Directory created :: " + destinationFolder);
            }

            //Get all files from source directory
            String files[] = sourceFolder.list();

            //Iterate over all files and copy them to destinationFolder one by one
            for (String file : files) {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);

                //Recursive function call
                copyFolder(srcFile, destFile);
            }
        } else {
            //Copy the file content from one place to another
            Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied :: " + destinationFolder);
        }
    }

    public static void deleteRemoteRepository() {
        if (System.getProperty("os.name").contains("Win")) {
            //Messages.showInfoMessage("your system is " + System.getProperty("os.name"), title);
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd " + System.getProperty("user.home") + " && rmdir /Q /S TecbotFRC_Templates");
            builder.redirectErrorStream(true);
            Process p = null;
            try {
                p = builder.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = null;
                try {
                    line = r.readLine();
                    System.out.println(line);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (line == null) {
                    break;
                }
            }
        } else {
            /*ProcessBuilder builder = new ProcessBuilder();
            builder.command("sh", "-c", "cd "+System.getProperty("user.home")+" && rm -r TecbotFRC_Templates");*/

            ProcessBuilder processBuilder = new ProcessBuilder();

            // -- Linux --

            // Run a shell command
            processBuilder.command("bash", "-c", "cd " + System.getProperty("user.home") + " && rm -r TecbotFRC_Templates");
            try {

                Process process = processBuilder.start();

                StringBuilder output = new StringBuilder();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line + "\n");
                }

                int exitVal = process.waitFor();
                if (exitVal == 0) {
                    System.out.println("Success!");
                    System.out.println(output);
                    System.exit(0);
                } else {
                    //abnormal...
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void downloadRemoteRepository() {

        //DOWNLOAD REMOTE REPOSITORY
        Messages.showInfoMessage("Downloading repositories to " + System.getProperty("user.home") + ", from https://github.com/Tecbot3158/TecbotFRC_Templates", title);
        //FileTemplate f;
        if (System.getProperty("os.name").contains("Win")) {
            deleteRemoteRepository();
            //Messages.showInfoMessage("your system is " + System.getProperty("os.name"), title);
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd " + System.getProperty("user.home") + " && git clone https://github.com/Tecbot3158/TecbotFRC_Templates");
            builder.redirectErrorStream(true);
            Process p = null;
            try {
                p = builder.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = null;
                try {
                    line = r.readLine();
                    System.out.println(line);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        } else {
            ProcessBuilder processBuilder = new ProcessBuilder();

            // -- Linux --

            // Run a shell command
            processBuilder.command("bash", "-c", "cd " + System.getProperty("user.home") + " && git clone https://github.com/Tecbot3158/TecbotFRC_Templates");
            try {

                Process process = processBuilder.start();

                StringBuilder output = new StringBuilder();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line + "\n");
                }

                int exitVal = process.waitFor();
                if (exitVal == 0) {
                    System.out.println("Success!");
                    System.out.println(output);
                    System.exit(0);
                } else {
                    //abnormal...
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("apparently works!");
        Messages.showInfoMessage("Repository has been successfully downloaded!", title);

        //DOWNLOAD REMOTE REPOSITORY ENDS
    }

    public static String getRepositoryPath() {
        return System.getProperty("user.home") + "\\TecbotFRC_Templates\\";
    }

    public static void createProject(@NotNull String templatePathName) {
        if (!((new File(System.getProperty("user.home") + "\\TecbotFRC_Templates\\project_templates\\")).exists())) {
            Messages.showErrorDialog("Directory 'TecbotFRC_Templates' not found in " + System.getProperty("user.dir") + ".\nIt is recommended that you download the repository using the 'UpdateTemplates' button, otherwise you won't be able to use this plugin.", title);
            return;
        }
        String projectName, stringTargetPath;
        File targetPathWithoutProjectName, targetPath, sourceFolder;
        projectName = Messages.showInputDialog("Please enter the project name.", title, getTecbotIcon());
        if (projectName == null) {
            Messages.showErrorDialog("Invalid project name. Please try again.", title);
            return;
        }
        stringTargetPath = ImportantResources.getPathFolderChooser(title, "Please choose a directory in which your project will be. A new folder will be created inside that directory.", false);
        if (stringTargetPath == null) {
            Messages.showErrorDialog("Invalid Directory. Please try again.", title);
            return;
        }
        targetPathWithoutProjectName = new File(stringTargetPath);

        if (!targetPathWithoutProjectName.exists()) {
            Messages.showErrorDialog(targetPathWithoutProjectName + " not found. Please try again.", title);
            return;
        }
        sourceFolder = new File(getRepositoryPath() + "\\project_templates\\" + templatePathName);
        if (!sourceFolder.exists()) {
            Messages.showErrorDialog(sourceFolder + " not found. Please check that the folder " + getRepositoryPath() + " exists.", title);
            return;
        }
        targetPath = new File(stringTargetPath + "\\" + projectName);
        Messages.showInfoMessage("Copying to " + targetPath + "\n from " + sourceFolder, title);
        try {
            ImportantResources.copyFolder(sourceFolder, targetPath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        editJSON_byProjectString(targetPath.toString());
        Messages.showInfoMessage("Project successfully created!", title);

        final ProjectManagerEx projectManager = ProjectManagerEx.getInstanceEx();
        final ProjectManager projectManager1 = ProjectManager.getInstance();
        //Project project = projectManager.newProject(projectName, String.valueOf(targetPath), true, false);
        Project project = projectManager1.createProject(projectName, String.valueOf(targetPath));
        if (project == null) return;
        projectManager.openProject(project);

        //ProjectManagerEx.getInstanceEx().loadProject(targetPath.toString());
    }

    public static void createProjectFromSpecificFolder() {
        String projectName, stringTargetPath;
        File targetPathWithoutProjectName, targetPath, sourceFolder;
        projectName = Messages.showInputDialog("Please enter the project name.", title, getTecbotIcon());
        if (projectName == null) {
            Messages.showErrorDialog("Invalid project name. Please try again.", title);
            return;
        }
        stringTargetPath = ImportantResources.getPathFolderChooser(title, "Please choose a directory in which your project will be. A new folder will be created inside that directory.", false);
        if (stringTargetPath == null) {
            Messages.showErrorDialog("Invalid Directory. Please try again.", title);
            return;
        }
        targetPathWithoutProjectName = new File(stringTargetPath);

        if (!targetPathWithoutProjectName.exists()) {
            Messages.showErrorDialog(targetPathWithoutProjectName + " not found. Please try again.", title);
            return;
        }
        sourceFolder = new File(getPathFolderChooser(title, "Please specify the directory from which you would like to clone the project.", false));
        if (!sourceFolder.exists()) {
            Messages.showErrorDialog(sourceFolder + " not found. Please check that the folder " + getRepositoryPath() + " exists.", title);
            return;
        }
        targetPath = new File(stringTargetPath + "\\" + projectName);
        Messages.showInfoMessage("Copying to " + targetPath + "\n from " + sourceFolder, title);
        try {
            ImportantResources.copyFolder(sourceFolder, targetPath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Messages.showInfoMessage("Project successfully created!", title);

        final ProjectManagerEx projectManager = ProjectManagerEx.getInstanceEx();
        final ProjectManager projectManager1 = ProjectManager.getInstance();
        //Project project = projectManager.newProject(projectName, String.valueOf(targetPath), true, false);
        Project project = projectManager1.createProject(projectName, String.valueOf(targetPath));
        if (project == null) return;
        projectManager.openProject(project);

        //ProjectManagerEx.getInstanceEx().loadProject(targetPath.toString());
    }

    public static void editJsonFile(String pathFileToDelete, Integer teamNumber, String targetFolder) {
        if(pathFileToDelete==null) Messages.showErrorDialog("JSON to delete not found.",title);
        if(targetFolder==null) Messages.showErrorDialog("Target folder not found.",title);

        File oldJSON = new File(pathFileToDelete);
        if (oldJSON.exists()) {
            oldJSON.delete();
        } else {
            Messages.showErrorDialog("File '" + oldJSON.getPath() + "'. Attempted to delete file but not found.", title);
            return;
        }

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("enableCppIntellisense", new Boolean(false));
            jsonObject.put("currentLanguage", "java");
            jsonObject.put("projectYear", new Integer(2019));

            jsonObject.put("teamNumber", new Integer(teamNumber));
        } catch (JSONException e) {
            e.printStackTrace();
            Messages.showErrorDialog("Unable to add property to JSON File", title);
        }
        Messages.showInfoMessage(jsonObject.toString(), title);
        File newFile = new File(targetFolder + "\\wpilib_preferences.json");
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter;
        try {
            fileWriter = new FileWriter(newFile.getPath());

        } catch (IOException e) {
            e.printStackTrace();
            Messages.showErrorDialog("Was not able to create File Writer", title);
        }
        bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.write(jsonObject.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            Messages.showErrorDialog("Was not able to write to file.", title);
        }


    }

    public static void editJSON_byProjectString(String projectPath_) {

        String projectPath = projectPath_;
        String filePath = projectPath + "\\.wpilib\\wpilib_preferences.json";
        String targetPath = projectPath + "\\.wpilib";
        String value = Messages.showInputDialog("Please write the new team number. If not an integer, 0 is default.", title, getTecbotIcon());
        editJsonFile(filePath, Integer.valueOf(value) == null ? 0 : Integer.valueOf(value), targetPath);
    }

    public static Icon getTecbotIcon() {
        return IconLoader.getIcon("META-INF/tecbotIcon.svg");
    }

}
