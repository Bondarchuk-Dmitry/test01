package ru.home;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainClass {

    public static void main(String[] args)
    {
        Test01 t1 = new Test01();
        String Path = "c:/test/in/";
        try {
            File f = new File(Path);
            f.list();
            String[] list = f.list();
            for (int i = 0; i < list.length; i++) {
                if (list[i].substring(0, 2).toLowerCase().equals("in")) {
                    t1.ReadFile(Path, list[i], list[i].replace("in", "out"));
                }
            }
            t1.WriteFile(Path);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
