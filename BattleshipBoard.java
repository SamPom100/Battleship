import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
public class BattleshipBoard
{
    static String[][] playerBoard = new String[6][6];
    static String[][] playerTwoBoard = new String[6][6];
    static String[][] cpuBoard = new String[6][6];
    static String[][] guessBoard = new String[6][6];
    static String[][] guessBoardTWO = new String[6][6];
    public BattleshipBoard()
    {
        for(int x = 0; x<cpuBoard.length; x++)
        {
            for(int y = 0; y<cpuBoard[x].length; y++)
            {
                cpuBoard[x][y] = "XXX";
                playerBoard[x][y] = "XXX";
                playerTwoBoard[x][y] = "XXX";
                guessBoard[x][y] = "---";
                guessBoardTWO[x][y] = "---";
            }
        }
    }

    public void printPlayerBoard()
    {
        System.out.print("    A"+"    B"+"    C"+"    D"+"    E"+"    F");
        System.out.println("\n");
        int count = 0;
        boolean t = false;
        for(int x = 0; x<cpuBoard.length; x++)
        {
            for(int y = 0; y<cpuBoard[x].length; y++)
            {
                if(t == false){System.out.print(count+"  "); t = true;}
                System.out.print(playerBoard[x][y] + "  ");
            }
            System.out.println("\n");
            count++;
            t = false;
        }
    }
    
    public void printPlayerTwoBoard()
    {
        System.out.print("    A"+"    B"+"    C"+"    D"+"    E"+"    F");
        System.out.println("\n");
        int count = 0;
        boolean t = false;
        for(int x = 0; x<cpuBoard.length; x++)
        {
            for(int y = 0; y<cpuBoard[x].length; y++)
            {
                if(t == false){System.out.print(count+"  "); t = true;}
                System.out.print(playerTwoBoard[x][y] + "  ");
            }
            System.out.println("\n");
            count++;
            t = false;
        }
    }    

    public void printCPUBoard()
    {
        System.out.print("    A"+"    B"+"    C"+"    D"+"    E"+"    F");
        System.out.println("\n");
        int count = 0;
        boolean t = false;
        for(int x = 0; x<cpuBoard.length; x++)
        {
            for(int y = 0; y<cpuBoard[x].length; y++)
            {
                if(t == false){System.out.print(count+"  "); t = true;}
                System.out.print(cpuBoard[x][y] + "  ");
            }
            System.out.println("\n");
            count++;
            t = false;
        }
    }

    public void printGUESSBoard()
    {
        System.out.print("    A"+"    B"+"    C"+"    D"+"    E"+"    F");
        System.out.println("\n");
        int count = 0;
        boolean t = false;
        for(int x = 0; x<guessBoard.length; x++)
        {
            for(int y = 0; y<guessBoard[x].length; y++)
            {
                if(t == false){System.out.print(count+"  "); t = true;}
                System.out.print(guessBoard[x][y] + "  ");
            }
            System.out.println("\n");
            count++;
            t = false;
        }
    }
    
    public void printGUESSBoardtwo()
    {
        System.out.print("    A"+"    B"+"    C"+"    D"+"    E"+"    F");
        System.out.println("\n");
        int count = 0;
        boolean t = false;
        for(int x = 0; x<guessBoardTWO.length; x++)
        {
            for(int y = 0; y<guessBoardTWO[x].length; y++)
            {
                if(t == false){System.out.print(count+"  "); t = true;}
                System.out.print(guessBoardTWO[x][y] + "  ");
            }
            System.out.println("\n");
            count++;
            t = false;
        }
    }

    public void printBothBoards()
    {
        System.out.println("Player Board:\n");
        printPlayerBoard();
        System.out.println("\n");
        System.out.println("Guess Board:\n");
        printGUESSBoard();
    }
    
    public void printBothBoardsTwo()
    {
        System.out.println("Player Board:\n");
        printPlayerTwoBoard();
        System.out.println("\n");
        System.out.println("Guess Board:\n");
        printGUESSBoardtwo();
    }
    
    public boolean checkCPU(int x, int y)
    {
        if(cpuBoard[y][x].equals("SHP"))
        {
            return true;
        }
        return false;
    }   

    public boolean checkPLA(int x, int y)
    {
        if(playerBoard[y][x].equals("SHP"))
        {
            return true;
        }
        return false;
    }

    public boolean checkPLATwo(int x, int y)
    {
        if(playerTwoBoard[y][x].equals("SHP"))
        {
            return true;
        }
        return false;
    }
    
    public void setCPUpos(String input, int x, int y)
    {
        cpuBoard[y][x] = input;
    }

    public void setPlayerpos(String input, int x, int y)
    {
        playerBoard[y][x] = input;
    }

    public void setPlayerTwopos(String input, int x, int y)
    {
        playerTwoBoard[y][x] = input;
    }
    
    public void setGUESSpos(String input, int x, int y)
    {
        guessBoard[y][x] = input;
    }

    public void setGUESSposTWO(String input, int x, int y)
    {
        guessBoardTWO[y][x] = input;
    }
    
