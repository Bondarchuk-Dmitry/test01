package ru.home;

import java.io.*;
import java.util.ArrayList;


public class Test01 {

    public ArrayList<Sorted> s1 = new ArrayList<Sorted>(); //Колекция обекта Sorted
    private int Total; // итоговая сумма
    private int RowIndex; // индекс строки

    /* подсчёт итоговой суммы*/
    public void LineSum(String Line){
        String[] l1 = Line.split(" ");
        for(int i = 0; i< l1.length; i++)
        {
            Total = Total + Integer.parseInt(l1[i]);
        }
    }

    /* вычисление в строке (Подсчёт суммы, поиск минимального и максимального значения)*/
    public String CheckedLine(String Line, int IndexLine)
    {
        RowIndex++;
        Math m1 = new Math();
        String[] l1 =  Line.split(" ");
        int[] i1 = new int[l1.length];
        for(int i = 0 ; i < l1.length; i++)
            i1[i] = Integer.parseInt(l1[i]);

        if (i1.length >= IndexLine)
        {
            return RowIndex + "    :   sum=" + m1.Sum(i1, IndexLine) + "   :   MIN = " + m1.Min(i1, IndexLine) + "   :   MAX = " + m1.Max(i1, IndexLine);
        }
        return "Error найдено " + i1.length + " чисел вместо " + IndexLine;
    }

    public void ReadFile(String Path, String InFile, String OutputFile)throws IOException{
        Total = 0;
        RowIndex = 0;
        int Row = 0;
        int Column = 0;
        ArrayList<String> Readline = new ArrayList<String>();
        try {
            File file = new File(Path + InFile);
            // создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            // создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем первую строку и получим количество строк
            Row = Integer.parseInt(reader.readLine());
            // считаем вторую строку и получим количество столбцов
            Column = Integer.parseInt(reader.readLine());
            // объявляем переменую для записи строки
            String Line = "";
            // заполняем колекцию строками из файла
            while (true){
                Line = reader.readLine();
                if (Line != null) {
                    Readline.add(Line);
                }
                else
                    break;

                LineSum(Line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //записываем результат по каждому in в out
            PrintWriter writer = new PrintWriter(new FileOutputStream(Path + OutputFile));
            if (Readline.size() >= Row)
            {
                for(int j = 0; j < Row; j++ )
                    writer.println(CheckedLine(Readline.get(j), Column));
                System.out.println(Path + OutputFile);
                    writer.print("Total=" + Total);
            }
            else {
                writer.println("Error, найдено " + (Readline.size())  + " строки вместо " + Row);
                Total = 0;
                writer.print("Total=" + Total);
            }
            writer.flush();
            writer.close();

            // Заполняем колекцию и сортируем по возрастанию
            if (s1.size() > 0)
            {
                for (int i = 0; i < s1.size(); i++)
                {
                    if (s1.get(i).TotalNew >= Total) {
                        s1.add(i, new Sorted(Total, OutputFile));
                        break;
                    }
                    if ((i + 1)  == s1.size()) {
                        s1.add(s1.size(), new Sorted(Total, OutputFile));
                        break;
                    }

                }
            }
            else
                s1.add(new Sorted(Total,OutputFile));


        } catch (IOException ex) {

        }

    }

    /* записываем файл Result.txt*/
    public void WriteFile (String Path)
    {
        try{

            PrintWriter writer = new PrintWriter(
                    new FileOutputStream(Path + "Result.txt"));

            for (int i = 0; i < s1.size(); i++) {
                writer.println(s1.get(i).getOutFileName());
            }
            writer.flush();
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
    class Sorted {
        private int TotalNew;
        private String OutFileName;
        Sorted(int Total, String OutFileName)
        {
            this.TotalNew = Total;
            this.OutFileName = OutFileName;
        }

        public int getTotalNew() {
            return TotalNew;
        }

        public void setTotalNew(int totalNew) {
            TotalNew = totalNew;
        }

        public String getOutFileName() {
            return OutFileName;
        }

        public void setOutFileName(String outFileName) {
            OutFileName = outFileName;
        }
    }

}