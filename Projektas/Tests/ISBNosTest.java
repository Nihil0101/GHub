import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

//Test1 - tests following methods: String IntArray, int convertToInt,
// int[] BrokenApartLtoR, int Sum, int Remainder, boolean CheckSum, String Last, toString();

//Test2 - tests following methods: String IntArray, int convertToInt,
// int[] BrokenApartRtoL, int RecursiveSum, int Remainder, boolean CheckSum, String Last, toString();

//Test3 - tests following methods: String IntArray, double formDouble,
// int[] BrokenApartD, int AlternativeSum, int AlternativeRemainder, boolean CheckSum, String Last, toString();

//Test4 - tests following methods: String formString, int [] FormIntArray
//int Sum, int Remainder, boolean CheckSum, String Last, toString();

public class ISBNosTest {

    @Test
    public void Test1() {
        System.out.println("Test1-----------------------------------------");
        for(int i = 0; i < 11; i++) {
            ISBNos isbnos = new ISBNos();
            String step1 = isbnos.IntArray();
            int step2 = isbnos.convertToInt(step1);
            boolean check1 = isbnos.Changed(step1, step2);
            int[] step3 = isbnos.BrokenApartLtoR(step2);
            int step4 = isbnos.Sum(step3);
            int step5 = isbnos.Remainder(step4);
            boolean step6 = isbnos.CheckSum(step5);
            String step7 = isbnos.last(step5, step6);
            ISBNos result;
            String check2 = isbnos.First(check1,step2);
            if(check1 == false){
            result = new ISBNos(step1, step7);
            }
            else {
                result = new ISBNos(check2, step1, step7);
            }
            String step8 = result.showISBN();
            boolean step9 = result.isISBN(step8);
            System.out.print(result.toString());
            System.out.println(result.toString().length());
            System.out.println(step9);

//            Assert.assertSame(step1.length(),9);
//            Assert.assertTrue(step2<(int)Math.pow(10,9) && step2 >99999999);
//            //Assert.assertTrue(check1);
//            Assert.assertSame(step3.length,9);
//            Assert.assertTrue(step4>0);
//            Assert.assertTrue(step5>=0);
//            Assert.assertSame(step7.length(),3);
//            Assert.assertSame(step8.length(),12);
//            Assert.assertTrue(step9 == true);


        }
    }
    @Test
    public void Test2(){
        System.out.println("Test2-----------------------------------------");
        for(int i = 0; i < 11; i++) {
            ISBNos isbnos = new ISBNos();
            String step1 = isbnos.IntArray();
            int step2 = isbnos.convertToInt(step1);
            boolean check1 = isbnos.Changed(step1, step2);
            int[] step3 = isbnos.BrokenApartRtoL(step2);
            int step4 = isbnos.RecursiveSum(step3,0,0);
            int step5 = isbnos.Remainder(step4);
            boolean step6 = isbnos.CheckSum(step5);
            String step7 = isbnos.last(step5, step6);
            ISBNos result;
            String check2 = isbnos.First(check1,step2);
            if(check1 == false){
                result = new ISBNos(step1, step7);
            }
            else {
                result = new ISBNos(check2, step1, step7);
            }
            String step8 = result.showISBN();
            boolean step9 = result.isISBN(step8);
            System.out.print(result.toString());
            System.out.println(result.toString().length());
            System.out.println(step9);

//            Assert.assertSame(step1.length(),9);
//            Assert.assertTrue(step2<(int)Math.pow(10,9) && step2 >99999999);
//            //Assert.assertTrue(check1);
//            Assert.assertSame(step3.length,9);
//            Assert.assertTrue(step4>0);
//            Assert.assertTrue(step5>=0);
//            Assert.assertSame(step7.length(),3);
//            Assert.assertSame(step8.length(),12);
//            Assert.assertTrue(step9 == true);
        }
    }
    @Test
    public void Test3(){
        System.out.println("Test3-----------------------------------------");
        for(int i = 0; i < 11; i++) {
            ISBNos isbnos = new ISBNos();
            String step1 = isbnos.IntArray();
            double step2 = isbnos.FormDouble(step1);
            int[] step3 = isbnos.BrokenApartD(step2);
            int step4 = isbnos.AlternativeSum(step3);
            int step5 = isbnos.AlternativeRemainder(step4);
            boolean step6 = isbnos.CheckSum(step5);
            String step7 = isbnos.last(step5, step6);
            ISBNos result = new ISBNos(step1, step7);
            String step8 = result.showISBN();
            boolean step9 = result.isISBN(step8);
            System.out.print(result.toString());
            System.out.println(result.toString().length());
            System.out.println(step9);


//            Assert.assertSame(step1.length(),9);
//            Assert.assertTrue(step2<8001 && step2>-1);
//            //Assert.assertTrue(check1);
//            Assert.assertSame(step3.length,9);
//            Assert.assertTrue(step4>0);
//            Assert.assertTrue(step5>=0);
//            Assert.assertSame(step7.length(),3);
//            Assert.assertSame(step8.length(),12);
//            Assert.assertTrue(step9);
        }
    }
    @Test
    public void Test4(){
        System.out.println("Test4-----------------------------------------");
        for(int i = 0; i < 11; i++) {
            ISBNos isbnos = new ISBNos();
            String step1 = isbnos.FormString();
            int [] step2 = isbnos.FormIntArray(step1);
            int step3 = isbnos.Sum(step2);
            int step4 = isbnos.Remainder(step3);
            boolean step5 = isbnos.CheckSum(step4);
            String step6 = isbnos.last(step4, step5);
            ISBNos result = new ISBNos(step1, step6);
            String step7 = result.showISBN();
            boolean step8 = result.isISBN(step7);
            System.out.print(result.toString());
            System.out.println(result.toString().length());
            System.out.println(step8);

            //Assert.assertTrue(step1.length()>11 && step1.length() < 20);

        }
    }

}