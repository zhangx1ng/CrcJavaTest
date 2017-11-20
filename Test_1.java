import java.util.Arrays;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by pactera on 2017/11/3.
 */
public class Test_1 {
    static public void main(String[] args) throws Exception {
        System.out.println("第六题：通过循环1-200之间的数，找出其中的素数并打印");
        // 通过循环1-200之间的数，找出其中的素数并打印;
        for (int i = 2; i <= 200; i++) {
            if (isPrimeNumber(i)){
                System.out.println(i);}
        }
        //生成无序数组
        int[] disorderarray = createDisorderArray();
        //生成有序数组
        int[] orderarray = createOrderArray();
        //100w无序数组
        int[] disorderarrayExtra = null;
        String maxstring;
        System.out.println("\n" + "第七题：生成一个无序数组");
        System.out.println(Arrays.toString(disorderarray));
        System.out.println("生成一个有序数组");
        System.out.println(Arrays.toString(orderarray));
        //找出最大值以及所在位置
        maxstring = findMax(disorderarray);
        //无序数组排序
        Arrays.sort(disorderarray);
        System.out.println("\n" + "第八题：把上面的无序数组从小到大排序");
        System.out.println(Arrays.toString(disorderarray));
        System.out.println("\n" + "第九题：找出上面无序数组的最大值以及所在元素的位置");
        System.out.println(maxstring);
        System.out.println("\n" + "第十题：找出上面无序数组的重复元素以及重复的个数");
        findRpeat(disorderarray);
        System.out.println("\n" + "附加题1：上面第八题的数组大小改为100W，并输出到一个txt文件中");
        System.out.println("生成D:\\无序数组.txt");
        createDisorderArrayExtra();
        disorderarrayExtra = read();
        System.out.println("\n" + "附加题2：从上面的TXT文件中读取数组，并找出其中重复的元素和对应的个数");
        long startTime = System.currentTimeMillis();//开始时间
        findRpeatExtra(disorderarrayExtra);
        System.out.println("生成D:\\重复元素.txt");
        long endTime = System.currentTimeMillis();//结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");//打印执行时间
        System.out.println("\n" + "附加题3：从上面的TXT文件中读取数组，并找出最大值以及所在位置");
        long startTime1 = System.currentTimeMillis();//开始时间
        System.out.println(findMax(disorderarrayExtra));
        long endTime1 = System.currentTimeMillis();//结束时间
        System.out.println("程序运行时间： " + (endTime1 - startTime1) + "ms");//打印执行时间
        System.out.println("\n" + "附加题4：从上面的TXT文件中读取数组，并进行从小到大的排序");
        Arrays.sort(disorderarrayExtra);
        long startTime2 = System.currentTimeMillis();//开始时间
        write(disorderarrayExtra, "D:\\排序数组.txt");
        System.out.println("生成D:\\序数组.txt");
        long endTime2 = System.currentTimeMillis();//结束时间
        System.out.println("程序运行时间： " + (endTime2 - startTime2) + "ms");//打印执行时间
    }

    //是否为素数
    static private boolean isPrimeNumber(int i) {
        for (int m = 2; m < i; m++) {
            if (i % m == 0)
                return false;
        }
        return true;
    }



    //生成一个无序数组
    static private int[] createDisorderArray() {
        Random rand = new Random();
        int disorderarray[] = null;
        disorderarray = new int[1000];
        for (int i = 0; i < 1000; i++) {
            // 产生一个100W以内的随机数，并赋值给数组的每个成员
            disorderarray[i] = rand.nextInt(1000000) + 1;

            ;
        }
        return disorderarray;
    }

    //生成一个从小到大的有序数组
    static private int[] createOrderArray() {
        Random rand = new Random();
        int orderarray[] = null;

        orderarray = new int[1000];
        for (int i = 0; i < 1000; i++) {
            //i=0时直接生成一个随机数,下一个生成的随机数在上一个随机数-100w的范围之间
            if (i > 0) {
                orderarray[i] = rand.nextInt(1000001 - orderarray[i - 1]) + orderarray[i - 1];
            } else {
                orderarray[i] = rand.nextInt(1000000) + 1;
            }

        }
        return orderarray;
    }

    //找出最大的值，并找出它的位置
    static private String findMax(int[] disorderarray) {
        int maxnum = 0;
        String maxindex = "在位置：";
        for (int i = 0; i < disorderarray.length; i++) {
            //当前数比最大值大才赋值,i为0时直接赋值
            if (i > 0) {
                if (disorderarray[i] > maxnum) {
                    maxnum = disorderarray[i];
                }
            } else {
                maxnum = disorderarray[i];
            }
        }
        //最大数可能重复,需要全部找出位置
        for (int i = 0; i < disorderarray.length; i++) {
            if (disorderarray[i] == maxnum) {
                maxindex = maxindex + " " + (i + 1);
            }
        }
        return "最大值是：" + maxnum + maxindex;
    }

