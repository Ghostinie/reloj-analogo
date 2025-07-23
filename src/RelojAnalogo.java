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
        c.setBackground(Color.WHITE); // Fondo blanco
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

        // Línea Interior del círculo
        float[] dashPattern = {5, 5};
        g2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dashPattern, 0.0f));
        g2.setColor(Color.BLACK);
        g2.drawOval(centerX - radio, centerY - radio, (int) diametro, (int) diametro);

        // Línea Exterior del círculo
        g2.drawOval(centerX - radio, centerY - radio, (int) diametro, (int) diametro);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(10.0f));
        g2.drawOval(centerX - radio - 10, centerY - radio - 10, (int) diametro + 20, (int) diametro + 20);

        // Fuentes de numeros del reloj
        Font num = new Font("Spectral", Font.BOLD, 12);
        g2.setFont(num);

        // Colores
        Color numeroColor = Color.BLACK; // Color de los números
        Color manecillasColor = Color.RED; // Color de las manecillas

        // Dibujar los números alrededor del círculo del reloj
        for (int i = 1; i <= 12; i++) {
            double angulo = Math.toRadians(90 - i * 30);
            int radioNumeros = radio - 20;
            int xNumero = (int) (centerX + radioNumeros * Math.cos(angulo)) - 8;
            int yNumero = (int) (centerY - radioNumeros * Math.sin(angulo)) + 5;
            String numero = String.valueOf(i);
            g2.setColor(numeroColor); // Usar el color de los números
            g2.drawString(numero, xNumero, yNumero);
        }

        // Dibujar Manecillas
        int Hora = (hora % 12) * 360 / 12;
        int comprimentoHora = radio - 30;
        double radianosHora = Math.toRadians(Hora - 90);
        int xHora = (int) (centerX + comprimentoHora * Math.cos(radianosHora));
        int yHora = (int) (centerY + comprimentoHora * Math.sin(radianosHora));
        g2.setColor(manecillasColor); // Usar el color de las manecillas
        g2.setStroke(new BasicStroke(3.0f));
        g2.drawLine(centerX, centerY, xHora, yHora);

        int Minuto = minuto * 360 / 60;
        int comprimentoMinuto = radio - 20;
        double radianosMinuto = Math.toRadians(Minuto - 90);
        int xMinuto = (int) (centerX + comprimentoMinuto * Math.cos(radianosMinuto));
        int yMinuto = (int) (centerY + comprimentoMinuto * Math.sin(radianosMinuto));
        g2.setColor(manecillasColor); // Usar el color de las manecillas
        g2.setStroke(new BasicStroke(2.0f));
        g2.drawLine(centerX, centerY, xMinuto, yMinuto);

        int Segundo = segundo * 360 / 60;
        int comprimentoSegundo = radio - 10;
        double radianosSegundo = Math.toRadians(Segundo - 90);
        int xSegundo = (int) (centerX + comprimentoSegundo * Math.cos(radianosSegundo));
        int ySegundo = (int) (centerY + comprimentoSegundo * Math.sin(radianosSegundo));
        g2.setColor(manecillasColor); // Usar el color de las manecillas
        g2.setStroke(new BasicStroke(1.0f));
        g2.drawLine(centerX, centerY, xSegundo, ySegundo);

        // Escribir "Reloj Analogo" en la parte superior de la ventana
        String texto = "RELOJ  ANALOGO";
        g2.setColor(Color.BLACK); // Color del texto
        Font letra = new Font("Times New Roman", Font.BOLD, 34);
        g2.setFont(letra);
        FontMetrics metrics = g2.getFontMetrics(letra);
        int anchoTexto = metrics.stringWidth(texto);
        int xTexto = (getWidth() - anchoTexto) / 2; // Centrar horizontalmente
        int yTexto = 80; // Posición vertical en la parte superior
        g2.drawString(texto, xTexto, yTexto);
    }

    public static void main(String[] args) {
        RelojAnalogo rj = new RelojAnalogo();
    }
}
