/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj_krieg;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import studijosKTU.ScreenKTU;

public class KurskTablo extends studijosKTU.ScreenKTU {
    final static private int commandersCount = 12;
    String countryName;
    Commander[] commanders = new Commander[commandersCount];
    StringBuffer battleFlow;
    /*StringBuffer may have characters and substrings inserted in the middle or appended to the end.
    It will automatically grow to make room for such additions and often has more characters preallocated than are actually needed,
    to allow room for growth 
    */
    KurskTablo(String[] data){
//        super(20, 14, 20, 50, Fonts.plainB, Grid.OFF);
        super(18, 12, 20, 60, ScreenKTU.Fonts.boldA, ScreenKTU.Grid.OFF);
        setTitle("\u00a9 KTU IF \u00a9 Battle Of Kursk registration tablo"); //lango pavadinimas
        formMilitary(data);
        initiateTable();
        refresh();
    }
    //čia skaičiuohami taskai ir rašomos raidės
    int[] eventCount = new int[commandersCount];
    void eventRegistration(int cInd, int attack){
        Commander c1 = commanders[cInd];
        switch(attack){
            case -1: c1.l1++; break;
            case -2: c1.l2++; break;
            case -3: c1.l3++; break;
            case  1: c1.w1++; break;
            case  2: c1.w2++; break;
            case  3: c1.w3++; break;
        };
        setBackColor(attack>0? Color.green: Color.lightGray);
        setFontColor(attack>0? Color.white: Color.black);
        if(Math.abs(attack) == 1){
        print(4+cInd, 32+eventCount[cInd]++, 'S' );
        } 
        if(Math.abs(attack) == 2){
        print(4+cInd, 32+eventCount[cInd]++, 'B' );
        }
        if(Math.abs(attack) == 3){
        print(4+cInd, 32+eventCount[cInd]++, 'O' );
        }
        setColors(Color.pink, Color.yellow);
        print(4+cInd, 32+eventCount[cInd], Integer.toString(c1.victories()) ); 
    }
    // papildoma eilute 
    void updateBattleFlow(int commanderIndex, int attack){
        battleFlow.append("#"+commanders[commanderIndex].armyNr);
        if(attack>0) battleFlow.append('+');
        battleFlow.append(attack);
    }
    void formEventsBasedOnData(){
        Arrays.fill(eventCount, 0);
        for(Commander c1: commanders){
            c1.l1=0; c1.l2=0; c1.l3=0; c1.w1=0; c1.w2=0; c1.w3=0; 
        }
        for(int i=0; i<battleFlow.length();){
            int commanderNr = battleFlow.charAt(++i) - '0'; // tampi 4
            char e = battleFlow.charAt(++i);// tampa ženklu
            if(e>='0' && e<='9'){//jeigu skaičius
                commanderNr = commanderNr*10 + e-'0'; // kažkoks skaičius
                e = battleFlow.charAt(++i);
            }
            int attack = battleFlow.charAt(++i) - '0'; //skaičius po ženklo
            if(e == '-') attack = -attack; //nuima minusą
            int cIndex = 0; //commander index
            while(cIndex<commandersCount && commanders[cIndex].armyNr != commanderNr) cIndex++;
            if(cIndex == commandersCount){
                System.out.println(countryName + " not found in battleFlow #"+commanderNr);
                cIndex--;
            }
            eventRegistration(cIndex, attack);
            i++;
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int r = e.getY()/cellH, c = e.getX()/cellW;
        //System.out.println("row= " + r +"  col= " + c ); // pagalbinė info
        //print(r, c, Color.blue);            // skirta koordinatėms sužinoti
        if(r>=4 && r<4+commandersCount && c>=28 && c<=30){
            int victories = c-27;
            if(e.getButton() != 1) // jei 1 - tai sėkmingai laimėta
                victories = -victories;  // jei ne kairys mygtukas, tai pralaimėjimas
            eventRegistration(r-4, victories);
            updateBattleFlow(r-4, victories);
        } else if(r == screenH-1){ // veiksmai nurodomi apatinėje eilutėje
            if(c>=2 && c<=6){         // veiksmas CLEAR
                battleFlow.delete(0, battleFlow.length());
                initiateTable();
            } else if(c>=9 && c<=12)   // veiksmas SAVE
                System.out.println("\"" + battleFlow + "\"");
            else if(c>=15 && c<=21)    // veiksmas SUMMARY
                displayResults();
            else if(c>=24 && c<=27)    // veiksmas GAME
                initiateTable();
        }
        refresh();
    }
    void displayResults(){
        Arrays.sort(commanders, Commander.byVictories);
        setBackColor(Color.red);
      //  fillRect(4, 21, commandersCount, screenW-22);
        int eil = 4;
        for(Commander c: commanders){
            setFontColor(Color.white);
            print(eil,  1, String.format("%1s", c.armyCommander));
            print(eil, 25, String.format("%1d",  c.armyNr));
            setFontColor(Color.yellow);
            String s1 = (c.l1+c.w1)==0? "": String.format("%d/%d", c.w1, c.l1+c.w1);
            String s2 = (c.l2+c.w2)==0? "": String.format("%d/%d", c.w2, c.l2+c.w2);
            String s3 = (c.l3+c.w3)==0? "": String.format("%d/%d", c.w3, c.l3+c.w3);
            print(eil++, 32, String.format("%6s%6s%6s = %2d", 
                                            s1, s2, s3, c.victories()));
        }
    }
    final void formMilitary(String[] data){
//        int i = 1;
        countryName = data[0];
        for(int i=0; i<commandersCount; i++)
            commanders[i] = new Commander(data[i+1]);
        battleFlow = new StringBuffer(data[commandersCount+1]);
//        while(data[i].charAt(0)!= '#'){
//            commanders[i] = new Commander(data[i]);
//            i++;           
//        }
//        battleFlow = new StringBuffer(data[i]);
    }    
    final void initiateTable(){
        Arrays.sort(commanders);
        clearAll(Color.red);
        for(int i=0; i<commandersCount; i++){
//            setFontColor(Color.white);
//            print(i+4, 1, String.format("%1s%7d", commanders[i].armyCommander, commanders[i].armyNr));//row colona tekstas
//            setFontColor(Color.magenta);
//            print(i+4, 27, "SBO");
            setFontColor(Color.white);
            print(i+4, 1, String.format("%1s", commanders[i].armyCommander));//row colona tekstas
            print(i+4, 25, String.format("%1s", commanders[i].armyNr));
            setFontColor(Color.magenta);
            print(i+4, 28, "SBO");
        }
        setFontColor(Color.green);
        print(1, 22, countryName);
        setFontColor(Color.yellow);
        int lineType = 2;
        lineBorder(3,  0, 14, screenW, lineType);
        lineType = 1;
//        drawLine  (3, 13, 14, 0, lineType);
//        drawLine  (3, 16, 14, 0, lineType);
//        drawLine  (3, 20, 14, 0, lineType);
        drawLine  (3, 24, 14, 0, lineType); //vertikalė, vertikalių vieta horizontalėj
        drawLine  (3, 27, 14, 0, lineType);
        drawLine  (3, 31, 14, 0, lineType);
        //fillRect(4, 21, 12, screenW-22, Color.red);
        //fillRect(4, 21, 12, screenW-22, Color.red);
        setColors(Color.cyan, Color.magenta);
        print(screenH-1, 2, "CLEAR");
        print(screenH-1, 9, "SAVE");
        print(screenH-1, 15, "SUMMARY");
        print(screenH-1, 24, "GAME");
        formEventsBasedOnData();
    }

    public static void main(String[] args) {
        new KurskTablo(Data.data[0]);
        new KurskTablo(Data.data[1]);
    }
}