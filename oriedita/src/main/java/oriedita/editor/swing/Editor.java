package oriedita.editor.swing;


import oriedita.editor.Canvas;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;

/**
 * BorderLayout for each different part.
 */
@Singleton
public class Editor {
    private JPanel root;
    private Canvas canvas;
    private RightPanel rightPanel;
    private BottomPanel bottomPanel;
    private TopPanel topPanel;
    private LeftPanel leftPanel;

    @Inject
    public Editor(Canvas canvas, RightPanel rightPanel, BottomPanel bottomPanel, TopPanel topPanel, LeftPanel leftPanel) {
        this.canvas = canvas;
        this.rightPanel = rightPanel;
        this.bottomPanel = bottomPanel;
        this.topPanel = topPanel;
        this.leftPanel = leftPanel;
        $$$setupUI$$$();
    }

    private void createUIComponents() {
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        root = new JPanel();
        root.setLayout(new BorderLayout(0, 0));
        root.add(bottomPanel.$$$getRootComponent$$$(), BorderLayout.SOUTH);
        root.add(rightPanel.$$$getRootComponent$$$(), BorderLayout.EAST);
        root.add(canvas, BorderLayout.CENTER);
        root.add(topPanel.$$$getRootComponent$$$(), BorderLayout.NORTH);
        root.add(leftPanel.$$$getRootComponent$$$(), BorderLayout.WEST);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }
}
