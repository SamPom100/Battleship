import javax.swing.JOptionPane;
public class pVp
{
    static int p1count = 0;
    static int p2count = 0;
    public static void firstStep()
    {
        BattleshipBoard board = new BattleshipBoard();
        board.writePlayerOne();
        board.writePlayerTwo();
        board.writePlayerOneguess();
        board.writePlayerTwoguess();
    }

    public static void setP1()
    {    
        BattleshipBoard b = new BattleshipBoard();
        pVp p = new pVp();
        trackingAI ai = new trackingAI();
        b.printPlayerBoard();
        b.importP1();   
        for(int i = 0; i<3; i++)
        {                 
            String playerin1 = p.askForInput();
            b.setPlayerpos("SHP",ai.conversion(playerin1.charAt(0)+""),Integer.parseInt(playerin1.charAt(1)+""));
        }
        b.writePlayerOne();
        b.printPlayerBoard();
    }

    public static void setP2()
    {    
        BattleshipBoard b = new BattleshipBoard();
        pVp p = new pVp();
        trackingAI ai = new trackingAI();
        b.printPlayerTwoBoard();
        b.importP2();   
        for(int i = 0; i<3; i++)
        {                 
            String playerin1 = p.askForInput();
            b.setPlayerTwopos("SHP",ai.conversion(playerin1.charAt(0)+""),Integer.parseInt(playerin1.charAt(1)+""));
        }
        b.writePlayerTwo();
        b.printPlayerTwoBoard();
    }

    public String askForInput()
    {
        String temp = JOptionPane.showInputDialog("Input Ship Position");
        return temp;
    }

    public static void turnP1()
    {
        System.out.println("Player ONE Turn");
        BattleshipBoard b = new BattleshipBoard();
        pVp p = new pVp();
        trackingAI ai = new trackingAI();
        b.importP1();
        b.importP2();
        b.importP1guess();
        p.spacer();
        b.printBothBoards();
        String playerin1 = p.askForInput();        
        if(b.checkPLATwo(ai.conversion(playerin1.charAt(0)+""),Integer.parseInt(playerin1.charAt(1)+"")))
        {
            JOptionPane.showMessageDialog(null, "P1 Hit a ship!");
            b.setGUESSpos("HIT",ai.conversion(playerin1.charAt(0)+""),Integer.parseInt(playerin1.charAt(1)+""));
            b.writePlayerOneguess();
            p1count++;
            if(p1count==3)
            {
                JOptionPane.showMessageDialog(null, "P1 Wins!");
                ai.preparetoexit(true);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "P1 Missed!");
            b.setGUESSpos("MIS",ai.conversion(playerin1.charAt(0)+""),Integer.parseInt(playerin1.charAt(1)+""));
            b.writePlayerOneguess();
        }
        p.spacer();
        b.printBothBoards();
    }
    
    public static void turnP2()
    {
        System.out.println("Player TWO Turn");
        BattleshipBoard b = new BattleshipBoard();
        pVp p = new pVp();
        trackingAI ai = new trackingAI();
        b.importP1();
        b.importP2();
        b.importP2guess();
        p.spacer();
        b.printBothBoardsTwo();
        String playerin1 = p.askForInput();        
        if(b.checkPLA(ai.conversion(playerin1.charAt(0)+""),Integer.parseInt(playerin1.charAt(1)+"")))
        {
            JOptionPane.showMessageDialog(null, "P2 Hit a ship!");
            b.setGUESSposTWO("HIT",ai.conversion(playerin1.charAt(0)+""),Integer.parseInt(playerin1.charAt(1)+""));
            b.writePlayerTwoguess();
            p2count++;
            if(p2count==3)
            {
                JOptionPane.showMessageDialog(null, "P2 Wins!");
                ai.preparetoexit(true);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "P2 Missed!");
            b.setGUESSposTWO("MIS",ai.conversion(playerin1.charAt(0)+""),Integer.parseInt(playerin1.charAt(1)+""));
            b.writePlayerTwoguess();
        }
        p.spacer();
        b.printBothBoardsTwo();
    }

    public void spacer()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n");
    }
}
