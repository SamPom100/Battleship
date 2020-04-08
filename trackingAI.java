import javax.swing.JOptionPane;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
public class trackingAI
{    
    static ArrayList<String> hitlist = new ArrayList<String>();
    public static void playerVScpu()
    {
        boolean AIhit = false;
        int aiHitcount = 0;
        trackingAI ai = new trackingAI();
        BattleshipBoard board = new BattleshipBoard();
        board.printPlayerBoard();             
        boolean pass1 = true;
        boolean pass2 = true;
        boolean pass3 = true;
        String playerin1 = "";
        String playerin2 = "";
        String playerin3 = "";
        boolean grandpass = true;
        while(grandpass)
        {
            try
            {
                while(pass1)
                {
                    playerin1 = JOptionPane.showInputDialog("Input Position One");
                    board.setPlayerpos("SHP",ai.conversion(playerin1.charAt(0)+""),Integer.parseInt(playerin1.charAt(1)+""));
                    pass1 = false;
                }
            }catch(Exception e){}
            try
            {
                while(pass2)
                {
                    playerin2 = JOptionPane.showInputDialog("Input Position Two");
                    board.setPlayerpos("SHP",ai.conversion(playerin2.charAt(0)+""),Integer.parseInt(playerin2.charAt(1)+""));
                    pass2 = false;
                }
            }catch(Exception e){}
            try
            {
                while(pass3)
                {
                    playerin3 = JOptionPane.showInputDialog("Input Position Three");
                    board.setPlayerpos("SHP",ai.conversion(playerin3.charAt(0)+""),Integer.parseInt(playerin3.charAt(1)+""));
                    pass3 = false;
                }
            }catch(Exception e){}
            boolean con = false;
            try{
                if(playerin1.charAt(0) == playerin2.charAt(0) && playerin2.charAt(0) == playerin3.charAt(0)){con = true;} //all in the same column
                if(Integer.parseInt(playerin1.charAt(1)+"") == Integer.parseInt(playerin2.charAt(1)+"") && Integer.parseInt(playerin2.charAt(1)+"") == Integer.parseInt(playerin3.charAt(1)+"")){con = true;}}
            catch(Exception e){}
            if(con == false)
            {
                JOptionPane.showInputDialog("Input Correct Position Please");
            }
            else
            {
                grandpass = false;
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        Random rando = new Random();
        int coinflip = rando.nextInt(2); //either 1 or 0
        if(coinflip == 0) //vertical ship
        {
            int x = rando.nextInt(4); //from 0 - 3
            int y = rando.nextInt(6); //from 0 - 5
            board.setCPUpos("SHP",x,y);
            board.setCPUpos("SHP",x+1,y);
            board.setCPUpos("SHP",x+2,y);
        }
        else //horizontal ship
        {
            int x = rando.nextInt(6); //from 0 - 5
            int y = rando.nextInt(4); //from 0 - 3
            board.setCPUpos("SHP",x,y);
            board.setCPUpos("SHP",x,y+1);
            board.setCPUpos("SHP",x,y+2);
        }        
        //board.printCPUBoard(); for developing :)
        boolean inprogress = true;
        int hitcount = 0;
        board.printBothBoards();
        ///////////////////////////////////////////// player turn /////////////////////////////////////////////////////    
        while(inprogress)
        {           
            boolean goodGuess = true;
            while(goodGuess)
            {
                String playerGuess = JOptionPane.showInputDialog("Input Guess");
                try{
                    if(board.checkCPU(ai.conversion(playerGuess.charAt(0)+""),Integer.parseInt(playerGuess.charAt(1)+"")))
                    {
                        board.setCPUpos("HIT",ai.conversion(playerGuess.charAt(0)+""),Integer.parseInt(playerGuess.charAt(1)+""));
                        JOptionPane.showMessageDialog(null, "HIT!");
                        board.setGUESSpos("HIT",ai.conversion(playerGuess.charAt(0)+""),Integer.parseInt(playerGuess.charAt(1)+""));
                        hitcount++;
                        goodGuess = false;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "MISS!");
                        board.setGUESSpos("MIS",ai.conversion(playerGuess.charAt(0)+""),Integer.parseInt(playerGuess.charAt(1)+""));
                        goodGuess = false;
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Input a correct guess please");
                }
            }
            if(hitcount == 3)
            {
                inprogress = false;
                ai.preparetoexit(true);
            }  

            /////////////////////////////////////////// AI Turn ///////////////////////////////////////////////////
            int[][] cpuTurn;
            ai.putArrayInOrder();
            if(AIhit) //ai hit a player ship
            {               
                int[][] predict = ai.inteliTURN();
                JOptionPane.showMessageDialog(null, "CPU InteliGuessed: "+predict[0][0]+"-"+predict[0][1]);
                if(board.checkPLA(predict[0][0],predict[0][1]))
                {
                    JOptionPane.showMessageDialog(null, "IntelliGuess got you!");
                    hitlist.add(predict[0][0]+""+predict[0][1]);
                    board.setPlayerpos("IHT",predict[0][0],predict[0][1]); 
                    aiHitcount++;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "IntelliGuess missed :*(");
                    board.setPlayerpos("IMS",predict[0][0],predict[0][1]);
                }
            }
            else
            {
                cpuTurn = ai.getCPUturn();
                if(board.checkPLA(cpuTurn[0][0],cpuTurn[0][1]))
                {
                    
                    JOptionPane.showMessageDialog(null, "CPU Guessed: "+cpuTurn[0][0]+"-"+cpuTurn[0][1]);
                    JOptionPane.showMessageDialog(null, "You Were Hit!");
                    board.setPlayerpos("HIT",cpuTurn[0][0],cpuTurn[0][1]);   
                    hitlist.add(cpuTurn[0][0]+""+cpuTurn[0][1]);
                    AIhit = true;
                    aiHitcount++;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "CPU Guessed: "+cpuTurn[0][0]+"-"+cpuTurn[0][1]);
                    board.setPlayerpos("MIS",cpuTurn[0][0],cpuTurn[0][1]);
                }
            }
            if(aiHitcount == 3)
            {
                inprogress = false;
                ai.preparetoexit(false);
            }
            board.printBothBoards();
        }
    }

