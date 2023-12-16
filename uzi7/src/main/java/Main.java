import view.MainFrame;

/**
 * @author Hsu琛君珩
 * @ClassName:Main
 * @date 2023-12-16
 * @apiNote
 * @Version: v1.0
 */

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        // 界面加载完后，加载数据
        MainFrame.init();
        System.out.println();
    }
}
