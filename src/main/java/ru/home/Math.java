package ru.home;

public class Math {

    // Вычисление суммы
    public int Sum(int[] i1, int Index)
    {
        int sum = 0;
        for(int i = 0; i< Index; i++)
        {
            sum += i1[i];
        }
        return sum;
    }

    // Поиск максимального значения в масиве
    public int Max(int[] i1, int Index)
    {
        int max = i1[0];
        for(int i = 1; i< Index; i++)
        {
            if (max <= i1[i])
                max = i1[i];
        }
        return max;
    }
    // Поиск минимального значения в масиве
    public int Min(int[] i1, int Index)
    {
        int min = i1[0];
        for(int i = 1; i < Index; i++)
        {
            if (min >= i1[i])
                min = i1[i];
        }
        return min;
    }
}
