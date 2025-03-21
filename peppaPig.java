import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

// Abstract class for Peppa's parts
abstract class PeppaPart extends JPanel {
    protected BufferedImage image;

    public PeppaPart(String filePath) {
        loadImage(filePath);
    }

    private void loadImage(String filePath) {
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void drawImage(Graphics g, int x, int y) {
        if (image != null) {
            g.drawImage(image, x, y, this);
        }
    }
}

// Head class with random color selection
class Head extends PeppaPart {
    private static final String[] COLORS = {"whiteFace.png", "yellowFace.png", "blueFace.png"};
    
    public Head() {
        super(getRandomColor()); // Set a random color upon initialization
    }

    private static String getRandomColor() {
        Random rand = new Random();
        return COLORS[rand.nextInt(COLORS.length)]; // Randomly pick one of the three colors
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawImage(g, 75, 30); // Draw head at (75,30)
    }
}

// Body class
class Body extends PeppaPart {
    public Body() {
        super("body.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawImage(g, 75, 30); // Draw body at (85,100)
    }
}

// Arms class
class Arms extends PeppaPart {
    public Arms() {
        super("arm.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawImage(g, 75, 30); // Draw left arm
    }
}

// Legs class
class Legs extends PeppaPart {
    public Legs() {
        super("leg.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawImage(g, 75, 30); // Left leg
    }
}

// Peppa Panel (combines all parts)
class PeppaPanel extends JPanel {
    private Head head;
    private Body body;
    private Arms arms;
    private Legs legs;

    public PeppaPanel() {
        setPreferredSize(new Dimension(300, 300));
        head = new Head(); // Randomly chooses a head color
        body = new Body();
        arms = new Arms();
        legs = new Legs();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        arms.paintComponent(g);
        legs.paintComponent(g);
        body.paintComponent(g);
        head.paintComponent(g);
    }
}

// Main GUI class
public class peppaPig {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Peppa Pig Builder");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(350, 350);

            PeppaPanel peppa = new PeppaPanel(); // Creates a new Peppa instance with a random head color

            frame.add(peppa, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}

