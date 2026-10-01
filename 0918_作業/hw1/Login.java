import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    public BadLogin() {
        setTitle("登入");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 視窗置中

        // 使用 3x2 的網格佈局，避免手動算座標
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel l1 = new JLabel("帳號:", SwingConstants.CENTER);
        JTextField t1 = new JTextField();

        JLabel l2 = new JLabel("密碼:", SwingConstants.CENTER);
        JPasswordField t2 = new JPasswordField(); // 密碼使用 JPasswordField

        JButton btn = new JButton("登入");

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(new JLabel()); // 佔位空元件
        add(btn);

        btn.addActionListener(e -> {
            String username = t1.getText();
            String password = new String(t2.getPassword());

            // 使用 equals 比對字串內容
            if ("admin".equals(username) && "1234".equals(password)) {
                System.out.println("登入成功");
                JOptionPane.showMessageDialog(this, "登入成功！");
            } else {
                System.out.println("帳號或密碼錯誤");
                JOptionPane.showMessageDialog(this, "帳號或密碼錯誤", "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 所有元件加入完成後再顯示視窗
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BadLogin());
    }
}