import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class CreateNewCommandBasedProject extends com.intellij.openapi.actionSystem.AnAction {


    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        /*
        //int hi = Messages.showCheckboxMessageDialog("Select the Template", "Tecbot FRC New Project", new String[]{"Timed","Command","TecbotStandard","4","5"},"checkboxText",true,3,3,null,null);
        String projectName = Messages.showInputDialog("Please enter project name:", "Tecbot FRC Project Manager", null);
        //Messages.showMessageDialog("projectName", "titles", null);
        System.out.println(projectName);
        String targetDirectory = ImportantResources.getPathFolderChooser("Tecbot FRC Template Manager","Please enter project path")+projectName;
        System.out.println(targetDirectory);
        File sourceDirectory = new File("/templates/Tecbot2019Test");
        System.out.println(System.getProperty("user.dir"));
        //System.getProperty();

        try {
            copyFolder(sourceDirectory, new File(targetDirectory));
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/




        /*
        String projectName = Messages.showInputDialog("Please enter the project name.","Tecbot FRC Project Manager",null);
        if(projectName==null){
            Messages.showErrorDialog("Invalid project name. Please try again", "Tecbot FRC Template Manager");
            return;
        }
        String stringTargetPath = ImportantResources.getPathFolderChooser(ImportantResources.title,"Please choose a directory in which your project will be. A new folder will be created inside that directory.");
        if(stringTargetPath==null){
            Messages.showErrorDialog("Invalid Directory.","Tecbot FRC Template Manager");
        }
        File targetPathWithoutProjectName = new File(stringTargetPath);
        File sourceFolder = new File("C:\\TecbotFRC_Templates\\project_templates\\frc\\Command Robot");
        if (!sourceFolder.exists()) {
            Messages.showErrorDialog(sourceFolder + " not found.", "Tecbot FRC Templates Manager");
            return;
        }

        if (!targetPathWithoutProjectName.exists()) {
            Messages.showErrorDialog(targetPathWithoutProjectName + " not found.", "Tecbot FRC Templates Manager");
            return;
        }
        File targetPath = new File(stringTargetPath+"\\"+projectName);
        Messages.showInfoMessage("Copying to "+targetPath+" from "+sourceFolder,"Tecbot FRC TemplateManager");
        try {
            ImportantResources.copyFolder(sourceFolder,targetPath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Messages.showInfoMessage("Project successfully created!",ImportantResources.title);

        final ProjectManagerEx projectManager = ProjectManagerEx.getInstanceEx();
        final ProjectManager projectManager1 = ProjectManager.getInstance();
        //Project project = projectManager.newProject(projectName, String.valueOf(targetPath), true, false);
        Project project = projectManager1.createProject(projectName, String.valueOf(targetPath));
        if (project == null) return;
        projectManager.openProject(project);

        //ProjectManagerEx.getInstanceEx().loadProject(targetPath.toString());

         */
        ImportantResources.createProject("frc\\Command Robot");
    }


}
