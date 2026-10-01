import javax.swing.*;
import java.awt.*;

public class DiceSimulator extends JFrame {

    public DiceSimulator() {
        // 視窗標題「骰子模擬器」，尺寸 400x320
        setTitle("骰子模擬器");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 上方統計標籤
        JLabel topLabel = new JLabel("已擲 0 次，總和 0，平均 0.00", SwingConstants.CENTER);
        topLabel.setPreferredSize(new Dimension(400, 40));
        add(topLabel, BorderLayout.NORTH);

        // 中間骰子面板（預設顯示 1 點紅色圓點）
        DicePanel dicePanel = new DicePanel(1);
        add(dicePanel, BorderLayout.CENTER);

        // 下方按鈕（純按鈕，不綁定功能）
        JButton rollButton = new JButton("擲骰子");
        rollButton.setPreferredSize(new Dimension(400, 45));
        add(rollButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // 自訂繪製骰子的面板
    static class DicePanel extends JPanel {
        private int dots;

        public DicePanel(int dots) {
            this.dots = dots;
            setBackground(new Color(240, 240, 240));
        }

        public void setDots(int dots) {
            this.dots = dots;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            // 開啟反鋸齒，讓邊角和圓點更平滑
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int size = 120; // 骰子方塊尺寸
            int x = (getWidth() - size) / 2;
            int y = (getHeight() - size) / 2;

            // 繪製微立體陰影
            g2.setColor(new Color(200, 200, 200));
            g2.fillRoundRect(x + 4, y + 4, size, size, 28, 28);

            // 繪製骰子本體（白底圓角方塊）
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(x, y, size, size, 28, 28);

            // 繪製骰子外邊框
            g2.setColor(new Color(180, 180, 180));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x, y, size, size, 28, 28);

            // 點數圓點直徑
            int dotSize = (dots == 1) ? 26 : 18;
            
            // 點數 1 為紅色，其他點數為黑色
            g2.setColor(dots == 1 ? new Color(210, 40, 40) : new Color(30, 30, 30));

            // 計算九宮格圓點座標位置
            int left = x + 25;
            int cx = x + size / 2 - dotSize / 2;
            int right = x + size - 25 - dotSize;

            int top = y + 25;
            int cy = y + size / 2 - dotSize / 2;
            int bottom = y + size - 25 - dotSize;

            // 依照點數決定各圓點繪製位置
            if (dots == 1 || dots == 3 || dots == 5) {
                g2.fillOval(cx, cy, dotSize, dotSize); // 中心點
            }
            if (dots >= 2) {
                g2.fillOval(left, top, dotSize, dotSize);       // 左上
                g2.fillOval(right, bottom, dotSize, dotSize);  // 右下
            }
            if (dots >= 4) {
                g2.fillOval(right, top, dotSize, dotSize);     // 右上
                g2.fillOval(left, bottom, dotSize, dotSize);   // 左下
            }
            if (dots == 6) {
                g2.fillOval(left, cy, dotSize, dotSize);       // 左中
                g2.fillOval(right, cy, dotSize, dotSize);      // 右中
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DiceSimulator());
    }
}