import javax.swing.JOptionPane;
public class Game
{
    public static void main(String[] args)
    {
        int choice = Integer.parseInt(JOptionPane.showInputDialog("Which Game Mode?\n\n                        CPU + GUI = 1 \n                        CPU = 2 \n                        PvP = 3\n\n"));
        if(choice == 1)
        {
            guiAI.playerVScpu();
        }
        if(choice == 2)
        {
            trackingAI.playerVScpu();
        }
        if(choice == 3)
        {
            Game g = new Game();
            pVp.firstStep();
            JOptionPane.showMessageDialog(null, "Set Up Player One Board");
            pVp.setP1();
            JOptionPane.showMessageDialog(null, "Set Up Player Two Board");
            g.megaSpace();
            pVp.setP2();
            JOptionPane.showMessageDialog(null, "Ready?");
            g.megaSpace();
            while(true)
            {
                JOptionPane.showMessageDialog(null, "P1 Turn");
                pVp.turnP1();
                g.megaSpace();
                JOptionPane.showMessageDialog(null, "P2 Turn");
                pVp.turnP2();
                g.megaSpace();
            }
        }
    }
    public void megaSpace()
    {
        pVp p = new pVp();
        for(int i = 0; i<5; i++)
        {
            p.spacer();
        }
    }
}
