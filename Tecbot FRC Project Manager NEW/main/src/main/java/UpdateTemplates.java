import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class UpdateTemplates extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        ImportantResources.downloadRemoteRepository();
        String description = "Please choose the '.IntelliJIdea' folder. It can be found under C:\\Users\\{USER}\\";

        String stringPath = ImportantResources.getPathFolderChooser("Tecbot FRC Template Manager", description);

        //Messages.showWarningDialog(stringPath,"Tecbot");
        if(stringPath==null || !stringPath.contains("IntelliJ")){
            Messages.showErrorDialog("Path not found.","Tecbot FRC Template Manager");
            return;
        }
        Messages.showMessageDialog("Copying File Templates to " + stringPath, "FRC Template Manager", IconLoader.getIcon("META-INF/tecbotIcon.svg"));
        File targetPath = new File(stringPath+"\\config\\fileTemplates");
        File sourceFolder = new File(System.getProperty("user.home")+"\\TecbotFRC_Templates\\fileTemplates");
        try {
            ImportantResources.copyFolder(sourceFolder,targetPath);
            Messages.showInfoMessage("Files successfully copied. Please restart IntelliJIdea","Tecbot FRC Template Manager");
        } catch (IOException ex) {
            Messages.showErrorDialog("There has been an error during the copying of the File Templates. Please make sure that you have the TecbotFRC_Templates under "+System.getProperty("user.home"),ImportantResources.title);
            ex.printStackTrace();
        }
        //TIENE EL '/' NORMAL O SEA ESE
        //Messages.showInfoMessage(System.getProperty("os.name"),"fjaf");
    }
}
