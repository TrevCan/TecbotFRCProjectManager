import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class ChangeTeamNumberWithProjectParameter extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ImportantResources.editJSON_byProjectString(ImportantResources.getPathFolderChooser(ImportantResources.title, "Please select your FRC Project path.", true));
    }
}
