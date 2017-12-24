package views.frames;

import views.frames.launch.LaunchFrame;

/**
 * Created by Валера on 31.07.2017.
 *
 * Сюжетный фрейм - окно с ссылкой на лаунчер
 * для возможности перехода к окну запуска
 *
 */
public abstract class PlotFrame extends AppFrame {
    protected LaunchFrame launchFrame;

    protected abstract void init();

    public LaunchFrame getLaunchFrame() {
        return launchFrame;
    }
}
