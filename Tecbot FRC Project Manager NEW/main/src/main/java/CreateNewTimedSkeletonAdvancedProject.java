import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class CreateNewTimedSkeletonAdvancedProject extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ImportantResources.createProject("frc\\Timed Skeleton Advanced");
    }
}
