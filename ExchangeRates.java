
import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.sql.SQLOutput;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Boolean.*;
import static java.lang.Math.min;

public class ExchangeRates {
    public static void main(String[] args) throws IOException {
        String str = downloadWebPage("https://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=12/01/2023&date_req2=12/01/2023&VAL_NM_RQ=R01235");

        int begin = str.lastIndexOf("<Value>");
        int begin2 = str.lastIndexOf("</Value>");

        String course = str.substring(begin + 7, begin2);
        System.out.println("Курс на 12.01.2023 = "+course);

        String str1 = downloadWebPage("https://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=13/01/2023&date_req2=13/01/2023&VAL_NM_RQ=R01235");

        int begin3 = str1.lastIndexOf("<Value>");
        int begin4 = str1.lastIndexOf("</Value>");

        String course1 = str1.substring(begin3 + 7, begin4);
        System.out.println("Курс на 13.01.2023 = "+course1);

        String str2 = downloadWebPage("https://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=14/01/2023&date_req2=14/01/2023&VAL_NM_RQ=R01235");

        int begin5 = str2.lastIndexOf("<Value>");
        int begin6 = str2.lastIndexOf("</Value>");

        String course2 = str2.substring(begin5 + 7, begin6);
        System.out.println("Курс на 14.01.2023 ="+course2);

        double x = Double.parseDouble(course.replace(',', '.'));
        double y = Double.parseDouble(course1.replace(',', '.'));
        double z = Double.parseDouble(course2.replace(',', '.'));

        if (x > y && x > z) {
            System.out.print("Курс вырос или упал на ");
            System.out.println((x - y) + (x - z));
        } else if (y > x && y > z) {
            System.out.print("Курс вырос или упал на ");
            System.out.println((y - x) + (y - z));
        }else if (z > x && z > y){
            System.out.print("Курс вырос или упал на ");
            System.out.println((z - x) + (z - y));
        }

        if (x > y && x > z) {
            System.out.println("Наибольшее значение "+x);
        } else if (y > x && y > z) {
            System.out.println("Наибольшее значение "+y);

        }else if (z > x && z > y){
            System.out.println("Наибольшее значение "+z);
        }
        if (x < y && x < z) {
            System.out.println("Наименьшее значение "+x);
        } else if (y < x && y < z) {
            System.out.println("Наименьшее значение "+y);

        }else if (z < x && z < y){
            System.out.println("Наименьшее значение "+z);
        }


    }




    private static String downloadWebPage(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;

        URLConnection urlConnection = new URL(url).openConnection();


        try (InputStream is = urlConnection.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            while ((line = br.readLine()) != null) {
                result.append(line);
            }

        }

        return result.toString();

    }
}