    //找出重复元素，并计算个数
    static private void findRpeat(int[] disorderarray) {
        // 把数组放入传入map中
        Map map = new HashMap<Integer, Integer>();
        // 放入已经打印过的数字
        Map map2 = new HashMap<Integer, Integer>();
        int value;
        for (int i = 0; i < disorderarray.length; i++) {
            //在map中代表有重复
            if (map.containsKey(disorderarray[i])) {
                //不在map2中说明还未打印，且把未打印的传入map2中,如果打印过存在map2中，则增加value值计算个数
                if (!map2.containsKey(disorderarray[i])) {
                    //放入已打印的
                    map2.put(disorderarray[i], 1);
                } else {
                    value = (Integer) map2.get(disorderarray[i]);
                    map2.put(disorderarray[i], value + 1);
                }
            } else {
                //如果不在map中则把元素放入
                map.put(disorderarray[i], 0);
            }
        }
//遍历map中的键
        if (!map2.isEmpty()) {
            for (Object key : map2.keySet()) {

                System.out.println("数字" + key + "重复了" + map2.get(key) + "次");

            }
        } else {
            System.out.println("没有数字重复");
        }

    }

    //找出重复元素，并计算个数
    static private void findRpeatExtra(int[] disorderarray) throws IOException {
        File file = new File("D:\\重复元素.txt");// 指定要写入的文件
        if (!file.exists()) {// 如果文件不存在则创建
            file.createNewFile();
        }
        // 获取该文件的缓冲输出流

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        // 把数组放入传入map中
        Map map = new HashMap<Integer, Integer>();
        // 放入已经打印过的数字
        Map map2 = new HashMap<Integer, Integer>();
        int value;
        for (int i = 0; i < disorderarray.length; i++) {
            //在map中代表有重复
            if (map.containsKey(disorderarray[i])) {
                //不在map2中说明还未打印，且把未打印的传入map2中,如果打印过存在map2中，则增加value值计算个数
                if (!map2.containsKey(disorderarray[i])) {
                    //放入已打印的
                    map2.put(disorderarray[i], 1);
                } else {
                    value = (Integer) map2.get(disorderarray[i]);
                    map2.put(disorderarray[i], value + 1);
                }
            } else {
                //如果不在map中则把元素放入
                map.put(disorderarray[i], 0);
            }
        }
//遍历map中的键
        if (!map2.isEmpty()) {
            for (Object key : map2.keySet()) {

                bufferedWriter.write("数字" + key + "重复了" + map2.get(key) + "次");
                bufferedWriter.newLine();
            }
        } else {
            bufferedWriter.write("没有数字重复");
        }
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流

    }

    //生成一个100w的无序数组
    static private void createDisorderArrayExtra() throws Exception {
        long startTime = System.currentTimeMillis();//开始时间
        Random rand = new Random();
        int disorderarray[] = null;
        disorderarray = new int[1000 * 1000];
        for (int i = 0; i < 1000 * 1000; i++) {
            // 产生一个100W以内的随机数，并赋值给数组的每个成员
            disorderarray[i] = rand.nextInt(1000000) + 1;
            ;
        }
        // 创建文件,写入数组
        write(disorderarray, "D:\\无序数组.txt");

        long endTime = System.currentTimeMillis();//结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");//打印执行时间
    }

    // 创建文件,写入数组
    private static void write(int[] disorderarray, String filename) throws IOException {

        File file = new File(filename);// 指定要写入的文件
        if (!file.exists()) {// 如果文件不存在则创建
            file.createNewFile();
        }
        // 获取该文件的缓冲输出流

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));


        // 写入信息
        for (int i = 0; i < disorderarray.length; i++) {
            bufferedWriter.write(Integer.toString(disorderarray[i]));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();// 清空缓冲区
        bufferedWriter.close();// 关闭输出流
    }

    private static int[] read() throws FileNotFoundException, IOException {
        long startTime = System.currentTimeMillis();//开始时间
        int disorderarray[] = null;
        int i = 0;
        disorderarray = new int[1000 * 1000];
        File file = new File("D:\\无序数组.txt");// 指定要读取的文件
        // 获得该文件的缓冲输入流
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = "";// 用来保存每次读取一行的内容
        while ((line = bufferedReader.readLine()) != null) {
            disorderarray[i] = Integer.valueOf(line);
            i++;
        }
        bufferedReader.close();// 关闭输入流
        long endTime = System.currentTimeMillis();//结束时间
        System.out.println("\n"+"从文件读取数组时间： " + (endTime - startTime) + "ms");//打印执行时间
        return disorderarray;
    }
}