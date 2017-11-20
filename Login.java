import java.util.Scanner; //导入输入类

/**
 * Created by pactera on 2017/11/2.
 */
public class Login {
    private static String userinfo[][] = {{"user1", "123"}, {"user2", "456"}, {"user3", "789"}};

    static public void main(String[] args) {
        String username;
        String password;
        boolean loginreturn = false;
        //创建输入对象
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        //输入用户名
        username = sc.nextLine();
        System.out.println("请输入密码：");
        //输入密码
        password = sc.nextLine();
        //校验用户名密码
        loginreturn = checkInput(userinfo, username, password);
        if (loginreturn == false)
            System.out.println("登录失败，用户名或密码错误！");
        else
            System.out.println("登录成功！");
    }

    static private boolean checkInput(String[][] userinfo, String username, String password) {
        for (int i = 0; i < userinfo.length; i++) {
            if (username.equals(userinfo[i][0]) & password.equals(userinfo[i][1]))
                return true;
        }
        return false;
    }
}
