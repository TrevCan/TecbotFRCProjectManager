import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class CreateNewSampleBasedProject extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ImportantResources.createProject("frc\\Sample Robot");
    }
}