    public void writePlayerOne()
    {
        try{
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter("playerONE.txt"));
            outputWriter.newLine();
            for(int x = 0; x<cpuBoard.length; x++)
            {
                for(int y = 0; y<cpuBoard[x].length; y++)
                {
                    outputWriter.write(playerBoard[x][y]);
                }
                outputWriter.newLine();
                outputWriter.newLine();
                outputWriter.newLine();
            }
            outputWriter.flush();  
            outputWriter.close(); 
        }
        catch(Exception e){}
    }
    
    public void writePlayerOneguess()
    {
        try{
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter("playerONEguess.txt"));
            outputWriter.newLine();
            for(int x = 0; x<cpuBoard.length; x++)
            {
                for(int y = 0; y<cpuBoard[x].length; y++)
                {
                    outputWriter.write(guessBoard[x][y]);
                }
                outputWriter.newLine();
                outputWriter.newLine();
                outputWriter.newLine();
            }
            outputWriter.flush();  
            outputWriter.close(); 
        }
        catch(Exception e){}
    }

    public void writePlayerTwo()
    {
        try{
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter("playerTWO.txt"));
            outputWriter.newLine();
            for(int x = 0; x<cpuBoard.length; x++)
            {
                for(int y = 0; y<cpuBoard[x].length; y++)
                {
                    outputWriter.write(playerTwoBoard[x][y]);
                }
                outputWriter.newLine();
                outputWriter.newLine();
                outputWriter.newLine();
            }
            outputWriter.flush();  
            outputWriter.close(); 
        }
        catch(Exception e){}
    }
    
    public void writePlayerTwoguess()
    {
        try{
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter("playerTWOguess.txt"));
            outputWriter.newLine();
            for(int x = 0; x<cpuBoard.length; x++)
            {
                for(int y = 0; y<cpuBoard[x].length; y++)
                {
                    outputWriter.write(guessBoardTWO[x][y]);
                }
                outputWriter.newLine();
                outputWriter.newLine();
                outputWriter.newLine();
            }
            outputWriter.flush();  
            outputWriter.close(); 
        }
        catch(Exception e){}
    }

    public String readPlayerOne()
    {
        BufferedReader br = null;
        String contentLine;
        String fullText = "";
        try
        { 
            br = new BufferedReader(new FileReader("playerONE.txt"));
            contentLine = br.readLine();
            while (contentLine != null)
            { 
                contentLine = br.readLine();
                fullText = fullText + contentLine;                    
            }
        } 
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        } 
        return fullText.substring(0,fullText.length()-4);
    }   
    
    public String readPlayerOneguess()
    {
        BufferedReader br = null;
        String contentLine;
        String fullText = "";
        try
        { 
            br = new BufferedReader(new FileReader("playerONEguess.txt"));
            contentLine = br.readLine();
            while (contentLine != null)
            { 
                contentLine = br.readLine();
                fullText = fullText + contentLine;                    
            }
        } 
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        } 
        return fullText.substring(0,fullText.length()-4);
    } 

    public String readPlayerTwo()
    {
        BufferedReader br = null;
        String contentLine;
        String fullText = "";
        try
        { 
            br = new BufferedReader(new FileReader("playerTWO.txt"));
            contentLine = br.readLine();
            while (contentLine != null)
            { 
                contentLine = br.readLine();
                fullText = fullText + contentLine;                    
            }
        } 
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        } 
        return fullText.substring(0,fullText.length()-4);
    }
    
    public String readPlayerTwoguess()
    {
        BufferedReader br = null;
        String contentLine;
        String fullText = "";
        try
        { 
            br = new BufferedReader(new FileReader("playerTWOguess.txt"));
            contentLine = br.readLine();
            while (contentLine != null)
            { 
                contentLine = br.readLine();
                fullText = fullText + contentLine;                    
            }
        } 
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        } 
        return fullText.substring(0,fullText.length()-4);
    }

    public void importP1()
    {
        String[][] tempStore = new String[6][6];
        String assembled = readPlayerOne();
        int count = 0;
        for(int x = 0; x<cpuBoard.length; x++)
        {
            for(int y = 0; y<cpuBoard[x].length; y++)
            {
                tempStore[x][y] = assembled.charAt(count)+""+assembled.charAt(count+1)+""+assembled.charAt(count+2)+"";
                count=count+3;
            }
        }
        playerBoard = tempStore;
    }
    
    public void importP1guess()
    {
        String[][] tempStore = new String[6][6];
        String assembled = readPlayerOneguess();
        int count = 0;
        for(int x = 0; x<cpuBoard.length; x++)
        {
            for(int y = 0; y<cpuBoard[x].length; y++)
            {
                tempStore[x][y] = assembled.charAt(count)+""+assembled.charAt(count+1)+""+assembled.charAt(count+2)+"";
                count=count+3;
            }
        }
        guessBoard = tempStore;
    }

    public void importP2()
    {
        String[][] tempStore = new String[6][6];
        String assembled = readPlayerTwo();
        int count = 0;
        for(int x = 0; x<cpuBoard.length; x++)
        {
            for(int y = 0; y<cpuBoard[x].length; y++)
            {
                tempStore[x][y] = assembled.charAt(count)+""+assembled.charAt(count+1)+""+assembled.charAt(count+2)+"";
                count=count+3;
            }
        }
        playerTwoBoard = tempStore;
    }
    
    public void importP2guess()
    {
        String[][] tempStore = new String[6][6];
        String assembled = readPlayerTwoguess();
        int count = 0;
        for(int x = 0; x<cpuBoard.length; x++)
        {
            for(int y = 0; y<cpuBoard[x].length; y++)
            {
                tempStore[x][y] = assembled.charAt(count)+""+assembled.charAt(count+1)+""+assembled.charAt(count+2)+"";
                count=count+3;
            }
        }
        guessBoardTWO = tempStore;
    }
}
