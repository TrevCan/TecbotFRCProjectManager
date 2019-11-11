import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class CreateNewTecbotProject extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ImportantResources.createProject("tecbot\\Tecbot2019");

    }
}
