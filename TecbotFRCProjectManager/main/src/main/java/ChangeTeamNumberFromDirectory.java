import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class ChangeTeamNumberFromDirectory extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        String sourceFolderWith_wpilibDirectory = ImportantResources.getPathFolderChooser(ImportantResources.title, "Please select the FRC Project directory.", false);
        ImportantResources.editTeamNumber(sourceFolderWith_wpilibDirectory);
    }
}
