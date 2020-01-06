package GUIpack.CEO;
import javax.swing.JFrame;

public class AddSquadFrame extends JFrame{
    private static final int DEFLAUT_WIDTH = 640,
            DEFLAUT_HEIGHT = 400;

    public AddSquadFrame(){
        this(DEFLAUT_WIDTH, DEFLAUT_HEIGHT, "Bez tytulu");
    }

    public AddSquadFrame(String title){
        this(DEFLAUT_WIDTH, DEFLAUT_HEIGHT, title);
    }

    public AddSquadFrame(final int frameWidth, final int frameHeight) {
        this(frameWidth, frameHeight, "Bez tytulu");
    }

    public AddSquadFrame(final int frameWidth, final int frameHeight, final String title) {
        final AddSquadPanel addIPanel = new AddSquadPanel(frameWidth, frameHeight);

        this.setTitle(title);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(addIPanel);
        this.pack();
    }
}
