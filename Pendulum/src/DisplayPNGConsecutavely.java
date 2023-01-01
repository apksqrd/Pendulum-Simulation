import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DisplayPNGConsecutavely {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel image = new JLabel();
        image.setSize(540, 540);
        panel.setSize(540, 540);
        frame.setSize(540, 540);

        panel.add(image);
        frame.add(panel);
        frame.setVisible(true);

        for (int index = 0; index < 991; index++) {
            image.setIcon(
                    new ImageIcon((new ImageIcon("1000pendulums/" + index + ".PNG")).getImage().getScaledInstance(540,
                            540, Image.SCALE_DEFAULT)));
            Thread.sleep(1000 / 60);
        }
    }
}
