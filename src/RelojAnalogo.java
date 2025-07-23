import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.TimeZone;

public class RelojAnalogo extends JFrame {
    private int radio;
    private double diametro;
    private int hora;
    private int minuto;
    private int segundo;

    public RelojAnalogo() {
        super("RELOJ ANALOGO");
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        setSize(500, 500);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setBackground(Color.WHITE);
        setVisible(true);

        this.radio = 100;
        this.diametro = radio * 2;

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                hora = calendar.get(Calendar.HOUR_OF_DAY);
                minuto = calendar.get(Calendar.MINUTE);
                segundo = calendar.get(Calendar.SECOND);
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Dibujar círculo exterior
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(10.0f));
        g2.drawOval(centerX - radio - 10, centerY - radio - 10, (int) diametro + 20, (int) diametro + 20);

        // Marcas de minutos alrededor del círculo
        for (int i = 0; i < 60; i++) {
        double angulo = Math.toRadians(i * 6); 
        int r1 = (i % 5 == 0) ? radio - 5 : radio - 2; 
        int r2 = radio;
        int x1 = (int) (centerX + r1 * Math.cos(angulo - Math.PI / 2));
        int y1 = (int) (centerY + r1 * Math.sin(angulo - Math.PI / 2));
        int x2 = (int) (centerX + r2 * Math.cos(angulo - Math.PI / 2));
        int y2 = (int) (centerY + r2 * Math.sin(angulo - Math.PI / 2));

        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.GRAY);
        g2.drawLine(x1, y1, x2, y2);
            }

        // Números del reloj
        Font num = new Font("Spectral", Font.BOLD, 12);
        g2.setFont(num);
        g2.setColor(Color.BLACK);

        for (int i = 1; i <= 12; i++) {
            double angulo = Math.toRadians(90 - i * 30);
            int radioNumeros = radio - 20;
            int xNumero = (int) (centerX + radioNumeros * Math.cos(angulo)) - 8;
            int yNumero = (int) (centerY - radioNumeros * Math.sin(angulo)) + 5;
            g2.drawString(String.valueOf(i), xNumero, yNumero);
        }

        // Calcular ángulos
        int anguloHora = ((hora % 12) * 60 + minuto) * 360 / (12 * 60);
        int anguloMinuto = minuto * 360 / 60;
        int anguloSegundo = segundo * 360 / 60;

        // Dibujar manecilla de hora (negra)
        double radianHora = Math.toRadians(anguloHora - 90);
        int xHora = (int) (centerX + (radio - 40) * Math.cos(radianHora));
        int yHora = (int) (centerY + (radio - 40) * Math.sin(radianHora));
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(4.0f));
        g2.drawLine(centerX, centerY, xHora, yHora);

        // Manecilla de minuto (azul)
        double radianMinuto = Math.toRadians(anguloMinuto - 90);
        int xMinuto = (int) (centerX + (radio - 20) * Math.cos(radianMinuto));
        int yMinuto = (int) (centerY + (radio - 20) * Math.sin(radianMinuto));
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(3.0f));
        g2.drawLine(centerX, centerY, xMinuto, yMinuto);

        // Manecilla de segundo (roja)
        double radianSegundo = Math.toRadians(anguloSegundo - 90);
        int xSegundo = (int) (centerX + (radio - 10) * Math.cos(radianSegundo));
        int ySegundo = (int) (centerY + (radio - 10) * Math.sin(radianSegundo));
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(centerX, centerY, xSegundo, ySegundo);

        // Círculo en el centro
        g2.setColor(Color.BLACK);
        g2.fillOval(centerX - 5, centerY - 5, 10, 10);

        // Título superior
        String texto = "RELOJ ANALOGO";
        Font letra = new Font("Times New Roman", Font.BOLD, 34);
        g2.setFont(letra);
        FontMetrics metrics = g2.getFontMetrics(letra);
        int anchoTexto = metrics.stringWidth(texto);
        int xTexto = (getWidth() - anchoTexto) / 2;
        int yTexto = 80;
        g2.drawString(texto, xTexto, yTexto);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RelojAnalogo());
    }
}