    ///////////////////////////////////////// Conversion ////////////////////////////////////////
    public int conversion(String in)
    {
        in = in.toUpperCase();
        if(in.equals("A"))
        {
            return 0;
        }
        if(in.equals("B"))
        {
            return 1;
        }
        if(in.equals("C"))
        {
            return 2;
        }
        if(in.equals("D"))
        {
            return 3;
        }
        if(in.equals("E"))
        {
            return 4;
        }
        if(in.equals("F"))
        {
            return 5;
        }
        return -1;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////// random AI guessing + storage //////////////////////////////////////////////
    static ArrayList<String> storage = new ArrayList<String>();

    public int[][] genNum()
    {
        Random r = new Random(); 
        int[][] temp = new int[2][2];
        temp[0][0] = r.nextInt(6);
        temp[0][1] = r.nextInt(6);
        return temp;
    }

    public boolean checkMatch(int x, int y)
    {
        boolean pass = true;
        for(int i = 0; i<storage.size(); i++)
        {
            if(storage.size() > 0){
                int storeX = Integer.parseInt(storage.get(i).charAt(0)+"");
                int storeY = Integer.parseInt(storage.get(i).charAt(1)+"");
                if(x == storeX && y == storeY)
                {                    
                    pass = false;
                }
            }
        }
        if(pass)
        {            
            return false;
        }
        return true;
    }

    public int[][] getCPUturn()
    {
        int[][] toReturn = new int[2][2];
        boolean conT = true;
        while(conT)
        {
            int[][] arr = genNum();
            if(!checkMatch(arr[0][0],arr[0][1]))
            {
                toReturn[0][0] = arr[0][0];
                toReturn[0][1] = arr[0][1];
                storage.add(arr[0][0]+""+arr[0][1]);
                return toReturn;
            }           
        }
        return toReturn;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// AI Intelligent Boat Tracking //////////////////////////////////////////    
    public int[][] inteliTURN()
    {
        int[][] toReturn = new int[2][2];
        boolean conT = true;
        while(conT)
        {
            int[][] arr = predictshippos();
            if(!checkMatch(arr[0][0],arr[0][1]))
            {
                toReturn[0][0] = arr[0][0];
                toReturn[0][1] = arr[0][1];
                storage.add(arr[0][0]+""+arr[0][1]); //for some reason checkMatch won't add an intelligent guess to storage :(
                return toReturn;
            }           
        }
        return toReturn;
    }                

    public int[][] predictshippos()
    {
        String firstPos = hitlist.get(0);
        String secondPos = ""; boolean hasSecond = false;
        try
        {
            secondPos = hitlist.get(1);
            hasSecond = true;
        }
        catch(Exception e){}
        int x1 = Integer.parseInt(firstPos.charAt(0)+"");
        int y1 = Integer.parseInt(firstPos.charAt(1)+"");
        int x2 = -1;
        int y2 = -1;
        if(hasSecond)
        {
            x2 = Integer.parseInt(secondPos.charAt(0)+"");
            y2 = Integer.parseInt(secondPos.charAt(1)+"");
        }
        boolean dontpass = true;        
        int guessX = 0;
        int guessY = 0;
        int compass = -1;
        if(!hasSecond)
        {
            while(dontpass)
            {
                guessX = x1;
                guessY = y1;
                compass = getCompass(); //either 0,1,2,3 = up, down, right, left
                if(compass == 0) //up
                {
                    guessY = y1+1;
                }
                if(compass == 1) //down
                {
                    guessY = y1-1;
                }
                if(compass == 2) //left
                {
                    guessX = x1-1;
                }
                if(compass == 3) //right
                {
                    guessX = x1+1;
                }
                if(guessY < 6 && guessX < 6)
                {
                    if(guessY >= 0 && guessX >= 0)
                    {
                        dontpass = false;
                    }        
                }
            }
        }
        if(hasSecond)
        {
            guessX = x1;
            guessY = y1;
            boolean vertical = false;
            boolean horizontal = false;
            if(x1+1 == x2 || x2+1 == x1)
            {
                horizontal = true;
            }
            if(y1+1 == y2 || y2+1 == y1)
            {
                vertical = true;
            }
            boolean secondpass = true;
            while(secondpass)
            {
                int coinflip = get50();
                if(horizontal)
                {
                    if(coinflip == 0) //guess left
                    {
                        guessX = x1-1;
                    }
                    else //guess right
                    {
                        guessX = x2+1;
                    }
                }
                else
                {
                    if(coinflip == 0) //guess up
                    {
                        guessY = y1-1;
                    }
                    else //guess down
                    {
                        guessY = y2+1;
                    }
                }
                if(guessY < 6 && guessX < 6)
                {
                    if(guessY >= 0 && guessX >= 0)
                    {
                        secondpass = false;
                    }        
                }
            }
        }
        int[][] toReturn = new int[2][2];
        toReturn[0][0] = guessX;
        toReturn[0][1] = guessY;
        return toReturn;
    }

    public int getCompass()
    {
        Random rando = new Random();
        return rando.nextInt(4);
    }

    public int get50()
    {
        Random rando = new Random();
        return rando.nextInt(2);
    }

    public void putArrayInOrder()
    {
        ArrayList<Integer> toSort = new ArrayList<Integer>();
        ArrayList<String> sorted = new ArrayList<String>();
        for(int i = 0; i<hitlist.size(); i++)
        {
            int temp = Integer.parseInt(hitlist.get(i));
            toSort.add(temp);
        }
        Collections.sort(toSort);
        for(int i = 0; i<hitlist.size(); i++)
        {
            String insert = toSort.get(i)+"";
            try
            {
                char b = insert.charAt(1); //does it have a second digit
            }
            catch(Exception e)
            {
                insert = "0"+insert;
            }
            sorted.add(insert);
        }
        hitlist = sorted;
    }

    public void winART()
    {
        System.out.println("              |    |    |                 ");System.out.println("             )_)  )_)  )_)              ");System.out.println("            )___))___))___)\\            ");                
        System.out.println("           )____)____)_____)\\");System.out.println("         _____|____|____|____\\\\__");System.out.println("---------\\                 //---------");               
        System.out.println("      ^^^^^ ^^^^^^^^^^^^^^^^^^^^^");System.out.println("    ^^^^      ^^^^     ^^^    ^^");System.out.println("^^^     ^^^^      ^^^     ^^^^      ^^^");System.out.println("                YOU  WIN           ");                        
    }

    public void loseART()
    {
        System.out.println("              _.-^^---....,,---_");System.out.println("           _--                  --_");System.out.println("          <         YOU LOSE       >)");                
        System.out.println("           \\._                 _.//");System.out.println("              ```--. . , ; .--'''");System.out.println("                    | |   |");              
        System.out.println("                 .-=||  | |=-."); System.out.println("                 `-=#$%&%$#=-'");System.out.println("                    | ;  :|");System.out.println("           _____.,-#%&$@%#&#~,._____");               
    }

    public void preparetoexit(boolean win)
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        if(win){winART();}
        else{loseART();}
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("Preparing to Exit");
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("Exit in 3...");
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("Exit in 2...");
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("Exit in 1...");
        System.exit(0);
    }
}

